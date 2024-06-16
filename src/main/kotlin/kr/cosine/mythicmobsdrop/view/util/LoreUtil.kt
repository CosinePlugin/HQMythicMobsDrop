package kr.cosine.mythicmobsdrop.view.util

import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.data.item.DefaultItemStack
import kr.cosine.mythicmobsdrop.extension.format

object LoreUtil {

    private val emptyLore = listOf("")
    private val removeDescriptionLore = listOf("§c쉬프트+우클릭 §7▸ §f목록에서 삭제합니다.")
    private const val CHANCE_LORE = "§e§l| §f확률: §e%s"
    private val chanceDescriptionLore = listOf("§e좌클릭 §7▸ §f확률을 설정합니다.") + removeDescriptionLore

    fun getLore(baseItemStack: BaseItemStack): List<String> {
        return emptyLore + when (baseItemStack) {
            is DefaultItemStack -> removeDescriptionLore
            is ChanceItemStack -> listOf(CHANCE_LORE.format(baseItemStack.getChance().format())) +
                    emptyLore +
                    chanceDescriptionLore
        }
    }
}