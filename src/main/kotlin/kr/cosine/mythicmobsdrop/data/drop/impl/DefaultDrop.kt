package kr.cosine.mythicmobsdrop.data.drop.impl

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.DefaultItemStack
import kr.cosine.mythicmobsdrop.key.DropKey
import org.bukkit.inventory.ItemStack

data class DefaultDrop(
    override val baseItemStacks: MutableList<DefaultItemStack> = mutableListOf()
) : Drop(DropKey.DEFAULT, "기본 설정") {

    override fun toBaseItemStack(itemStack: ItemStack): BaseItemStack = DefaultItemStack(itemStack)

    override fun addBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.add(baseItemStack as DefaultItemStack)
    }

    override fun removeBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.remove(baseItemStack)
    }
}