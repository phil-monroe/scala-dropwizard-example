package example.philmonroe.resources

import javax.ws.rs.{Produces, GET, Path}
import javax.ws.rs.core.MediaType
import com.wordnik.swagger.annotations.{ApiOperation, Api}
import com.codahale.metrics.annotation.{ExceptionMetered, Metered}

@Path("/helloworld")
@Api(value = "/helloworld", description = "Hello World API Example.")
@Produces(Array(MediaType.TEXT_PLAIN))
class HelloWorldResource {

  @GET
  @Metered
  @ExceptionMetered(name = "hello-errors")
  @ApiOperation(value = "Say Hello World!", notes = "Greets the World.", response = classOf[String], produces = MediaType.TEXT_PLAIN)
  def helloWorld: String = {
    "Hello World"
  }
}
