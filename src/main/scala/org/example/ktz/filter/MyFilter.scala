package org.example.ktz.filter


object MyFilter {
  trait Service[A, B] {
    def apply(a: A): B
  }

  trait Filter[A, B] { self =>
    def apply(service: Service[A, B]): Service[A, B]

    def andThen(next: Filter[A, B]): Filter[A, B] = {
      val selfApply: Service[A, B] => Service[A, B] = self.apply
      val nextApply: Service[A, B] => Service[A, B] = next.apply
      val function: Service[A, B] => Service[A, B] = selfApply andThen nextApply

      new Filter[A, B] {
        override def apply(service: Service[A, B]): Service[A, B] =
          function(service)
      }
    }

    def andThen2(next: Filter[A, B]): Filter[A, B] = {
      new Filter[A, B] {
        override def apply(service: Service[A, B]): Service[A, B] = new Service[A, B] {
          override def apply(a: A): B = next(self(service))(a)
        }
      }
    }
  }

  val a = 1

  private val FilterA: Filter[Int, String] = new Filter[Int, String] {
    override def apply(service: Service[Int, String]): Service[Int, String] =
      new Service[Int, String] {
        override def apply(a: Int): String = {
          println(s"Before filterA: $a")
          val res = service(a)
          println(s"After filterA: $a")
          res
        }
    }
  }

  private val FilterB: Filter[Int, String] = new Filter[Int, String] {
    override def apply(service: Service[Int, String]): Service[Int, String] =
      new Service[Int, String] {
        override def apply(b: Int): String = {
          println(s"Before filterB: $b")
          val res = service(b)
          println(s"After filterB: $b")
          res
        }
      }
  }


  private val filterAB: Filter[Int, String] = FilterA.andThen(FilterB)
  val service = new Service[Int, String] {
    override def apply(a: Int): String = a.toString
  }

  filterAB(service)(10)
}

object MyFilter2 extends App{
  trait MyService[A, B] {
    def apply(a: A): B
  }

  trait MyFilter[A, B] { self =>
    def apply(a: A, service: MyService[A, B]): B

    def andThen(next: MyFilter[A, B]): MyFilter[A, B] = {
      new MyFilter[A, B] {
        override def apply(a: A, service: MyService[A, B]): B = next.apply(a, new MyService[A, B] {
          override def apply(a: A): B = self.apply(a, service)
        })
      }
    }
  }


  val a = 1

  private val FilterA: MyFilter[Int, String] = new MyFilter[Int, String] {
    override def apply(a: Int, service: MyService[Int, String]): String = {
      println(s"Before filterA: $a")
      val res = service(a)
      println(s"After filterA: $a")
      res
    }
  }

  private val FilterB: MyFilter[Int, String] = new MyFilter[Int, String] {
    override def apply(b: Int, service: MyService[Int, String]): String = {
      println(s"Before filterB: $b")
      val res = service(b)
      println(s"After filterB: $b")
      res
    }
  }


  private val filterAB: MyFilter[Int, String] = FilterA.andThen(FilterB)
  val service = new MyService[Int, String] {
    override def apply(a: Int): String = a.toString
  }

  filterAB.apply(10, service)(10)
}