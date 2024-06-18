package kr.cosine.mythicmobsdrop.data.drop.holder

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry.Companion.isChanged
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class DropHolder(
    val key: String,
    private val drops: MutableList<Drop> = mutableListOf()
) {

    fun <T : Drop> getDrop(clazz: KClass<T>): T {
        return drops.filterIsInstance(clazz.java).firstOrNull() ?: clazz.createInstance().apply {
            drops.add(this)
        }
    }

    fun <T : Drop> removeDrop(clazz: KClass<T>): Boolean {
        return drops.removeIf { it::class == clazz }.apply { if (this) isChanged = true }
    }

    fun getDrops(): List<Drop> = drops
}