package kr.cosine.mythicmobsdrop.data.drop.impl

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.key.DropKey
import org.bukkit.inventory.ItemStack

data class ChanceDrop(
    override val baseItemStacks: MutableList<ChanceItemStack> = mutableListOf()
) : Drop(DropKey.CHANCE, "확률 설정") {

    override fun toBaseItemStack(itemStack: ItemStack): BaseItemStack = ChanceItemStack.of(itemStack)

    override fun addBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.add(baseItemStack as ChanceItemStack)
    }

    override fun removeBaseItemStack(baseItemStack: BaseItemStack) {
        baseItemStacks.remove(baseItemStack)
    }
}