package com.example.mvc.controller.get;

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetApiController {

    @GetMapping("/hello")
    fun hello(): String{
        return "hello kotlin"
    }

    // 옛날 방식
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String{
        println("${name}, ${age}")
        return name+" "+age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value="name") _name: String, @PathVariable age: Int): String{
        val name = "sdf"
        println("${_name}, ${age}")
        return _name+" "+age
    }

    @GetMapping("/get-mapping/query-param")      // ?key=value&key=value
    fun queryParam(
        @RequestParam name: String,
        @RequestParam age: Int
    ):String{
        println("${name}, ${age}")
        return name+" "+age
    }

    // 쿼리파라미터가 많다 -> 객체로 해보자
    // RestController 선언하면 리턴의 타입이 객체이면, json 형태로 바뀐다. objectmapper가 json으로 바꾸기 때문
    // 주소 url에 하이픈 안됨(코틀린 변수 선언할때도 마찬가지), 코틀린은 request parameter 만들 때 카멜 케이스 안씀. phonenumber.. 식으로 가능
    // 다만, pathvariable에 value를 줘서 phone-number 이런식으로 사용하면 됨
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    // 맵으로 받기 : 위와 동일한 결과
    // {name=omm, age=20, email=omm@gmail.com, address=address-}  key-value
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        return map
    }
}