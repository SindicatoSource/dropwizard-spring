# Spring Framework support for Dropwizard

Dropwizard with Spring Framework support for DI


##Setup

### Maven Setup



### Gradle Setup

1. Add below both repos in your _build.gradle_ file
```
repositories {
    maven {
      url 'http://oss.sonatype.org/content/repositories/snapshots'
    }
    maven {
      url 'http://repo.sindica.to/nexus/content/repositories/public-milestones/'
    }
}
 ```

2. Add the following dependency

```
dependencies {
  compile 'sindica.to:dropwizard-spring:0.1.0-SNAPSHOT'
}
```