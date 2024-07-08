package kr.cosine.mythicmobsdrop

import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.math.ln
import kotlin.random.Random
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

    @Test
    fun chance_test() {
        val map = mutableMapOf<String?, Double>(
            "사과" to 2.0,
            "바나나" to 5.0
        )
        val sum = 100 - map.values.sum()
        map[null] = sum
        val resultMap = mutableMapOf<String?, Int>()
        repeat(100) {
            val key = map.random()
            val value = resultMap.getOrDefault(key, 0) + 1
            resultMap[key] = value
        }
        println(resultMap)
    }

    private fun <T : Any?> Map<T, Double>.random(): T? {
        return this.entries.minByOrNull {
            -ln(Random.nextDouble()) / it.value
        }?.key
    }
}