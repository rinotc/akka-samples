package sample.fpstyle

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}

/**
 * @note Akka Typed actor はOOP スタイルと FPスタイルの両方で書ける。下記はFPスタイル。
 *       FPスタイルはOOPスタイルと比べて短く書くことができる。
 *       FPスタイルは [[Behaviors.setup]] で実装した `apply` だけあれば良い。
 */
object Tom {
  sealed trait Message
  final case object Hello extends Message

  //noinspection MatchToPartialFunction
  def apply(): Behavior[Message] = Behaviors.setup { context: ActorContext[Message] =>
    Behaviors.receiveMessage { message: Message =>
      message match {
        case Hello =>
          println("Hi, I'm Tom.")
          Behaviors.same
      }
    }
  }
}
