import sbt._

object Git {
  lazy val GitSha = Process("git rev-parse HEAD 2>/dev/null || echo $GIT_SHA").lines.head
  lazy val GitShaShort = GitSha.substring(0, 8)

  def version(baseVersion: String) = {
    s"$baseVersion-$GitShaShort"
  }
}