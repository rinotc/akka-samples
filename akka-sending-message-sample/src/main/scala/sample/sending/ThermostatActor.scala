package sample.sending

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import sample.sending.ThermostatActor.MessageToThermostat
import sample.sending.ThermostatSupervisor.{StringMessage, SystemMessage}

object ThermostatActor {
  sealed trait MessageToThermostat {
    def sender: ActorRef[SystemMessage]
  }

  final case class CurrentTemperature(sender: ActorRef[SystemMessage])               extends MessageToThermostat
  final case class IncreaseTemperature(sender: ActorRef[SystemMessage], amount: Int) extends MessageToThermostat
  final case class DecreaseTemperature(sender: ActorRef[SystemMessage], amount: Int) extends MessageToThermostat

  def apply(): Behavior[MessageToThermostat] = Behaviors.setup { context => new ThermostatActor(context) }
}

private class ThermostatActor(context: ActorContext[MessageToThermostat]) extends AbstractBehavior(context) {

  private var currentTemp = 72

  override def onMessage(msg: MessageToThermostat): Behavior[MessageToThermostat] = {
    msg match {
      case ThermostatActor.CurrentTemperature(sender) =>
        sendReply(sender)
        Behaviors.same
      case ThermostatActor.IncreaseTemperature(sender, amount) =>
        currentTemp += amount
        sendReply(sender)
        Behaviors.same
      case ThermostatActor.DecreaseTemperature(sender, amount) =>
        currentTemp -= amount
        sendReply(sender)
        Behaviors.same
    }
  }

  private def sendReply(sender: ActorRef[SystemMessage]): Unit = {
    val msg = s"Thermostat: Temperature is $currentTemp degrees"
    println(msg)
    sender ! StringMessage(msg)
  }
}
