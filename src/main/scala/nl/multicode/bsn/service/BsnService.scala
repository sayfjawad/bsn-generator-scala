package nl.multicode.bsn.service

import nl.multicode.bsn.validate.BsnElfproef

import scala.concurrent.forkjoin.ThreadLocalRandom

class BsnService(bsnElfproef: BsnElfproef) {
  private val MAX = 999999999
  private val MIN = 100000000


  def generateRandomBsnNummers(): String = {
    while (true) {
      val randomNumber = ThreadLocalRandom.current.nextInt(MIN, MAX)
      if (isValidBsn(randomNumber.toString)) {
        return randomNumber.toString
      }
    }
    null
  }

  def isValidBsn(bsn: String): Boolean = {
    bsnElfproef.isElfproef(bsn)
  }
}
