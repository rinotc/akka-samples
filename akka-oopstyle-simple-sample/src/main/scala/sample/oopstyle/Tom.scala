package sample.oopstyle

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import sample.oopstyle.Tom.Message

object Tom {
  sealed trait Message
  case object Hello extends Message

  def apply(): Behavior[Message] = Behaviors.setup { context => new Tom(context) }
}

private class Tom(context: ActorContext[Message]) extends AbstractBehavior[Message](context) {
  override def onMessage(msg: Message): Behavior[Message] = {
    msg match {
      case Tom.Hello =>
        println("Hi, I'm Tom.")
        this
    }
  }
}
