package example.philmonroe.resources

import javax.ws.rs.{Path, Produces, GET}
import javax.ws.rs.core.MediaType
import example.philmonroe.api.Time


@Path("/time")
@Produces(Array(MediaType.APPLICATION_JSON))
class TimeResource {

  @GET
  def getTime: Time = {
    Time(currentTime)
  }

  def currentTime =
    new java.sql.Timestamp(System.currentTimeMillis()).toString
}
