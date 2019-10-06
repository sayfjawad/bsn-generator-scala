package nl.multicode.bsn

import akka.actor.{ Actor, ActorLogging, Props }

object BsnServiceActor {
  final case class ActionPerformed(description: String)
  final case class GetBsn()
  final case class ValidateBsn(bsn: String)

  def props: Props = Props[BsnServiceActor]
}

class BsnServiceActor extends Actor with ActorLogging {
  import BsnServiceActor._

  def receive: Receive = {
    case GetBsn =>
      sender() ! "generated BSN" //generate and return BSN
    case ValidateBsn(bsn) =>
      sender() ! "false".concat(bsn) // validate incomming BSN
  }
}
