package sample.sending

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import sample.sending.ThermostatSupervisor.{StringMessage, SystemMessage}

object ThermostatSupervisor {
  sealed trait SystemMessage
  case object StartSendingMessages      extends SystemMessage
  case object StopSendingMessages       extends SystemMessage
  case class StringMessage(msg: String) extends SystemMessage

  def apply(): Behavior[SystemMessage] = Behaviors.setup[SystemMessage] { context => new ThermostatSupervisor(context) }
}

private class ThermostatSupervisor(context: ActorContext[SystemMessage]) extends AbstractBehavior(context) {

  import ThermostatActor._

  private val thermostatActor: ActorRef[ThermostatActor.MessageToThermostat] =
    context.spawn(ThermostatActor(), "ThermostatActor")

  override def onMessage(msg: SystemMessage): Behavior[SystemMessage] = msg match {
    case ThermostatSupervisor.StartSendingMessages =>
      thermostatActor ! CurrentTemperature(context.self)
      thermostatActor ! IncreaseTemperature(context.self, 1)
      thermostatActor ! DecreaseTemperature(context.self, 2)
      Behaviors.same
    case ThermostatSupervisor.StopSendingMessages =>
      Behaviors.stopped
    case ThermostatSupervisor.StringMessage(msg) =>
      println(s"MSG: $msg")
      Behaviors.same
  }
}
