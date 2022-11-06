package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDataTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

// stringformatdatetime 애노테이션 붙은 변수에 대해서 이 validator통해서 검증
@Constraint(validatedBy = [StringFormatDataTimeValidator::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER

)
@Retention(AnnotationRetention.RUNTIME) // ??
@MustBeDocumented
annotation class StringFormatDateTime (
    val pattern: String = "yyyy-MM-dd HH:mm:ss",
    val message: String = "시간 형식이 유효하지 않습니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
