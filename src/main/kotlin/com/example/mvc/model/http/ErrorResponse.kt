package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse(

    @field:JsonProperty("result_code")
    var resultCode:String?=null,

    @field:JsonProperty("http_status")
    var httpStatus:String?=null,

    @field:JsonProperty("http_method")
    var httpMethod:String?=null,

    var message:String?=null,
    var path:String?=null,
    var timestamp:LocalDateTime?=null,
    var errors:MutableList<Error>?=mutableListOf()
)

data class Error(
    var field:String?=null,
    var message:String?=null,
    var value:Any?=null
)

/*
{
    "result_code" : "ok",
    "http_status" : "400",
    "message" : "요청이 잘못 되었습니다.",
    "path" : "/api~~",
    "timestamp": "2020-~~~ ",
    "errors" : [
        {
            "field": "_name",
            "message" : "~~"
        }
    ]
}
* */