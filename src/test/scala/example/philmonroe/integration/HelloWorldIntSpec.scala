package example.philmonroe.integration

import com.sun.jersey.api.client.ClientResponse
import example.philmonroe.test.IntegrationSpec


class HelloWorldIntSpec extends IntegrationSpec {

  describe("/helloworld") {
    it("does something") {
      val response = newClient.path("/helloworld").get(classOf[ClientResponse])
      response.getStatus shouldEqual 200
      response.getEntity(classOf[String]) shouldEqual "Hello World"
    }
  }
}
