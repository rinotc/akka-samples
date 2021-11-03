package sample.sending

import akka.actor.typed.ActorSystem
import sample.sending.ThermostatSupervisor.{StartSendingMessages, StopSendingMessages}

object ThermostatApp {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem(ThermostatSupervisor(), "ThermostatSupervisor")
    actorSystem ! StartSendingMessages
    Thread.sleep(1_000)
    actorSystem ! StopSendingMessages

    Thread.sleep(500)
    actorSystem.terminate()
  }
}
