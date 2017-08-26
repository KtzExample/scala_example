sealed trait Bool {
  type && [b <: Bool] <: Bool
  type || [b <: Bool] <: Bool
  type ifElse[B, T <: B, f <:  B] <: B
}

object True extends Bool {
  override type && [b <: Bool] = b
  override type || [b <: Bool] = True.type
  override type ifElse[B, T <: B, F <:  B] = T
}

object False extends Bool {
  override type && [b <: Bool] = False.type
  override type || [b <: Bool] = b
  override type ifElse[B, T <: B, F <:  B] = F
}

sealed trait Nat {
  type N <: Nat
  type + [b <: Nat] <: Nat
}

class Zero extends Nat {
  override type N = Zero
  override type + [b <: Nat] = b
}

case class Succ[M <: Nat]() extends Nat{
  override type N = Succ[M]
}

trait Sum[A <: Nat, B <: Nat] {
  type Out <: Nat
}