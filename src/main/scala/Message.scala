object Message {
  sealed trait Message

  case object Ping extends Message
  case object Pong extends Message
  case object Start extends Message
  case object Stop extends Message
}
