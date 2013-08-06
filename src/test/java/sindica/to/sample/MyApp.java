package sindica.to.sample;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import sindica.to.dropwizard.spring.SpringApplication;
import sindica.to.sample.configuration.MyConf;

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 05/08/13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class MyApp extends SpringApplication<MyConf> {
  public static void main(String[] args) throws Exception {
    new MyApp().run(args);
  }

  @Override
  public void onConfigureSpringContext(AnnotationConfigWebApplicationContext applicationContext) {
    applicationContext.scan("sindica.to.sample");
  }
}
