package nl.multicode.bsn.validate

object BsnElfproef {
  private val BSN_ONDNR_MULTIPLIERS = Array(9, 8, 7, 6, 5, 4, 3, 2, -1)
  private val VALID_BSN_REGEX = "[0-9]{9}"
}

class BsnElfproef extends ElfProef {

  override def isElfproef(bsn: String): Boolean = {
    if (bsn.matches(BsnElfproef.VALID_BSN_REGEX)) {
      return isElfProef(bsn, BsnElfproef.BSN_ONDNR_MULTIPLIERS)
    }
    false
  }

}
