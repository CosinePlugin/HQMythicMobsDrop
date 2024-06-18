package kr.cosine.mythicmobsdrop.factory.item.impl

import kr.cosine.mythicmobsdrop.data.drop.impl.ChanceDrop
import kr.cosine.mythicmobsdrop.factory.item.BaseItemStackFactory
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.inventory.ItemStack
import kotlin.math.ln
import kotlin.random.Random

@Component
class ChanceItemStackFactory : BaseItemStackFactory<ChanceDrop> {

    private val random: Random = Random

    override fun getItemStacks(drop: ChanceDrop): List<ItemStack> {
        val itemStack = drop.baseItemStacks.minByOrNull {
            -ln(random.nextDouble()) / it.getChance()
        }?.toOriginalItemStack() ?: return emptyList()
        return listOf(itemStack)
    }
}