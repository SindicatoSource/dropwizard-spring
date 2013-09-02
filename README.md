# Spring Framework support for Dropwizard

Dropwizard with Spring Framework support for DI

## Motivation
We love Dropwizard and Spring framework. Unfortunately the existing solutions to bring together both technologies are outdated. We like Dropwizard latest version (yeah, the 0.7.0-SNAPSHOT).

And we love pragmatic programming models. The less we write, the more happiness we have.
That's the reason this project was born.

## Build Status
[![Build Status](http://jenkins.sindica.to/buildStatus/icon?job=DropwizardSpring%20master%20build)](http://jenkins.sindica.to/job/DropwizardSpring%20master%20build/)

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
      compile 'sindica.to:dropwizard-spring:0.2.0-SNAPSHOT'
    }
    ```

## Development

1. Create your Dropwizard Application extending from _sindica.to.dropwizard.spring.SpringApplication_

    ```java
    package sindica.to.sample;

    import sindica.to.dropwizard.spring.SpringApplication;

    public class MyApp extends SpringApplication<MyConf> {
      public static void main(String[] args) throws Exception {
        new MyApp().run(args);
      }

      @Override
      public void onConfigureSpringContext(AnnotationConfigWebApplicationContext applicationContext) {
        //Scan your own packages for Spring beans with annotations
        //like @Component, @Repository, @Inject, @Autowired, etc
        applicationContext.scan("sindica.to.sample");
      }
    }
    ```
2. Create the usual Dropwizard artifacts as always

    ```java
    package sindica.to.sample.resource;

    import org.springframework.stereotype.Component;
    import sindica.to.sample.model.Person;

    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import java.util.List;

    @Path("/person")
    @Component
    public class PersonResource {
      @GET
      public List<Person> list() {
        //TODO: implement this...
      }
    }
    ```
    The trick is to add the Spring's Annotations like _@Component_

3. Enjoy, hack, deliver, profit¡