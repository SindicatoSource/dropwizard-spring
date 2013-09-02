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
package sindica.to.dropwizard.spring.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 02/08/13
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class SpringContextLoaderListener implements ServletContextListener {

  private AnnotationConfigWebApplicationContext context;

  public SpringContextLoaderListener(AnnotationConfigWebApplicationContext context) {
    this.context = context;
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
    context.setServletContext(sce.getServletContext());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //do nothing
  }
}
