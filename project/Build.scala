import sbt._
import Keys._
import com.github.siasia._

object General {
  val settings = Defaults.defaultSettings ++ Seq(
    name := "lovep.ie",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.9.1"
  )
}

object LovePieBuild extends Build {
  lazy val main = Project(
    "GAE",
    file("gae"),
    settings = General.settings ++ sbtappengine.AppenginePlugin.webSettings ++ Seq(
      libraryDependencies ++= Seq(
        "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty",
        "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
      )
    )
  )
}
