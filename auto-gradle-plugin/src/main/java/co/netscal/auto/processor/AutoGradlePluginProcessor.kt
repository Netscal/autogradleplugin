package co.netscal.auto.processor

import co.netscal.auto.annotations.AutoGradlePlugin
import com.google.auto.service.AutoService
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import javax.tools.StandardLocation

@AutoService(Processor::class)
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.ISOLATING)
@SupportedOptions("debug")
class AutoGradlePluginProcessor : AbstractProcessor() {
    /**
     * Maps the @[AutoGradlePlugin] value to the
     * fully qualified class name which implement them.
     */
    private val plugins: MutableMap<String, String> = HashMap()

    override fun process(
        annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment
    ): Boolean {
        return try {
            if (roundEnv.processingOver()) {
                generatePluginFiles()
            } else {
                processAnnotations(annotations, roundEnv)
            }
            true
        } catch (e: Exception) {
            // We don't allow exceptions of any kind to propagate to the compiler
            val writer = StringWriter()
            e.printStackTrace(PrintWriter(writer))
            error(writer.toString())
            true
        }
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return linkedSetOf(AutoGradlePlugin::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    private fun processAnnotations(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ) {
        val elements = roundEnv.getElementsAnnotatedWith(AutoGradlePlugin::class.java)
        log(annotations.toString())
        log(elements.toString())
        for (e in elements) {
            val typeElement = e as TypeElement
            val autoGradlePlugin = e.getAnnotation(
                AutoGradlePlugin::class.java
            )
            val pluginId: String = autoGradlePlugin.value
            if (Validator.isValid(pluginId)) {
                plugins[pluginId] = typeElement.toString()
            } else {
                error(Validator.error?.message!!, typeElement)
            }
        }
    }

    private fun generatePluginFiles() {
        val filer = processingEnv.filer
        for (plugin in plugins.keys) {
            val resourceFile = "META-INF/gradle-plugins/$plugin.properties"
            log("Working on resource file: $resourceFile")
            try {
                val fileObject = filer.createResource(
                    StandardLocation.CLASS_OUTPUT, "", resourceFile
                )
                val out = fileObject.openOutputStream()
                FileWritter.writePluginFile(plugins[plugin], out)
                out.close()
                log("Wrote to: " + fileObject.toUri())
            } catch (e: IOException) {
                error("Unable to create $resourceFile, $e")
                return
            }
        }
    }

    private fun log(msg: String) {
        if (processingEnv.options.containsKey("debug")) {
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, msg)
        }
    }

    private fun error(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
    }

    private fun error(message: String, typeElement: TypeElement) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, typeElement)
    }
}