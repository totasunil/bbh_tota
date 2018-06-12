organization in ThisBuild := "com.bbh"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"



// START OF WARNING
// this setup is only used on DevMode (aka 'sbt runAll') . It is not applicatble in
// production and it should not point to production machines.
lagomCassandraEnabled in ThisBuild := false
//lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://172.31.1.101:9042")
lagomKafkaEnabled in ThisBuild := false
//lagomKafkaAddress in ThisBuild := "172.31.5.219:9092"
// END OF WARNING


lazy val `bbhServices` = (project in file("."))
  .aggregate(`createSwiftMessage-api`, `createSwiftMessage-impl`, `readSwiftMessage-api`, `readSwiftMessage-impl`)


lazy val `createSwiftMessage-api` = (project in file("createSwiftMessage-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `createSwiftMessage-impl` = (project in file("createSwiftMessage-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslKafkaBroker,
      lagomJavadslTestKit,
      // kafka dependeices are already included in lagomJavadslKafkaBroker
      //      "org.apache.kafka" % "kafka_2.11" % "0.10.0.1",
      //      "org.apache.kafka" % "kafka-clients" % "0.10.0.1",
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`createSwiftMessage-api`)

lazy val `readSwiftMessage-api` = (project in file("readSwiftMessage-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `readSwiftMessage-impl` = (project in file("readSwiftMessage-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslKafkaBroker,
      lagomJavadslTestKit,
      // kafka dependeices are already included in lagomJavadslKafkaBroker
      //      "org.apache.kafka" % "kafka_2.11" % "0.10.0.1",
      //      "org.apache.kafka" % "kafka-clients" % "0.10.0.1",
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`readSwiftMessage-api`)

val lombok = "org.projectlombok" % "lombok" % "1.16.10"

def common = Seq(
  javacOptions in compile += "-parameters"
)

