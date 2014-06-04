import sbt._

object Git {
  lazy val GitSha = Process("git rev-parse HEAD").lines.head
  lazy val GitShaShort = GitSha.substring(0, 8)

  def version(baseVersion: String) = {
    s"$baseVersion-$GitShaShort"
  }
}