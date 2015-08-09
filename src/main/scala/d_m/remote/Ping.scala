package d_m.remote

import akka.actor._
import d_m.shared.Message

class Ping(pong: ActorRef, supervisor: ActorRef) extends Actor {

  // Internal count for incrementing
  var count = 0

  def incrementAndPrint {
    count += 1
    supervisor ! Message.Ping
  }

  override def postRestart(reason: Throwable) = {
    super.postRestart(reason)
    println("Restarted!")
    pong ! Message.Ping
  }

  def receive = {
    case Message.Start =>
      incrementAndPrint
      pong ! Message.Ping
    case Message.Pong =>
      incrementAndPrint
      if (count > 99) {
        throw new Exception("Hello!")
      } else {
        sender ! Message.Ping
      }
    case Message.Stop =>
      println("d_m.remote.Ping stopped")
      context.stop(self)
  }
}
