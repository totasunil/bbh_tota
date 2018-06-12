organization in ThisBuild := "com.bbh"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

// Connecting to a running Cassandra instance
lagomCassandraEnabled in ThisBuild := false
//lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://172.31.1.101:9042")

lazy val `compugain` = (project in file("."))
  .aggregate(`bbhaccesscontrol-api`, `bbhaccesscontrol-impl`)

lazy val `bbhaccesscontrol-api` = (project in file("bbhaccesscontrol-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `bbhaccesscontrol-impl` = (project in file("bbhaccesscontrol-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslTestKit,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`bbhaccesscontrol-api`)

val lombok = "org.projectlombok" % "lombok" % "1.16.10"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2"

libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"

def common = Seq(
  javacOptions in compile += "-parameters"
)




