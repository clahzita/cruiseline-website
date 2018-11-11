name := "Cruiseline"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
   cache,
	"com.datastax.cassandra" %
	"cassandra-driver-core" %
	"3.0.2"
)     

play.Project.playJavaSettings
