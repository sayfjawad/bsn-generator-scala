package nl.multicode.bsn

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import nl.multicode.bsn.BsnServiceActor.{GetBsn, ValidateBsn}

import scala.concurrent.Future
import scala.concurrent.duration._

trait BsnRoutes {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[BsnRoutes])

  def bsnServiceActor: ActorRef

  implicit lazy val timeout = Timeout(5.seconds)

  lazy val bsnRoutes: Route =
    pathPrefix("bsn") {
      concat(
        path("validate" / Segment) { bsn =>
          concat(
            get {
              val validaBsn: Future[String] =
                (bsnServiceActor ? ValidateBsn(bsn)).mapTo[String]
              complete(validaBsn)
            }
          )
        },
        path("generate") {
          concat(
            get {
              val bsn: Future[String] =
                (bsnServiceActor ? GetBsn).mapTo[String]
              complete(bsn)
            }
          )
        }
      )
    }
}
