package co.netscal.auto.processor

internal enum class Error(val message: String) {
    MISSING_PLUGIN_ID_ERROR("No plugin id provided for element!"),
    CONTAINS_SINGLE_DOT("Plugin id must contain at least 1 '.' character"),
    STARTS_OR_ENDS_WITH_DOT("Plugin id cannot start or end with '.' character"),
    CONTAINS_CONSECUTIVE_DOT("Plugin id cannot contain consecutive '.' characters"),
    UNSUPPORTED_CHARACTERS("Plugin id ay contain any alphanumeric character, '.', and '-'"),
    NOT_ALLOWED_NAMESPACES("Plugin id cannot use 'org.gradle' or 'com.gradleware' namespaces")
}