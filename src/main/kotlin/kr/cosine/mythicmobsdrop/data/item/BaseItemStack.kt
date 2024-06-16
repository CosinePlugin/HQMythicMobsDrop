package kr.cosine.mythicmobsdrop.data.item

import org.bukkit.inventory.ItemStack

sealed interface BaseItemStack {

    fun getItemStack(): ItemStack

    fun getOriginalItemStack(): ItemStack
}