ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "MarsR"
  )

libraryDependencies += "com.typesafe" % "config" % "1.4.2"
