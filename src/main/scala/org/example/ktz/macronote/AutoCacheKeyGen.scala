package org.example.ktz.macronote

import scala.reflect.macros.blackbox

object AutoCacheKeyGen {
  def memoize[V](f: => V): V = macro AutoCacheKeyGenImpl.memoizeImpl[V]
}

object InMemoryCache {
  val cache: collection.mutable.Map[String, Any] = collection.mutable.Map.empty

  def getOrUpdate[V](key: String, f: => V): V = {
    cache.get(key) match {
      case Some(v) => v.asInstanceOf[V]
      case None =>
        cache.put(key, f)
        f
    }
  }
}

class AutoCacheKeyGenImpl(val c: blackbox.Context) {
  import c.universe._
  def memoizeImpl[V: c.WeakTypeTag](f: c.Tree): c.Tree = {
    val className = getFullClassName(getClassSymbol())
    val methodName = getMethodSymbol().name.toString
    val methodParams = ""
    val key = s"$className-$methodName-$methodParams"

    q"""
       _root_.org.example.ktz.macronote.InMemoryCache.getOrUpdate($key, $f)
     """
  }

  private def getClassSymbol(): c.Symbol = {
    def getClassSymbolRecursively(sym: Symbol): Symbol = {
      if (sym == null)
        c.abort(c.enclosingPosition, "Encountered a null symbol while searching for enclosing class")
      else if (sym.isClass || sym.isModule)
        sym
      else
        getClassSymbolRecursively(sym.owner)
    }

    getClassSymbolRecursively(c.internal.enclosingOwner)
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

  private def getFullClassName(classSymbol: c.Symbol): c.Tree = {
    val className = classSymbol.fullName
    // return a Tree
    q"$className"
  }
}