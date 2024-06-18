package kr.cosine.mythicmobsdrop.factory.item.impl

import kr.cosine.mythicmobsdrop.data.drop.impl.DefaultDrop
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.factory.item.BaseItemStackFactory
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.inventory.ItemStack

@Component
class DefaultItemStackFactory : BaseItemStackFactory<DefaultDrop> {

    override fun getItemStacks(drop: DefaultDrop): List<ItemStack> {
        return drop.baseItemStacks.map(BaseItemStack::toOriginalItemStack)
    }
}