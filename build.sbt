import Dependencies._

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / scalacOptions ++= Seq(
  "-feature",     // 明示的に import する必要のある機能を使用した場合、警告と場所を知らせる
  "-deprecation", // 非推奨のAPIの仕様している場合、警告と場所を知らせる
  //    "-Xfatal-warnings", // 警告が出た場合はコンパイル失敗させる
  "-Xlint",          // 推奨される警告の有効化
  "-Ywarn-dead-code" // デットコードがあれば警告する
)

lazy val `akka-scheduler-sample` = (project in file("akka-scheduler-sample"))
  .settings(
    name := "akka-scheduler-sample",
    libraryDependencies ++= Seq(
      TypeSafe.config,
      Logback.classic,
      TypeSafe.Akka.actorTyped,
      TypeSafe.Akka.slf4j
    )
  )

lazy val `akka-oopstyle-simple-sample` = (project in file("akka-oopstyle-simple-sample"))
  .settings(
    name := "akka-oopstyle-simple-sample",
    libraryDependencies ++= Seq(
      TypeSafe.config,
      Logback.classic,
      TypeSafe.Akka.actorTyped,
      TypeSafe.Akka.slf4j
    )
  )