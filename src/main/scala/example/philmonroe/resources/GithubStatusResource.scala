package example.philmonroe.resources

import javax.ws.rs.{Produces, GET, QueryParam, Path}
import javax.ws.rs.core.MediaType
import example.philmonroe.clients.GithubAPI
import example.philmonroe.api.{RepoStatus, BranchStatus}
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import example.philmonroe.api.github.Branch
import com.wordnik.swagger.annotations.{ApiParam, ApiOperation, Api}
import com.codahale.metrics.annotation.{ExceptionMetered, Metered, Timed}


@Path("/status")
@Api("GitHub-Status")
@Produces(Array(MediaType.APPLICATION_JSON))
class GitHubStatusResource(gitHub: GithubAPI) {

  @GET
  @Timed(name = "checkRepo-timing")
  @Metered
  @ExceptionMetered(name = "checkRepo-failures")
  @ApiOperation(value = "Check Repo Status", notes = "Checks the build status for every branch in the given GitHub repositories.")
  def checkRepo(@QueryParam("repos")
                @ApiParam(allowMultiple = true, value = "Comma separated list of GitHub repos", defaultValue = "dropwizard/dropwizard")
                repos: String = "dropwizard/dropwizard"
                 ): Seq[RepoStatus] = {

    repos
      .split(",")
      .map(fetchRepoStatus)
      .map(await)
  }

  def fetchRepoStatus(repo: String) = {
    future {
      gitHub
        .getBranches(repo)
        .map(fetchBranchStatus(repo, _))
        .map(await)
    }.map { branches =>
      RepoStatus(repo, branches)
    }
  }

  def fetchBranchStatus(repo: String, branch: Branch) = {
    future {
      val statuses = gitHub.getStatus(repo, branch.name)
      if (statuses.isEmpty)
        BranchStatus(branch.name, "unknown", "The branch has yet to be tested.", null, null)
      else
        BranchStatus.fromResponse(branch.name, statuses.head)
    }
  }

  def await[T](future: Future[T]) = Await.result(future, Duration.Inf)
}
