package org.example.ktz.gof.chap4

import org.example.ktz.gof.chap4.adapter.AccountAdapterService
import org.example.ktz.gof.chap4.bridge.AccountBridgeService
import org.example.ktz.gof.chap4.composite.{FakeAccountComposite, Repository}

object AccountInfoApp extends App {

  val martinAccountInfo: AccountInfoDto = AccountInfoDto(1, "Martin", 100)
  val ktzAccountInfo: AccountInfoDto = AccountInfoDto(2, "Ktz", 200)

  // Adapter

  println("\n\nAdapter\n\n")

  val fakeCacheRepository: FakeCacheRepository = new FakeCacheRepository
  val fakeDbRepository: FakeDbRepository = new FakeDbRepository

  import org.example.ktz.gof.chap4.adapter.RepositoryAdapter._
  val accountAdapterService: AccountAdapterService = new AccountAdapterService(fakeCacheRepository, fakeDbRepository)

  accountAdapterService.addAccount(martinAccountInfo)
  accountAdapterService.addAccount(ktzAccountInfo)

  accountAdapterService.addBudget(1, 50)
  accountAdapterService.addBudget(2, 120)

  println(accountAdapterService.getAccountInfoById(1))
  println(accountAdapterService.getAccountInfoById(2))


  // Bridge

  println("\n\nBridge\n\n")

  val accountBridgeService: AccountBridgeService = new AccountBridgeService

  accountBridgeService.addAccount(martinAccountInfo)
  accountBridgeService.addAccount(ktzAccountInfo)

  accountBridgeService.addBudget(1, 50)
  accountBridgeService.addBudget(2, 120)

  println(accountBridgeService.getAccountInfoById(1))
  println(accountBridgeService.getAccountInfoById(2))


  // Composite

  println("\n\nComposite\n\n")

  val accountComposite: Repository = new FakeAccountComposite(
    new composite.FakeCacheRepository,
    new composite.FakeDbRepository
  )

  accountComposite.addAccount(martinAccountInfo)
  accountComposite.addAccount(ktzAccountInfo)

  accountComposite.addMoney(1, 50)
  accountComposite.addMoney(2, 120)

  println(accountComposite.getAccountInfoById(1))
  println(accountComposite.getAccountInfoById(2))
}
