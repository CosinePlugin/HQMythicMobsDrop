package kr.cosine.mythicmobsdrop.view.util

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.data.item.DefaultItemStack
import kr.cosine.mythicmobsdrop.extension.format

object LoreUtil {

    private val emptyLore = listOf("")
    private val removeDescriptionLore = listOf("§cShift + RMB §7▸ §f Delete from the list.")
    private const val CHANCE_LORE = "§e§l| §fProbability: §e%s"
    private val chanceDescriptionLore = listOf("§eLMB §7▸ §fSet probability.") + removeDescriptionLore

    fun getLore(baseItemStack: BaseItemStack): List<String> {
        return emptyLore + when (baseItemStack) {
            is DefaultItemStack -> removeDescriptionLore
            is ChanceItemStack -> listOf(CHANCE_LORE.format(baseItemStack.getChance().format())) +
                    emptyLore +
                    chanceDescriptionLore
        }
    }
}