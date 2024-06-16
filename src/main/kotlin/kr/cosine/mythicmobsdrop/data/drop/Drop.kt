package kr.cosine.mythicmobsdrop.data.drop

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import org.bukkit.inventory.ItemStack

sealed interface Drop {

    val title: String

    val baseItemStacks: List<BaseItemStack>

    fun getBaseItemStack(itemStack: ItemStack): BaseItemStack

    fun addBaseItemStack(baseItemStack: BaseItemStack)

    fun removeBaseItemStack(baseItemStack: BaseItemStack)
}