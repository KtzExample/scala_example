package org.example.ktz.gof

package object chap3 {
  sealed trait Direction
  object North extends Direction
  object South extends Direction
  object East extends Direction
  object West extends Direction

  trait MapSite {
    def Enter(): Unit
  }
}
