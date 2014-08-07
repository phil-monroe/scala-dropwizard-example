package example.philmonroe.resources

import javax.ws.rs.{Path, Produces, GET}
import javax.ws.rs.core.MediaType
import example.philmonroe.api.Time
import com.wordnik.swagger.annotations.{Api, ApiOperation}
import com.codahale.metrics.annotation.{ExceptionMetered, Metered}


@Path("/time")
@Api("/time")
@Produces(Array(MediaType.APPLICATION_JSON))
class TimeResource {

  @GET
  @Metered
  @ExceptionMetered(name = "search-errors")
  @ApiOperation(value = "getTime", response = classOf[Time], produces = MediaType.APPLICATION_JSON)
  def getTime: Time = {
    Time(currentTime)
  }

  def currentTime =
    new java.sql.Timestamp(System.currentTimeMillis()).toString
}
