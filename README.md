Auto Gradle Plugin

When implementing standalone Gradle Plugins you can use `@AutoGradlePlugin` in order
to register implementations of well-known types using META-INF metadata.

# Download

Download the latest JAR or grab via Maven:
```xml
<dependency>
  <groupId>co.netscal</groupId>
  <artifactId>auto-gradle-plugin-annotations</artifactId>
  <version>1.0.0</version>
</dependency>
````
```xml
<dependency>
  <groupId>co.netscal</groupId>
  <artifactId>auto-gradle-plugin</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
implementation 'co.netscal:auto-gradle-plugin-annotations:1.0.0'
annotationProcessor 'co.netscal:auto-gradle-plugin:1.0.0'
```

Kotlin
```groovy
implementation 'co.netscal:auto-gradle-plugin-annotations:1.0.0'
kapt 'co.netscal:auto-gradle-plugin:1.0.0'
```

# Example

Say you have:
```java
package foo.bar;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

@AutoGradlePlugin("com.example")
final class MyPlugin implements Plugin<Project> {
  // …
}
```

AutoGradlePlugin will generate the file
`META-INF/gradle-plugins/com.example.properties` in the output classes