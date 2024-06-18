package kr.cosine.mythicmobsdrop.factory.item.handler

import kr.cosine.mythicmobsdrop.factory.item.BaseItemStackFactory
import kr.cosine.mythicmobsdrop.factory.item.registry.BaseItemStackFactoryRegistry
import kr.hqservice.framework.global.core.component.handler.ComponentHandler
import kr.hqservice.framework.global.core.component.handler.HQComponentHandler

@ComponentHandler
class BaseItemStackFactoryComponentHandler(
    private val baseItemStackFactoryRegistry: BaseItemStackFactoryRegistry
) : HQComponentHandler<BaseItemStackFactory<*>> {

    override fun setup(element: BaseItemStackFactory<*>) {
        baseItemStackFactoryRegistry.registerBaseItemStackFactory(element)
    }
}