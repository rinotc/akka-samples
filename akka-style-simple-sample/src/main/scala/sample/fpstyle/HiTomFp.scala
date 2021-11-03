package sample.fpstyle

import akka.actor.typed.ActorSystem

object HiTomFp {
  import Tom._
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem(Tom(), "50FirstDatesSystem")

    actorSystem ! Hello
    actorSystem ! Hello
    actorSystem ! Hello

    Thread.sleep(500)
    actorSystem.terminate()
  }
}
