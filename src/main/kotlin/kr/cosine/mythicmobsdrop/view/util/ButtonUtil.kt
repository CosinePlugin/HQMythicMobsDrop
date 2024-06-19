package kr.cosine.mythicmobsdrop.view.util

import kr.cosine.mythicmobsdrop.extension.playButtonSound
import kr.cosine.mythicmobsdrop.extension.setLeftClickFunction
import kr.cosine.mythicmobsdrop.view.PageView
import kr.cosine.mythicmobsdrop.view.data.Slot
import kr.hqservice.framework.inventory.button.HQButton
import kr.hqservice.framework.inventory.button.HQButtonBuilder
import org.bukkit.Material

object ButtonUtil {

    private const val BEFORE_PAGE_BUTTON_TITLE = "§c이전 페이지로 이동"
    private const val NEXT_PAGE_BUTTON_TITLE = "§a다음 페이지로 이동"
    private const val EMPTY = "§f"

    private const val BEFORE_PAGE_NOT_EXIST_MESSAGE = "§c이전 페이지가 존재하지 않습니다."
    private const val NEXT_PAGE_NOT_EXIST_MESSAGE = "§c다음 페이지가 존재하지 않습니다."

    fun getBeforePageButton(): HQButton {
        return HQButtonBuilder(Material.RED_STAINED_GLASS_PANE).apply {
            setDisplayName(BEFORE_PAGE_BUTTON_TITLE)
            setLeftClickFunction { event ->
                val player = event.getWhoClicked()
                val container = event.getContainer() as PageView
                player.playButtonSound()
                if (container.page == 0) {
                    player.sendMessage(BEFORE_PAGE_NOT_EXIST_MESSAGE)
                    return@setLeftClickFunction
                }
                container.page--
                event.getContainer().refresh()
            }
        }.build()
    }

    fun getNextPageButton(): HQButton {
        return HQButtonBuilder(Material.LIME_STAINED_GLASS_PANE).apply {
            setDisplayName(NEXT_PAGE_BUTTON_TITLE)
            setLeftClickFunction { event ->
                val player = event.getWhoClicked()
                val container = event.getContainer() as PageView
                player.playButtonSound()
                if (container.baseItemStacks.size <= (container.page + 1) * Slot.ITEM_SIZE) {
                    player.sendMessage(NEXT_PAGE_NOT_EXIST_MESSAGE)
                    return@setLeftClickFunction
                }
                container.page++
                container.refresh()
            }
        }.build()
    }

    fun getBackgroundButton(): HQButton {
        return HQButtonBuilder(Material.WHITE_STAINED_GLASS_PANE)
            .setDisplayName(EMPTY)
            .build()
    }
}