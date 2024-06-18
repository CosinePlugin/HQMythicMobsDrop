package kr.cosine.mythicmobsdrop.view

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.extension.playButtonSound
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry.Companion.isChanged
import kr.cosine.mythicmobsdrop.view.model.DropSettingViewModel
import kr.cosine.mythicmobsdrop.view.util.LoreUtil
import kr.hqservice.framework.bukkit.core.extension.editMeta
import kr.hqservice.framework.inventory.container.HQContainer
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

class DropSettingView(
    private val dropSettingViewModel: DropSettingViewModel,
    key: String,
    private val drop: Drop
) : HQContainer(54, "$key ▸ ${drop.title}") {

    private var page = 0

    private val baseItemStacks get() = drop.baseItemStacks
    private var currentBaseItemStacks = emptyList<BaseItemStack>()

    override fun initialize(inventory: Inventory) {
        inventory.clear()
        currentBaseItemStacks = baseItemStacks.drop(ITEM_SIZE * page).take(ITEM_SIZE)
        itemSlots.forEach { index ->
            if (index >= currentBaseItemStacks.size) return
            val baseItemStack = currentBaseItemStacks[index]
            val itemStack = baseItemStack.toItemStack().editMeta {
                lore = LoreUtil.getLore(baseItemStack)
            }
            inventory.setItem(index, itemStack)
        }
    }

    override fun onClick(event: InventoryClickEvent) {
        if (event.clickedInventory == null) return
        val player = event.whoClicked as Player
        val slot = event.rawSlot
        if (slot <= MAX_SLOT) {
            clickTopInventory(event, player)
        } else {
            clickBottomInventory(event, player)
        }
    }

    private val InventoryClickEvent.clickedBaseItemStack get() = currentBaseItemStacks.getOrNull(rawSlot)

    private fun clickTopInventory(event: InventoryClickEvent, player: Player) {
        val slot = event.rawSlot
        val click = event.click
        when (click) {
            ClickType.SHIFT_RIGHT -> {
                when (slot) {
                    BEFORE_PAGE_SLOT -> {
                        player.playButtonSound()
                        if (page == 0) return
                        page--
                        refresh()
                    }

                    NEXT_PAGE_SLOT -> {
                        player.playButtonSound()
                        if (baseItemStacks.size <= (page + 1) * ITEM_SIZE) return
                        page++
                        refresh()
                    }

                    in itemSlots -> {
                        val baseItemStack = event.clickedBaseItemStack ?: return
                        player.playButtonSound()
                        drop.removeBaseItemStack(baseItemStack)
                        isChanged = true
                        refresh()
                    }
                }
            }

            ClickType.LEFT -> {
                val baseItemStack = event.clickedBaseItemStack ?: return
                if (baseItemStack !is ChanceItemStack) return
                player.playButtonSound()
                ChanceDropSettingView.open(
                    player,
                    baseItemStack,
                    { isChanged = true },
                    { reopen(player) }
                )
            }

            else -> {}
        }
    }

    private fun reopen(player: Player) {
        dropSettingViewModel.later {
            refresh()
            open(player)
        }
    }

    private fun clickBottomInventory(event: InventoryClickEvent, player: Player) {
        if (event.click != ClickType.LEFT) return
        val itemStack = event.currentItem?.clone() ?: return
        player.playButtonSound()
        val baseItemStack = drop.toBaseItemStack(itemStack)
        drop.addBaseItemStack(baseItemStack)
        isChanged = true
        refresh()
    }

    private companion object {
        const val ITEM_SIZE = 45
        val itemSlots = 0 until ITEM_SIZE

        const val BEFORE_PAGE_SLOT = 45
        const val NEXT_PAGE_SLOT = 53

        const val MAX_SLOT = 53
    }
}