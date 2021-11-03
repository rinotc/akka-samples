package sample.oopstyle

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

/**
 * @note OOPスタイルでの書き方は一貫している。
 */
object OopActor {
  sealed trait Message
  final case object Message1               extends Message
  final case class Message2(param: String) extends Message

  def apply(): Behavior[Message] = Behaviors.setup(context => new OopActor(context))
}

private class OopActor(context: ActorContext[OopActor.Message]) extends AbstractBehavior(context) {
  override def onMessage(msg: OopActor.Message): Behavior[OopActor.Message] = {
    msg match {
      case OopActor.Message1 =>
        println("receive message 1")
        Behaviors.same
      case OopActor.Message2(param) =>
        println(s"receive message 2: $param")
        Behaviors.same
    }
  }
}
