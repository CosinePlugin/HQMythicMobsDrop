package kr.cosine.mythicmobsdrop.factory.item

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.hqservice.framework.global.core.component.HQComponent
import org.bukkit.inventory.ItemStack

interface BaseItemStackFactory<out T : Drop> : HQComponent {

    fun getItemStacks(drop: @UnsafeVariance T): List<ItemStack>
}