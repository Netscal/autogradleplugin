package co.netscal.auto.processor

import java.util.regex.Pattern

internal object Validator {
    var error: Error? = null
        private set

    /**
     * Validation Rules follow the guide in [Gradle website](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:creating_a_plugin_id)
     * @param id The Plugin id specified in @[co.netscal.auto.annotations.AutoGradlePlugin]
     * @return whether id complies with rules or not
     */
    fun isValid(id: String): Boolean {
        if (id.isEmpty()) {
            error =
                Error.MISSING_PLUGIN_ID_ERROR
            return false
        }
        if (!id.contains(".")) {
            error =
                Error.CONTAINS_SINGLE_DOT
            return false
        }
        if (id.startsWith(".") || id.endsWith(".")) {
            error =
                Error.STARTS_OR_ENDS_WITH_DOT
            return false
        }
        if (id.contains("..")) {
            error =
                Error.CONTAINS_CONSECUTIVE_DOT
            return false
        }
        if (Pattern.compile("[^A-Z0-9a-z-.]").matcher(id).find()) {
            error =
                Error.UNSUPPORTED_CHARACTERS
            return false
        }
        if (id.contains("org.gradle") || id.contains("com.gradleware")) {
            error =
                Error.NOT_ALLOWED_NAMESPACES
            return false
        }
        return true
    }

}