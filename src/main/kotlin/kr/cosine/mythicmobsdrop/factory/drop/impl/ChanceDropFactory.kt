package kr.cosine.mythicmobsdrop.factory.drop.impl

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.drop.impl.ChanceDrop
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.factory.drop.DropFactory
import kr.cosine.mythicmobsdrop.key.DropKey
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.inventory.ItemStack

@Component
class ChanceDropFactory : DropFactory(DropKey.CHANCE) {

    override fun createDrop(itemStacks: List<ItemStack>): Drop {
        return ChanceDrop(itemStacks.map(::ChanceItemStack).toMutableList())
    }
}