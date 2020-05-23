package co.netscal.auto.processor

import com.google.common.io.ByteSource
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourcesSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.tools.StandardLocation

/**
 * Tests the [AutoGradlePluginProcessor].
 */
@RunWith(JUnit4::class)
class AutoGradlePluginProcessorTest {
    @Test
    fun pluginName() {
        val data = "implementation-class=test.AnotherPlugin"
        val expected = ByteSource.wrap(data.toByteArray())
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/AnotherPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .compilesWithoutError()
            .and()
            .generatesFileNamed(
                StandardLocation.CLASS_OUTPUT,
                "",
                "META-INF/gradle-plugins/com.example.properties"
            )
            .withContents(expected)
    }

    @Test
    fun noPluginName() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/NoPluginNamePlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.MISSING_PLUGIN_ID_ERROR.message)
    }

    @Test
    fun noDotPlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/NoDotPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.CONTAINS_SINGLE_DOT.message)
    }

    @Test
    fun startsWithDotPlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/StartsWithDotPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.STARTS_OR_ENDS_WITH_DOT.message)
    }

    @Test
    fun endsWithDotPlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/EndsWithDotPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.STARTS_OR_ENDS_WITH_DOT.message)
    }

    @Test
    fun consecutiveDotsPlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/ConsecutiveDotsPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.CONTAINS_CONSECUTIVE_DOT.message)
    }

    @Test
    fun unSupportedCharactersPlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/UnSupportedCharactersPlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.UNSUPPORTED_CHARACTERS.message)
    }

    @Test
    fun notAllowedNamespacePlugin() {
        JavaSourcesSubject.assertThat(JavaFileObjects.forResource("test/NotAllowedNamespacePlugin.java"))
            .processedWith(AutoGradlePluginProcessor())
            .failsToCompile()
            .withErrorContaining(Error.NOT_ALLOWED_NAMESPACES.message)
    }
}