package kr.cosine.mythicmobsdrop.data.drop

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.DefaultItemStack
import org.bukkit.inventory.ItemStack

data class Default(
    override val baseItemStacks: MutableList<DefaultItemStack> = mutableListOf()
) : Drop {

    override val title = "기본 설정"

    override fun getBaseItemStack(itemStack: ItemStack): BaseItemStack = DefaultItemStack(itemStack)

    override fun addBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.add(baseItemStack as DefaultItemStack)
    }

    override fun removeBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.remove(baseItemStack)
    }
}