package kr.cosine.mythicmobsdrop.registry

import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.hqservice.framework.global.core.component.Bean

@Bean
class DropHolderRegistry {

    private val dropHolderMap = mutableMapOf<String, DropHolder>()

    fun findDropHolder(key: String): DropHolder? = dropHolderMap[key]

    fun getDropHolder(key: String): DropHolder {
        return dropHolderMap.computeIfAbsent(key) {
            DropHolder(key)
        }
    }

    fun setDropHolder(key: String, dropHolder: DropHolder) {
        dropHolderMap[key] = dropHolder
    }

    fun getDropHolderMap(): Map<String, DropHolder> = dropHolderMap

    companion object {
        var isChanged = false
    }
}