package kr.cosine.mythicmobsdrop

import org.junit.jupiter.api.Test
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.jvmErasure

class Test {

    class Korea : Water<Double>, Country<Int>

    interface Country<T : Number>

    interface Water<T : Number>

    @Test
    fun classifier_test() {
        val clazz = Korea()::class.supertypes.first {
            it.isSubtypeOf(Water::class.starProjectedType)
        }.arguments.firstOrNull()?.type?.jvmErasure
        println("clazz: $clazz")
    }
}