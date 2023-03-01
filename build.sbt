import stepik.spark.project.StepikSpark
import StepikSpark.Keys._

name := "Stepik Spark"

version := "1.0"

scalaVersion := StepikSpark.Version.scalaVersion

scalacOptions := StepikSpark.scalacOptions
Compile / console / scalacOptions := StepikSpark.scalacOptionsConsole
Test / console / scalacOptions := StepikSpark.scalacOptionsConsole

libraryDependencies ++= Seq(
  StepikSpark.Libs.spark
)

// Organize imports
scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"
scalafixConfig := Some(baseDirectory.value / ".scalafix.conf")
semanticdbEnabled := true
semanticdbVersion := scalafixSemanticdb.revision
organizeImports := scalafixAll.toTask(" OrganizeImports").value
