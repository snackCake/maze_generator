name := "maze_generator"

version := "1.0"

lazy val `maze_generator` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc withSources() withJavadoc(),
  cache withSources() withJavadoc(),
  ws withSources() withJavadoc(),
  specs2 % Test withSources() withJavadoc(),
  "org.apache.pdfbox" % "pdfbox" % "2.0.0-SNAPSHOT" withSources()
)



unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "ApacheSnapshot" at "https://repository.apache.org/content/groups/snapshots"

routesGenerator := InjectedRoutesGenerator
