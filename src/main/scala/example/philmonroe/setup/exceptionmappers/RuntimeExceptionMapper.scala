package example.philmonroe.setup.exceptionmappers

import javax.ws.rs.ext.{Provider, ExceptionMapper}
import example.philmonroe.setup.Logging
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status
import javax.ws.rs.{WebApplicationException, Produces}

case class ExceptionResponse(error: String, message: String, status: Int)

@Provider
@Produces(Array(MediaType.APPLICATION_JSON))
class RuntimeExceptionMapper extends ExceptionMapper[RuntimeException] with Logging {
  override def toResponse(e: RuntimeException): Response = {
    LOG.error(e.getClass.getCanonicalName, e)
    e match {
      case wae: WebApplicationException => wae.getResponse
      case _ => response(e)
    }
  }

  def response(e: RuntimeException, status: Status = Status.INTERNAL_SERVER_ERROR) = {
    Response
      .serverError()
      .entity(ExceptionResponse(e.getClass.getCanonicalName, e.getMessage, status.getStatusCode))
      .`type`(MediaType.APPLICATION_JSON)
      .build()
  }
}