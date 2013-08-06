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

import com.codahale.dropwizard.Configuration;
import com.codahale.dropwizard.setup.Bootstrap;
import com.codahale.dropwizard.setup.Environment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Convenient class to simplify the BaseSpringApplication class. This class is an adapter.
 */
public abstract class SpringApplication<T extends Configuration> extends BaseSpringApplication<T> {

  @Override
  public void onInitialize(Bootstrap<T> bootstrap) {
  }

  @Override
  public void onRun(T configuration, Environment environment, AnnotationConfigWebApplicationContext applicationContext) throws ClassNotFoundException {
  }

  @Override
  public void onConfigureSpringContext(AnnotationConfigWebApplicationContext applicationContext) {
  }
}
