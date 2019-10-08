package nl.multicode.bsn.validate

import java.util.concurrent.atomic.AtomicInteger

trait ElfProef {
  def isElfproef(nummer: String): Boolean

  def isElfProef(bsn: String, multipliers: Array[Int]): Boolean = {
    val digits = getDigits(bsn)
    val uitkomst = 0
    val sum = new AtomicInteger(0)

    for (i <- multipliers.indices) {
      sum.addAndGet(digits(i) * multipliers(i))
    }
    sum.get % 11 == uitkomst
  }

  def getDigits(number: String): Array[Int] = {
    val digitArray = new Array[Int](number.length)
    val numberCharArray = number.toCharArray
    var i = 0
    for (i <- number.indices) {
      digitArray(i) = Character.getNumericValue(numberCharArray(i))
    }
    digitArray
  }
}
