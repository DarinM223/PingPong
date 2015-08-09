package d_m.remote

import akka.actor._
import d_m.shared.Message

class Pong(supervisor: ActorRef) extends Actor {
  def receive = {
    case Message.Ping =>
      supervisor ! Message.Pong
      sender ! Message.Pong
    case Message.Stop =>
      println("d_m.remote.Pong stopped")
      context.stop(self)
  }
}
