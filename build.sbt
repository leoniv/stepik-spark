import stepik.spark.project.StepikSpark
import StepikSpark.Keys._

name := "Stepik Spark"

version := "1.0"

scalaVersion := StepikSpark.Version.scalaVersion

scalacOptions := StepikSpark.scalacOptions
Compile / console / scalacOptions := StepikSpark.scalacOptionsConsole
Test / console / scalacOptions := StepikSpark.scalacOptionsConsole

libraryDependencies ++= Seq(
  StepikSpark.Libs.sparkSql,
  StepikSpark.Libs.sparkCore
)

// Fix cannot access class sun.nio.ch.DirectBuffer
javaOptions += "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED"
fork := true

// Organize imports
scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"
scalafixConfig := Some(baseDirectory.value / ".scalafix.conf")
semanticdbEnabled := true
semanticdbVersion := scalafixSemanticdb.revision
organizeImports := scalafixAll.toTask(" OrganizeImports").value
