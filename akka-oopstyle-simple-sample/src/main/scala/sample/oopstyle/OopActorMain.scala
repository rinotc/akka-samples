package sample.oopstyle

import akka.actor.typed.ActorSystem

object OopActorMain {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem.create(OopActor(), "OopActorSystem")

    actorSystem ! OopActor.Message1
    actorSystem ! OopActor.Message2("Hi OopActor!")

    Thread.sleep(1000)
    actorSystem.terminate()
  }
}
