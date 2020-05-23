package co.netscal.auto.processor

import com.google.common.base.Charsets
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter

internal object FileWritter {

    /**
     * Writes the plugin implementation-class to a plugin file.
     *
     * @param output not `null`. Not closed after use.
     * @param implementationClass a not `null String` of fully qualified name of the implementation class.
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writePluginFile(implementationClass: String?, output: OutputStream) {
        val writer = BufferedWriter(OutputStreamWriter(output, Charsets.UTF_8))
        val fileOutput = "implementation-class=$implementationClass"
        writer.write(fileOutput)
        writer.flush()
    }
}