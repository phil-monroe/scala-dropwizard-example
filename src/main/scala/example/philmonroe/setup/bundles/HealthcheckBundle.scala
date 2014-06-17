package example.philmonroe.setup.bundles

import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.healthchecks.GitHubHealthCheck

/**
 * Created by phil on 6/4/14.
 */
class HealthcheckBundle(githubBundle: GithubBundle) extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(env: Environment): Unit = {
    env.healthChecks().register("GitHub", new GitHubHealthCheck(githubBundle))


  }
}
