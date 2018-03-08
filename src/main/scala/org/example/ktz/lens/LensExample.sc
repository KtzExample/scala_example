import monocle._

case class Foo(a: Int, b: String, c: Boolean)

val lens1: Lens[Foo, String] = Lens[Foo, String](_.b)(b => foo => foo.copy(b = b))

val optional1: Optional[Foo, String] = Optional[Foo, String] { f =>
  Some(lens1.get(f))
}(b => foo => foo.copy(b = b))

val optional2: Optional[Foo, String] = lens1.asOptional;