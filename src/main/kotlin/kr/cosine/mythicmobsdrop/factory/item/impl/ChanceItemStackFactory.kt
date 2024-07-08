package kr.cosine.mythicmobsdrop.factory.item.impl

import kr.cosine.mythicmobsdrop.data.drop.impl.ChanceDrop
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.factory.item.BaseItemStackFactory
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.math.ln
import kotlin.random.Random

@Component
class ChanceItemStackFactory : BaseItemStackFactory<ChanceDrop> {

    private val random: Random = Random

    override fun getItemStacks(drop: ChanceDrop): List<ItemStack> {
        val baseItemStacks = drop.baseItemStacks.toMutableList()
        val totalChance = baseItemStacks.sumOf { it.getChance() }
        if (totalChance < 100) {
            val emptyBaseItemStackChance = 100 - totalChance
            val emptyBaseItemStack = ChanceItemStack.of(ItemStack(Material.AIR), emptyBaseItemStackChance)
            baseItemStacks.add(emptyBaseItemStack)
        }
        val itemStack = baseItemStacks.minByOrNull {
            -ln(random.nextDouble()) / it.getChance()
        }?.toOriginalItemStack() ?: return emptyList()
        if (itemStack.type.isAir) return emptyList()
        return listOf(itemStack)
    }
}