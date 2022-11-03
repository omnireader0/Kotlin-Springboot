package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) //json property 일일이 하기 힘들 때 사용
data class UserRequest(
    var name: String?= null,
    var age: Int?= null,
    var email: String?= null,
    var address: String?= null,

    //@JsonProperty("phone_number")
    var phoneNumber: String?= null //phone_number
)
