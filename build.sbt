//import com.typesafe.sbt.SbtMultiJvm.multiJvmSettings
//import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

val akkaVersion = "2.5.25"

lazy val root = (project in file("."))
  .enablePlugins(MultiJvmPlugin)
  .configs(MultiJvm)
//  .settings(multiJvmSettings: _*)
//  .configs(MultiJvm)
  .settings(
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion,
      "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
      "org.scalatest" %% "scalatest" % "3.0.7" % Test),
    // disable parallel tests
    parallelExecution in Test := false
  )
