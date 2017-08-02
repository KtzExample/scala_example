trait AuthService {
  def isLogged(name: String): Boolean
}

class AuthServiceChar3 extends AuthService{
  override def isLogged(name: String): Boolean = name.length == 3
}

class AuthServiceChar5 extends AuthService{
  override def isLogged(name: String): Boolean = name.length == 5
}

trait UserService {
  def greet(name: String, isLogged: Boolean): String
}

class UserServiceDefaultUser extends UserService{
  override def greet(name: String, isLogged: Boolean): String = {
    val actualName = if(isLogged) name else "User"

    s"Hello $actualName"
  }
}

class UserServiceNoDefault extends UserService{
  override def greet(name: String, isLogged: Boolean): String = {
    if(isLogged) s"Hello $name" else "No authorization"
  }
}

case class Environment(userName: String, userService: UserService, authService: AuthService)

import cats.data.Reader

def isLoggedUser: Reader[Environment, Boolean] = Reader[Environment, Boolean] { env =>
  env.authService.isLogged(env.userName)
}

def greetUser(logged: Boolean): Reader[Environment, String] = Reader[Environment, String] { env =>
  env.userService.greet(env.userName, logged)
}

//  In Intellij if you use auto complete, Kleisli[Id, Environment, String] will be written. I will write post later
val resultR: Reader[Environment, String] = for {
  logged <- isLoggedUser
  greeting <- greetUser(logged)
} yield greeting

val environment1 = Environment("Joe", new UserServiceDefaultUser, new AuthServiceChar3)

println(resultR.run(environment1))

val environment2 = Environment("Joe", new UserServiceNoDefault, new AuthServiceChar5)

println(resultR.run(environment2))