import akka.util.Timeout
import scala.concurrent.Await
import scala.util.{Success, Failure}
import akka.actor._
import akka.pattern.ask
import scala.concurrent.duration._

object PingPongTest extends App {
  val system = ActorSystem("PingPongSystem")

  implicit val timeout = Timeout(5 seconds)
  val supervisor = system.actorOf(Props[Supervisor], "supervisor")
  val pongFuture = (supervisor ? (Props[Pong], "pong")).mapTo[ActorRef]
  val pong = Await.result(pongFuture, timeout.duration)

  val pingFuture = (supervisor ? (Props(new Ping(pong)), "ping")).mapTo[ActorRef]
  val ping = Await.result(pingFuture, timeout.duration)

  ping ! Message.Start
}
