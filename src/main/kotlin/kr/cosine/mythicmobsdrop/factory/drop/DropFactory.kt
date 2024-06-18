package kr.cosine.mythicmobsdrop.factory.drop

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.hqservice.framework.global.core.component.HQComponent
import org.bukkit.inventory.ItemStack

abstract class DropFactory(
    val key: String
) : HQComponent {

    abstract fun createDrop(itemStacks: List<ItemStack>): Drop
}