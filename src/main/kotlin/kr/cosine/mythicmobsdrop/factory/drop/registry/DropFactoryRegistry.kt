package kr.cosine.mythicmobsdrop.factory.drop.registry

import kr.cosine.mythicmobsdrop.factory.drop.DropFactory
import kr.hqservice.framework.global.core.component.Bean

@Bean
class DropFactoryRegistry {

    private val dropFactoryMap = mutableMapOf<String, DropFactory>()

    fun registerDropFactory(dropFactory: DropFactory) {
        dropFactoryMap[dropFactory.key] = dropFactory
    }

    fun getDropFactory(key: String): DropFactory {
        return dropFactoryMap[key] ?: throw UnsupportedOperationException("There is no support for creating DropFactory for $key.")
    }
}