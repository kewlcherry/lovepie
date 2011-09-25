import sbt._
import Keys._
import com.github.siasia._

object General {
  val settings = Defaults.defaultSettings ++ Seq(
    name := "lovep.ie",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.9.0-1"
  )
}

object LovePieBuild extends Build {
  lazy val main = Project(
    "GAE",
    file("gae"),
    settings = General.settings ++ sbtappengine.AppenginePlugin.webSettings ++ Seq(
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % "2.0.1",
//        "org.scalatra" %% "scalatra-scalate" % "2.0.0",
//        "org.fusesource.scalate" % "scalate-core" % "1.5.2",
        "net.databinder" %% "dispatch-http-gae" % "0.8.3",
        "net.databinder" %% "dispatch-lift-json" % "0.8.5",

        "org.scalatra" %% "scalatra-specs" % "2.0.1" % "test",
        "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty",
        "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
      )
    )
  )
}
