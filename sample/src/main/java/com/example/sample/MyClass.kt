package com.example.sample

import co.netscal.auto.annotations.AutoGradlePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

@AutoGradlePlugin("com.github.test.plugin")
class MyClassPlugin: Plugin<Project> {
    override fun apply(p0: Project) {
    }
}
