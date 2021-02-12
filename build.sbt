name := "untitled2"
 
version := "1.0" 
      
lazy val `untitled2` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.4"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += "org.scalatestplus" %% "selenium-3-141" % "3.2.2.0" % "test"

libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.2" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.2" % "test"