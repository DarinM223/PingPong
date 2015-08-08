import akka.actor.{Props, Actor}

class Supervisor extends Actor {
  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._
  import scala.concurrent.duration._

  // Limit maximum number of restarts due to errors to 10
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Exception => Restart // always restart
    }

  def receive = {
    case (p: Props, name: String) => sender ! context.actorOf(p, name)
  }
}
