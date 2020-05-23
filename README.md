Auto Gradle Plugin [ ![Download](https://api.bintray.com/packages/kdrakonakis/maven/auto-gradle-plugin/images/download.svg) ](https://bintray.com/kdrakonakis/maven/auto-gradle-plugin/_latestVersion) [![Build Status](https://travis-ci.org/Netscal/autogradleplugin.svg?branch=master)](https://travis-ci.org/Netscal/autogradleplugin)

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

# License

Copyright 2020 Netscal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.