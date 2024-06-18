package kr.cosine.mythicmobsdrop.factory.drop.handler

import kr.cosine.mythicmobsdrop.factory.drop.DropFactory
import kr.cosine.mythicmobsdrop.factory.drop.registry.DropFactoryRegistry
import kr.hqservice.framework.global.core.component.handler.ComponentHandler
import kr.hqservice.framework.global.core.component.handler.HQComponentHandler

@ComponentHandler
class DropFactoryComponentHandler(
    private val dropFactoryRegistry: DropFactoryRegistry
) : HQComponentHandler<DropFactory> {

    override fun setup(element: DropFactory) {
        dropFactoryRegistry.registerDropFactory(element)
    }
}