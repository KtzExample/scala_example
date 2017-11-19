package org.example.ktz.gof.chap4

import org.example.ktz.gof.chap4.AccountInfoDto.UserId


case class AccountInfoDto(userId: UserId, userName: String, budget: Long)

object AccountInfoDto {
  type UserId = Long
}