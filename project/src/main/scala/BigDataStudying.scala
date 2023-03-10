package bigdata.studying.project

import sbt._
import sbt.Keys._

object BigDataStudying {
  object Version {
    val scalaVersion = "2.13.8"
    val spark = "3.3.2"
    val hadoop = "3.3.4"
  }
  object Libs {
    val sparkCore = "org.apache.spark" %% "spark-core" % Version.spark
    val sparkSql = "org.apache.spark" %% "spark-sql" % Version.spark
    val hadoopClient = "org.apache.hadoop" % "hadoop-client" % Version.hadoop
  }

  object Keys {
    val organizeImports = taskKey[Unit]("Organize imports")
  }

  val scalacOptions0 = Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Xlint", // tpolecat enables only some linting checks, we use all of them. This also enables all unused checks.
    "-Ywarn-numeric-widen", // Warn when numerics are widened.
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
    "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  )

  val scalacOptionsConsole = {
    val optionsNotUsedInConsole = Seq("-Xfatal-warnings", "-Xlint")
    scalacOptions0.filterNot(a => optionsNotUsedInConsole.exists(_ == a))
  }

  val hadoopProject = project
    .in(file("hadoop"))
    .settings(
      fork := true,
      libraryDependencies ++= Seq(
        BigDataStudying.Libs.hadoopClient
      )
    )

  val sparkProject = project
    .in(file("spark"))
    .settings(
      javaOptions += "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
      fork := true,
      libraryDependencies ++= Seq(
        BigDataStudying.Libs.sparkSql,
        BigDataStudying.Libs.sparkCore,
      ),
    )
}
