package org.example.ktz.gof.chap4

import org.example.ktz.gof.chap4.adapter.AccountAdapterService
import org.example.ktz.gof.chap4.bridge.AccountBridgeService

object AccountInfoApp extends App {

  // Adapter

  println("\n\nAdapter\n\n")

  val fakeCacheRepository: FakeCacheRepository = new FakeCacheRepository
  val fakeDbRepository: FakeDbRepository = new FakeDbRepository

  import org.example.ktz.gof.chap4.adapter.RepositoryAdapter._
  val accountAdapterService: AccountAdapterService = new AccountAdapterService(fakeCacheRepository, fakeDbRepository)

  accountAdapterService.addAccount(AccountInfoDto(1, "Martin", 100))
  accountAdapterService.addAccount(AccountInfoDto(2, "Ktz", 200))

  accountAdapterService.addBudget(1, 50)
  accountAdapterService.addBudget(2, 120)

  println(accountAdapterService.getAccountInfoById(1))
  println(accountAdapterService.getAccountInfoById(2))


  // Bridge

  println("\n\nBridge\n\n")

  val accountBridgeService: AccountBridgeService = new AccountBridgeService

  accountBridgeService.addAccount(AccountInfoDto(1, "Martin", 100))
  accountBridgeService.addAccount(AccountInfoDto(2, "Ktz", 200))

  accountBridgeService.addBudget(1, 50)
  accountBridgeService.addBudget(2, 120)

  println(accountBridgeService.getAccountInfoById(1))
  println(accountBridgeService.getAccountInfoById(2))
}
