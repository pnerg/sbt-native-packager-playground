import com.typesafe.sbt.packager.docker.DockerPermissionStrategy

enablePlugins(JavaAppPackaging, DockerPlugin)

organization := "org.dmonix"
name := "sbt-native-packager-playground"
version := "0.0.0"
scalaVersion := "2.13.3"

maintainer in Docker := "Peter Nerg"
daemonUserUid in Docker := None
daemonGroup in Docker := "daemon"
daemonUser in Docker    := "daemon"
dockerPermissionStrategy in Docker := DockerPermissionStrategy.None 
dockerBaseImage in Docker := "adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.7_10"
dockerExposedPorts in Docker := Seq(8080)
dockerLabels in Docker := Map("Monkey" -> "Gorilla")

mainClass in (Compile, run) := Some("org.dmonix.Main")