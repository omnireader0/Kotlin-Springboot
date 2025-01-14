package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.util.LinkedMultiValueMap
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect(
            status().`is`(200)
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sssss")
        queryParams.add("age", "22")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("sssss 22")
        ).andDo(print())
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sssss")
        queryParams.add("age", "1")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
                .characterEncoding("UTF-8")
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("1")
        ).andDo(print())
    }

    @Test
    fun postTest(){

        val userRequest = UserRequest().apply {
            this.name = "sssss"
            this.age = 10
            this.phoneNumber = "010-1111-2222"
            this.address = "경기도 성남시"
            this.email = "sssss@gmail.com"
            this.createdAt = "2022-10-02 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("sssss")
        ).andExpect(
            jsonPath("\$.age").value("10")
        ).andExpect(
            jsonPath("\$.email").value("sssss@gmail.com")
        )
            .andDo(print())
    }

    @Test
    fun postFailTest(){

        val userRequest = UserRequest().apply {
            this.name = "sssss"
            this.age = -1
            this.phoneNumber = "010-1111-2222"
            this.address = "경기도 성남시"
            this.email = "sssss@gmail.com"
            this.createdAt = "2020-10-02 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().`is`(400)
        )
            .andDo(print())
    }
}