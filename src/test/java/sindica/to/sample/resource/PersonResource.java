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

  }
}
