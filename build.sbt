ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "scala-func-prog-mooc",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"
  )
