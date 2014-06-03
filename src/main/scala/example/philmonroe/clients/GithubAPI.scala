package example.philmonroe.clients

import com.sun.jersey.api.client.{WebResource, Client}
import javax.ws.rs.core.MediaType
import example.philmonroe.api.github.{RateLimitResponse, Branch, Status}





class GithubAPI(httpClient: Client, token: String, endpoint: String = "https://api.github.com") {
  val UserAgent = "Github Status Example App - phil-monroe/scala-dropwizard-example"
  val AuthorizationHeader = s"token ${token}"

  def getStatus(repo: String, branch: String): Seq[Status] =
    getResourceBuilder(s"repos/$repo/statuses/$branch").get(classOf[Array[Status]]).toSeq

  def getBranches(repo: String): Seq[Branch] =
    getResourceBuilder(s"repos/$repo/branches").get(classOf[Array[Branch]]).toSeq

  def getRateLimit() = getResourceBuilder("rate_limit").get(classOf[RateLimitResponse])

  private[this] def getResourceBuilder(path: String): WebResource#Builder =
    httpClient
      .resource(s"$endpoint/$path")
      .accept(MediaType.APPLICATION_JSON)
      .header("User-Agent", UserAgent)
      .header("Authorization", AuthorizationHeader)
}
