name := """useful-government-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.mongodb"            %  "mongodb-driver"     % "3.0.4",//unfamiliar with mongo driver
  "com.lihaoyi"            %% "pprint"             % "0.4.3"
)

