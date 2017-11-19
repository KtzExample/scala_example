package org.example.ktz.gof.chap4

object AccountInfoApp extends App {
  val fakeCacheRepository: FakeCacheRepository = new FakeCacheRepository
  val fakeDbRepository: FakeDbRepository = new FakeDbRepository

  import org.example.ktz.gof.chap4.adapter.RepositoryAdapter._
  val accountService: AccountService = new AccountService(fakeCacheRepository, fakeDbRepository)

  accountService.addAccount(AccountInfoDto(1, "Martin", 100))
  accountService.addAccount(AccountInfoDto(2, "Ktz", 200))

  accountService.addBudget(1, 50)
  accountService.addBudget(2, 120)

  println(accountService.getAccountInfoById(1))
  println(accountService.getAccountInfoById(2))
}
