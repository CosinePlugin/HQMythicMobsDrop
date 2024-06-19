package kr.cosine.mythicmobsdrop.factory.item.registry

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.factory.item.BaseItemStackFactory
import kr.hqservice.framework.global.core.component.Bean
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.jvmErasure

@Bean
class BaseItemStackFactoryRegistry {

    private val baseItemStackFactoryMap = mutableMapOf<KClassifier, BaseItemStackFactory<*>>()

    fun registerBaseItemStackFactory(baseItemStackFactory: BaseItemStackFactory<*>) {
        val classifier = baseItemStackFactory::class.supertypes.first {
            it.isSubtypeOf(BaseItemStackFactory::class.starProjectedType)
        }.arguments.first().type!!.jvmErasure
        baseItemStackFactoryMap[classifier] = baseItemStackFactory
    }

    @Suppress("unchecked_cast")
    fun <T : Drop> getBaseItemStackFactory(clazz: KClass<T>): BaseItemStackFactory<T> {
        return baseItemStackFactoryMap[clazz] as? BaseItemStackFactory<T>
            ?: throw UnsupportedOperationException("There is no support for creating BaseItemStackFactory for ${clazz.simpleName}.")
    }
}