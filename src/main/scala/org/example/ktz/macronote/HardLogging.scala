package org.example.ktz.macronote

import scala.reflect.macros.blackbox

object HardLogging {
  // needs
  // [C1] xxxx
  def log(message: String): Unit = macro HardLoggingImpl.logImpl
}

// white box
// black box
class HardLoggingImpl(val c: blackbox.Context) {
  import c.universe._
  // filename
  // line
  // method name
  def logImpl(message: c.Expr[String]): c.Tree = {
    val pos: c.universe.Position = c.macroApplication.pos
    val source = pos.source.file.name
    val line = pos.line
    val methodName = getMethodSymbol().asMethod.name.toString

    q"""println($source + ":" + $line + " - " + $methodName + ": [C1] " + $message)"""
  }

  private def getMethodSymbol(): c.Symbol = {
    def getMethodSymbolRecursively(sym: Symbol): Symbol = {
      if (sym == null || sym == NoSymbol || sym.owner == sym)
        c.abort(
          c.enclosingPosition,
          "This memoize block does not appear to be inside a method. " +
            "Memoize blocks must be placed inside methods, so that a cache key can be generated."
        )
      else if (sym.isMethod)
        sym
      else
        getMethodSymbolRecursively(sym.owner)
    }

    getMethodSymbolRecursively(c.internal.enclosingOwner)
  }
}
