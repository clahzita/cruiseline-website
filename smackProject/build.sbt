
name := "SmackProject"

version := "1.0-SNAPSHOT"

crossScalaVersions := Seq("2.11.12", "2.12.4")

//Utilizar Apache Cassandra 3.0.17
//http://www.apache.org/dyn/closer.lua/cassandra/3.0.17/apache-cassandra-3.0.17-bin.tar.gz

resolvers += "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  //"com.datastax.cassandra" % "cassandra-driver-core" % "3.0.2",
  //"com.datastax.spark" %% "spark-cassandra-connector" % "2.0.10"
"com.datastax.cassandra" % "cassandra-driver-core" % "3.6.0",
"com.datastax.spark" %% "spark-cassandra-connector" % "2.0.10",
"org.apache.spark" %% "spark-core" % "2.2.2",
"org.apache.spark" %% "spark-sql" % "2.2.2",
"org.apache.spark" %% "spark-streaming" % "2.2.2" % "provided",
"org.apache.bahir" %% "spark-streaming-akka" % "2.1.0"
)     



play.Project.playJavaSettings
