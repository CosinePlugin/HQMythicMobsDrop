package kr.cosine.mythicmobsdrop.data.item

import org.bukkit.inventory.ItemStack

sealed interface BaseItemStack {

    fun toItemStack(): ItemStack

    fun toOriginalItemStack(): ItemStack
}