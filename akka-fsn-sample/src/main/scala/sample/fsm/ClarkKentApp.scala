package sample.fsm

import akka.actor.typed.ActorSystem
import sample.fsm.ClarkKent._

object ClarkKentApp extends App {
  val actorSystem = ActorSystem(ClarkKent(), "SupermanSystem")

  actorSystem ! WorkAtNewsPaper

  // これらは失敗する。システムの状態が間違っているため
  actorSystem ! Fly
  actorSystem ! SavePeople
  actorSystem ! BecomeClarkKent

  // これは動作する
  actorSystem ! PutOnGlasses

  // これならば動作する
  actorSystem ! BecomeSuperman
  actorSystem ! Fly
  actorSystem ! SavePeople
  actorSystem ! BecomeClarkKent

  Thread.sleep(1000)
  actorSystem.terminate()
}
