package example.philmonroe.unit

import example.philmonroe.resources.HelloWorldResource
import example.philmonroe.test.UnitSpec

class HelloWorldSpec extends UnitSpec {

  val resource = new HelloWorldResource

  describe("#helloWorld") {
    it("says hello") {
      resource.helloWorld shouldEqual ("Hello World")
    }

    it("doesn't say goodbye to phil") {
      resource.helloWorld shouldNot equal("Hello Phil")
    }
  }
}
