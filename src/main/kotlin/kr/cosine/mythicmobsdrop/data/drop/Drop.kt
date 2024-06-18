package kr.cosine.mythicmobsdrop.data.drop

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import org.bukkit.inventory.ItemStack

abstract class Drop(
    val key: String,
    val title: String
) {

    abstract val baseItemStacks: List<BaseItemStack>

    abstract fun toBaseItemStack(itemStack: ItemStack): BaseItemStack

    abstract fun addBaseItemStack(baseItemStack: BaseItemStack)

    abstract fun removeBaseItemStack(baseItemStack: BaseItemStack)
}