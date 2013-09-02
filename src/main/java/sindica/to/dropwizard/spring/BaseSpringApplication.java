/*
 * Copyright 2013 Domingo Suarez Torres
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package sindica.to.dropwizard.spring;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.Configuration;
import com.codahale.dropwizard.lifecycle.Managed;
import com.codahale.dropwizard.servlets.tasks.Task;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import sindica.to.dropwizard.spring.servlet.SpringContextLoaderListener;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.EventListener;
import java.util.Map;

/**
 * Base class which load the Spring application context and the Dropwizard application.
 */
public abstract class BaseSpringApplication<T extends Configuration> extends Application<T> {
  private static final Logger LOG = LoggerFactory.getLogger(BaseSpringApplication.class);

  /**
   * Callback method when the Dropwizard application just finished its initialization.
   *
   * @param bootstrap
   */
  abstract void onInitialize(Bootstrap<T> bootstrap);

  /**
   * Callback method before run the Dropwizard application.
   *
   * @param configuration
   * @param environment
   * @param applicationContext
   * @throws ClassNotFoundException
   */
  abstract void onRun(T configuration, Environment environment, AnnotationConfigWebApplicationContext applicationContext) throws ClassNotFoundException;

  /**
   * Callback method to delegate to the developer the Spring framework fine tunning configuration.
   *
   * @param applicationContext
   */
  abstract void onConfigureSpringContext(AnnotationConfigWebApplicationContext applicationContext);


  @Override
  public final void initialize(Bootstrap<T> bootstrap) {
    onInitialize(bootstrap);
  }

  @Override
  public final void run(T configuration, Environment environment) throws ClassNotFoundException {
    AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

    parent.refresh();
    parent.getBeanFactory().registerSingleton("configuration", configuration);
    parent.registerShutdownHook();
    parent.start();

    //the real main app context has a link to the parent context
    ctx.setParent(parent);

    onConfigureSpringContext(ctx);

    ctx.refresh();
    ctx.registerShutdownHook();
    ctx.start();

    //HealthChecks
    Map<String, HealthCheck> healthChecks = ctx.getBeansOfType(HealthCheck.class);
    for (Map.Entry<String, HealthCheck> entry : healthChecks.entrySet()) {
      LOG.info("Registering HealthCheck: ${entry.key}");
      environment.healthChecks().register(entry.getKey(), entry.getValue());
    }

    //resources
    Map<String, Object> resources = ctx.getBeansWithAnnotation(Path.class);
    for (Map.Entry<String, Object> entry : resources.entrySet()) {
      LOG.info("Registering Resource: ${entry.key}");
      environment.jersey().register(entry.getValue());
    }

    //tasks
    Map<String, Task> tasks = ctx.getBeansOfType(Task.class);
    for (Map.Entry<String, Task> entry : tasks.entrySet()) {
      LOG.info("Registering Task: ${entry.key}");
      environment.admin().addTask(entry.getValue());
    }

    //Managed
    Map<String, Managed> managers = ctx.getBeansOfType(Managed.class);
    for (Map.Entry<String, Managed> entry : managers.entrySet()) {
      LOG.info("Registering Task: ${entry.key}");
      environment.lifecycle().manage(entry.getValue());
    }

    //JAX-RS providers
    Map<String, Object> providers = ctx.getBeansWithAnnotation(Provider.class);
    for (Map.Entry<String, Object> entry : providers.entrySet()) {
      LOG.info("Registering Provider: ${entry.key}");
      //environment.jersey().addProvider(entry.getValue())
    }

    //last, but not least, let's link Spring to the embedded Jetty in Dropwizard
    EventListener servletListener = new SpringContextLoaderListener(ctx);
    environment.servlets().addServletListeners(servletListener);

    onRun(configuration, environment, ctx);
  }
}
