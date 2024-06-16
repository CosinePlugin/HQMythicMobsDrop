package kr.cosine.mythicmobsdrop.data.item

import org.bukkit.inventory.ItemStack

class DefaultItemStack(
    private val itemStack: ItemStack
) : BaseItemStack {

    override fun getItemStack(): ItemStack = itemStack.clone()

    override fun getOriginalItemStack(): ItemStack = getItemStack()
}