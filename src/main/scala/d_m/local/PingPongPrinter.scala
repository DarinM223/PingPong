package d_m.local

import akka.actor._
import d_m.shared.Message

class PingPongPrinter extends Actor {
  def receive = {
    case Message.Ping => println("ping")
    case Message.Pong => println(" pong")
  }
}
