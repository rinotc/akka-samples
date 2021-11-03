package sample.oopstyle

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import sample.oopstyle.Tom.Message

/**
 * @note Akka Typed アクター は OOP スタイルと、FPスタイルのどちらでも書ける。以下はOOPスタイル。
 *       OOPスタイルは、クラスとコンパニオンオブジェクトの両方を使って書く。
 *       <ul>
 *         <li>
 *           コンパニオンオブジェクトでは
 *           <ol type="a">
 *             <li>アクターがハンドリングするメッセージを定義する</li>
 *             <li>ファクトリメソッド `apply` を定義する</li>
 *           </ol>
 *         </li>
 *         <li>Akkaの [[AbstractBehavior]] クラスを継承して、[[AbstractBehavior.onMessage]] メソッドを実装する</li>
 *         <li>
 *           クラスは `private` で定義する。これにより、コンストラクタにアクセスできなくなり、
 *           呼び出しもとは必ずコンパニオンオブジェクトの`apply` メソッドを使わなければならなくなる
 *         </li>
 *       </ul>
 */
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
