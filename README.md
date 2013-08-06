# Spring Framework support for Dropwizard

Dropwizard with Spring Framework support for DI

## Motivation
We love Dropwizard and Spring framework. Unfortunately the existing solutions to bring together both technologies are outdated. We like Dropwizard latest version (yeah, the 0.7.0-SNAPSHOT).

And we love pragmatic programming models. The less we write, the more happiness we have.
That's the reason this project was born.

## Setup

### Maven Setup
Very soon kids...

### Gradle Setup

1. Add the following both repos in your _build.gradle_ file

  ```groovy
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
   
   ```groovy
   dependencies {
     compile 'sindica.to:dropwizard-spring:0.1.0-SNAPSHOT'
   }
   ```

3. Create your Dropwizard Application extending from _sindica.to.dropwizard.spring.SpringApplication_

   ```java
   import sindica.to.dropwizard.spring.SpringApplication;

   public class MyApp extends SpringApplication<MyConf> {
     public static void main(String[] args) throws Exception {
       new MyApp().run(args);
     }
   }
   ```
