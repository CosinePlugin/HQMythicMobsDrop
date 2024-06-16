package kr.cosine.mythicmobsdrop.data.drop

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import org.bukkit.inventory.ItemStack

data class Chance(
    override val baseItemStacks: MutableList<ChanceItemStack> = mutableListOf()
) : Drop {

    override val title = "확률 설정"

    override fun getBaseItemStack(itemStack: ItemStack): BaseItemStack = ChanceItemStack.of(itemStack)

    override fun addBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.add(baseItemStack as ChanceItemStack)
    }

    override fun removeBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.remove(baseItemStack)
    }
}