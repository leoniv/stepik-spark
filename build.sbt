import bigdata.studying.project.BigDataStudying
import BigDataStudying.Keys._

name := "Big data studying"

version := "1.0"

scalaVersion := BigDataStudying.Version.scalaVersion

ThisBuild / scalacOptions := BigDataStudying.scalacOptions0
ThisBuild /Compile / console / scalacOptions := BigDataStudying.scalacOptionsConsole
ThisBuild / Test / console / scalacOptions := BigDataStudying.scalacOptionsConsole

lazy val sparkProject = BigDataStudying.sparkProject
lazy val hadoopProject = BigDataStudying.hadoopProject
