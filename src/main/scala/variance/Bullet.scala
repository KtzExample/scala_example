package variance

/**
  * Created by ktz on 16. 9. 8.
  */
trait Bullet
class NormalBullet extends Bullet
class ExplosiveBullet extends Bullet

final class AmmoMagazine[+A <: Bullet](private[this] var bullets: List[A]) {

  def hasBullets: Boolean = !bullets.isEmpty

  def giveNextBullet(): Option[A] =
    bullets match {
      case Nil => {
        None
      }
      case t :: ts => {
        bullets = ts
        Some(t)
      }
    }
}

object AmmoMagazine {
  def newNormalBulletsMag : AmmoMagazine[NormalBullet] = new AmmoMagazine[NormalBullet](List.fill(10)(new NormalBullet))
  def newExplosiveBulletsMag : AmmoMagazine[ExplosiveBullet] = new AmmoMagazine[ExplosiveBullet](List.fill(10)(new ExplosiveBullet))
}

final class Gun(private var ammoMag: AmmoMagazine[Bullet]) {

  def reload(ammoMag: AmmoMagazine[Bullet]): Unit =
    this.ammoMag = ammoMag

  def hasAmmo: Boolean = ammoMag.hasBullets

  /** Returns Bullet that was shoot or None if there is ammo left */
  def shoot(): Option[Bullet] = ammoMag.giveNextBullet()

}

object BulletMain extends App {
  val gun = new Gun(AmmoMagazine.newNormalBulletsMag)
  // compiles because of covariant subtyping
  gun.reload(AmmoMagazine.newExplosiveBulletsMag)
}

class Holder[+T] (initialValue: Option[T]) {
  // without [this] it will not compile
  private[this] var value : Option[T] = initialValue

  def getValue = value
  def makeEmpty { value = None }
}

class C(private val x: Int) {
  override def equals(obj: Any) = obj match {
    case other: C => x == other.x
    case _ => false
  }
}