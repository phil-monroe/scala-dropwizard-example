package example.philmonroe.setup.bundles

import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.healthchecks.GitHubHealthCheck

class HealthcheckBundle(githubBundle: GithubBundle) extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(env: Environment): Unit = {
    env.healthChecks().register("GitHub", new GitHubHealthCheck(githubBundle))


  }
}
