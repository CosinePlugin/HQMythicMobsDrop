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
        return dropFactoryMap[key] ?: throw UnsupportedOperationException("${key}에 대한 DropFactory 생성을 지원하지 않습니다.")
    }
}