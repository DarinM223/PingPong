import akka.actor._

class Pong(supervisor: ActorRef) extends Actor {
  def receive = {
    case Message.Ping =>
      supervisor ! Message.Pong
      sender ! Message.Pong
    case Message.Stop =>
      println("Pong stopped")
      context.stop(self)
  }
}
