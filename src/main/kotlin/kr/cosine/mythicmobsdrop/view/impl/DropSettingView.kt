package kr.cosine.mythicmobsdrop.view.impl

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.extension.playButtonSound
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry.Companion.isChanged
import kr.cosine.mythicmobsdrop.view.ChanceDropSettingView
import kr.cosine.mythicmobsdrop.view.PageView
import kr.cosine.mythicmobsdrop.view.data.Slot
import kr.cosine.mythicmobsdrop.view.model.DropSettingViewModel
import kr.cosine.mythicmobsdrop.view.util.ButtonUtil
import kr.cosine.mythicmobsdrop.view.util.LoreUtil
import kr.hqservice.framework.bukkit.core.extension.editMeta
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

class DropSettingView(
    private val dropSettingViewModel: DropSettingViewModel,
    key: String,
    private val drop: Drop
) : PageView("$key ▸ ${drop.title}") {

    override var page = 0

    override val baseItemStacks get() = drop.baseItemStacks
    private var currentBaseItemStacks = emptyList<BaseItemStack>()

    override fun initialize(inventory: Inventory) {
        inventory.clear()
        currentBaseItemStacks = baseItemStacks.drop(Slot.ITEM_SIZE * page).take(Slot.ITEM_SIZE)
        registerButton()
        Slot.itemSlots.forEach { index ->
            if (index >= currentBaseItemStacks.size) return
            val baseItemStack = currentBaseItemStacks[index]
            val itemStack = baseItemStack.toItemStack().editMeta {
                lore = LoreUtil.getLore(baseItemStack)
            }
            inventory.setItem(index, itemStack)
        }
    }

    private fun registerButton() {
        ButtonUtil.getBeforePageButton().setSlot(this, Slot.BEFORE_PAGE_SLOT)
        ButtonUtil.getNextPageButton().setSlot(this, Slot.NEXT_PAGE_SLOT)
        ButtonUtil.getBackgroundButton().setSlot(this, Slot.backgroundSlots)
    }

    override fun onClick(event: InventoryClickEvent) {
        if (event.clickedInventory == null) return
        val player = event.whoClicked as Player
        val slot = event.rawSlot
        if (slot <= Slot.MAX_SLOT) {
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
                if (slot in Slot.itemSlots) {
                    val baseItemStack = event.clickedBaseItemStack ?: return
                    player.playButtonSound()
                    drop.removeBaseItemStack(baseItemStack)
                    isChanged = true
                    if (page > 0 && baseItemStacks.size == Slot.ITEM_SIZE) {
                        page--
                    }
                    refresh()
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
        if (currentBaseItemStacks.size == Slot.ITEM_SIZE) {
            page++
        }
        refresh()
    }
}