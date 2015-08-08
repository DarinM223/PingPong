import akka.actor._

class Pong extends Actor {
  def receive = {
    case Message.Ping =>
      println(" pong")
      sender ! Message.Pong
    case Message.Stop =>
      println("Pong stopped")
      context.stop(self)
  }
}
