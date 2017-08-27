implicit val a: Int = 1
implicit val b: String = "Martin"

def Hello(implicit a : Int, b : String): String = s"Hello! $a, $b"

println(Hello)