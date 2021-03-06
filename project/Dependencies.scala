import sbt._

object Dependencies {

  object Versions {
    val akka           = "2.6.17"
    val akkaHttp       = "10.2.7"
    val logback        = "1.2.3"
    val typeSafeConfig = "1.4.1"
    val scalaTest      = "3.2.9"
  }

  object Logback {
    val classic = "ch.qos.logback" % "logback-classic" % Versions.logback
  }

  object ScalaTest { // https://www.scalatest.org/
    val scalactic = "org.scalactic" %% "scalactic" % Versions.scalaTest
    val scalatest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  }

  object TypeSafe {

    val config = "com.typesafe" % "config" % Versions.typeSafeConfig

    object Akka { // https://doc.akka.io/docs/akka/current/index.html
      // akkaには新APIとclassic APIがある。新APIを使うようにしている。
      // https://doc.akka.io/docs/akka/current/index-classic.html (classic API)
      val actorTyped         = "com.typesafe.akka" %% "akka-actor-typed"         % Versions.akka
      val testKit            = "com.typesafe.akka" %% "akka-actor-testkit-typed" % Versions.akka
      val slf4j              = "com.typesafe.akka" %% "akka-slf4j"               % Versions.akka
      val stream             = "com.typesafe.akka" %% "akka-stream"              % Versions.akka
      val persistenceTyped   = "com.typesafe.akka" %% "akka-persistence-typed"   % Versions.akka
      val persistenceTestKit = "com.typesafe.akka" %% "akka-persistence-testkit" % Versions.akka % Test
      val http               = "com.typesafe.akka" %% "akka-http"                % Versions.akkaHttp
    }
  }
}
