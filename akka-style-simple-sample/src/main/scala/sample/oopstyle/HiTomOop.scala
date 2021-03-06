package sample.oopstyle

import akka.actor.typed.ActorSystem

object HiTomOop {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem.create(Tom(), "50FirstDatesSystem")

    actorSystem ! Tom.Hello
    actorSystem ! Tom.Hello
    actorSystem ! Tom.Hello

    Thread.sleep(500)
    actorSystem.terminate()
  }
}
