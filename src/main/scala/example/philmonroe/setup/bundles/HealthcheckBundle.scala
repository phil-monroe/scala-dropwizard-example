package example.philmonroe.setup.bundles

import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}

class HealthcheckBundle() extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(env: Environment): Unit = {


  }
}
