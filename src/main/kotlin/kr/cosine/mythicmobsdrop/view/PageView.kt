package kr.cosine.mythicmobsdrop.view

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.hqservice.framework.inventory.container.HQContainer

abstract class PageView(
    title: String
) : HQContainer(54, title) {

    abstract var page: Int

    abstract val baseItemStacks: List<BaseItemStack>
}