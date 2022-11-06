package com.example.mvc.validator

import com.example.mvc.annotation.StringFormatDateTime
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDataTimeValidator : ConstraintValidator<StringFormatDateTime, String>{

    private var pattern: String?=null

    // 어노테이션 패턴을 변수 pattern에 넣기
    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    // 정상이면 true, 아니면 false
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try{
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        }catch (e:Exception){
            false
        }
    }
}
