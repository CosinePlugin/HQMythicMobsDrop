package kr.cosine.mythicmobsdrop.registry

import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.hqservice.framework.global.core.component.Bean

@Bean
class DropHolderRegistry {

    private val dropHolderMap = mutableMapOf<String, DropHolder>()

    fun getDropHolder(key: String): DropHolder {
        return dropHolderMap.computeIfAbsent(key) {
            DropHolder(key)
        }
    }

    fun setDropHolder(key: String, dropHolder: DropHolder) {
        dropHolderMap[key] = dropHolder
    }
}