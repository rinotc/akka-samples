package com.github.rinotc.akka.scheduler.sample

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors, TimerScheduler}
import akka.actor.typed.{ActorRef, Behavior}
import Buncher.Command

import scala.concurrent.duration.FiniteDuration

/**
 * @see https://doc.akka.io/docs/akka/current/typed/interaction-patterns.html#scheduling-messages-to-self
 */
object Buncher {

  sealed trait Command
  final case class ExcitingMessage(message: String) extends Command
  final case class Batch(messages: Vector[Command])
  private case object Timeout extends Command
  private case object TimerKey

  def apply(target: ActorRef[Batch], after: FiniteDuration, maxSize: Int): Behavior[Command] = {
    Behaviors.withTimers(timers => new Buncher(timers, target, after, maxSize).idle())
  }
}

class Buncher(
    timers: TimerScheduler[Buncher.Command],
    target: ActorRef[Buncher.Batch],
    after: FiniteDuration,
    maxSize: Int
) {
  import Buncher._

  private def idle(): Behavior[Command] = {
    Behaviors.receiveMessage[Command] { message =>
      timers.startSingleTimer(TimerKey, Timeout, after)
      active(Vector(message))
    }
  }

  def active(buffer: Vector[Command]): Behavior[Command] = {
    Behaviors.receiveMessage[Command] {
      case Timeout =>
        target ! Batch(buffer)
        idle()
      case m =>
        val newBuffer = buffer :+ m
        if (newBuffer.size == maxSize) {
          timers.cancel(TimerKey)
          target ! Batch(newBuffer)
          idle()
        } else
          active(newBuffer)
    }
  }
}
