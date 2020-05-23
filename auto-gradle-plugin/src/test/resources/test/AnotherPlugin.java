package test;

import co.netscal.auto.annotations.AutoGradlePlugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

@AutoGradlePlugin("com.example")
public class AnotherPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

    }
}