package sample.fsm

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import sample.fsm.ClarkKent.BaseBehaviors

object ClarkKent {
  sealed trait BaseBehaviors
  sealed trait ClarkKentBehaviors   extends BaseBehaviors
  final case object WorkAtNewsPaper extends ClarkKentBehaviors
  final case object PutOnGlasses    extends ClarkKentBehaviors
  final case object BecomeSuperman  extends ClarkKentBehaviors

  sealed trait SupermanBehaviors    extends BaseBehaviors
  final case object Fly             extends SupermanBehaviors
  final case object SavePeople      extends SupermanBehaviors
  final case object BecomeClarkKent extends SupermanBehaviors

  def apply(): Behaviors.Receive[BaseBehaviors] = clarkKentState()

  private def clarkKentState(): Behaviors.Receive[BaseBehaviors] = Behaviors.receiveMessagePartial[BaseBehaviors] {
    case WorkAtNewsPaper =>
      println("normalState: WorkAtNewsPaper")
      Behaviors.same
    case PutOnGlasses =>
      println("normalState: PutOnGlasses")
      Behaviors.same
    case BecomeSuperman =>
      println("normalState: BecomeSuperman")
      supermanState()
  }

  private def supermanState(): Behaviors.Receive[BaseBehaviors] = Behaviors.receiveMessagePartial {
    case Fly =>
      println("angryState: Fly")
      Behaviors.same
    case SavePeople =>
      println("angryState: SavePeople")
      Behaviors.same
    case BecomeClarkKent =>
      println("angryState: BecomeClarkKent")
      clarkKentState()
  }
}
