package kr.cosine.mythicmobsdrop.factory.drop.impl

import kr.cosine.mythicmobsdrop.data.drop.impl.DefaultDrop
import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.item.DefaultItemStack
import kr.cosine.mythicmobsdrop.factory.drop.DropFactory
import kr.cosine.mythicmobsdrop.key.DropKey
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.inventory.ItemStack

@Component
class DefaultDropFactory : DropFactory(DropKey.DEFAULT) {

    override fun createDrop(itemStacks: List<ItemStack>): Drop {
        return DefaultDrop(itemStacks.map(::DefaultItemStack).toMutableList())
    }
}