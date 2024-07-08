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
        val baseItemStacks = drop.baseItemStacks
        val emptyBaseItemStackChance = 100 - baseItemStacks.sumOf { it.getChance() }
        val emptyBaseItemStack = ChanceItemStack.of(ItemStack(Material.AIR), emptyBaseItemStackChance)
        val itemStack = (baseItemStacks + emptyBaseItemStack).minByOrNull {
            -ln(random.nextDouble()) / it.getChance()
        }?.toOriginalItemStack() ?: return emptyList()
        if (itemStack.type.isAir) return emptyList()
        return listOf(itemStack)
    }
}