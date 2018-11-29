
name := "SmackProject"

version := "1.0-SNAPSHOT"

//Utilizar Apache Cassandra 3.0.17
//http://www.apache.org/dyn/closer.lua/cassandra/3.0.17/apache-cassandra-3.0.17-bin.tar.gz

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.2",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.10",
  "org.apache.spark" %% "spark-core" % "2.1.2" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api"),
  "org.apache.spark" %% "spark-sql" % "2.1.2" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api"),
  "org.apache.spark" %% "spark-streaming" % "2.1.2" % "provided" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api"),
  "org.glassfish.hk2" % "hk2-utils" % "2.2.0",
  "org.glassfish.hk2" % "hk2-locator" % "2.2.0",
  "javax.validation" % "validation-api" % "2.0.1.Final",
  "org.apache.bahir" %% "spark-streaming-akka" % "2.1.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.3",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13" from "file:/home/clarissa/eclipse-workspace/smackProject/akka-stream-kafka_2.12-0.16.jar"
)     

play.Project.playJavaSettings
