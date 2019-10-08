package nl.multicode.bsn

import akka.actor.{Actor, ActorLogging, Props}
import nl.multicode.bsn.service.BsnService
import nl.multicode.bsn.validate.BsnElfproef

object BsnServiceActor {
  final case class GetBsn()
  final case class ValidateBsn(bsn: String)

  def props: Props = Props[BsnServiceActor]
}

class BsnServiceActor extends Actor with ActorLogging {
  import BsnServiceActor._

  var bsnService : BsnService= new BsnService(new BsnElfproef)

  def receive: Receive = {
    case GetBsn =>
      sender() ! bsnService.generateRandomBsnNummers()
    case ValidateBsn(bsn) =>
      sender() ! bsnService.isValidBsn(bsn).toString
  }
}
