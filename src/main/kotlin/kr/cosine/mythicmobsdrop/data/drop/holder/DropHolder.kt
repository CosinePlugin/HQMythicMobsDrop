package kr.cosine.mythicmobsdrop.data.drop.holder

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class DropHolder(
    val key: String,
    private val drops: MutableList<Drop> = mutableListOf()
) {

    fun <T : Drop> getDrop(clazz: KClass<T>): T {
        return (drops.filterIsInstance(clazz.java).firstOrNull() ?: clazz.createInstance()).apply {
            drops.add(this)
        }
    }
}