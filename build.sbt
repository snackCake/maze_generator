name := "maze_generator"

version := "1.0"

lazy val `maze_generator` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws , specs2 % Test,
  "org.apache.pdfbox" % "pdfbox" % "1.8.11" withSources() withJavadoc()
)



unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator
