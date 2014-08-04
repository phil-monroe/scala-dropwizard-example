package example.philmonroe.resources

import javax.ws.rs.{Produces, GET, Path}
import javax.ws.rs.core.MediaType
import com.wordnik.swagger.annotations.{ApiOperation, Api}

@Path("/helloworld")
@Api(value = "/helloworld", description = "Hello World API Example2222.")
@Produces(Array(MediaType.TEXT_PLAIN))
class HelloWorldResource {

  @GET
  @ApiOperation(value = "Say Hello World!", notes = "Greets the World.", response = classOf[String], produces = MediaType.TEXT_PLAIN)
  def helloWorld: String = {
    "Hello World"
  }
}
