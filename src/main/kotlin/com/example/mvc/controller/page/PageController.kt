package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

// @RestController : @Contoller에 @ResponseBody가 추가된 것으로 json형태로 객체 데이터 반환
@Controller // html을 찾아서 반환
class PageController {

    // http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String{     // text "main.html"
        println("init main")
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name = "steve"
        }
        //return "main.html"
    }
}