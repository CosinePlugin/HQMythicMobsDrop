package kr.cosine.mythicmobsdrop.service

import io.lumine.mythic.bukkit.BukkitAdapter
import io.lumine.mythic.core.mobs.ActiveMob
import kr.cosine.mythicmobsdrop.config.SettingConfig
import kr.cosine.mythicmobsdrop.factory.item.registry.BaseItemStackFactoryRegistry
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry
import kr.hqservice.framework.global.core.component.Service
import kr.hqservice.framework.inventory.util.hasSpace
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Service
class DropService(
    private val settingConfig: SettingConfig,
    private val dropHolderRegistry: DropHolderRegistry,
    private val baseItemStackFactoryRegistry: BaseItemStackFactoryRegistry
) {

    fun drop(killer: LivingEntity?, mob: ActiveMob) {
        val dropHolder = dropHolderRegistry.findDropHolder(mob.mobType) ?: return
        val location = BukkitAdapter.adapt(mob.location)
        dropHolder.getDrops().forEach { drop ->
            val baseItemStackFactory = baseItemStackFactoryRegistry.getBaseItemStackFactory(drop::class)
            val itemStacks = baseItemStackFactory.getItemStacks(drop)
            itemStacks.forEach { itemStack ->
                val clonedItemStack = itemStack.clone()
                if (settingConfig.drop || killer == null || killer !is Player) {
                    location.dropItemNaturally(clonedItemStack)
                } else {
                    val playerInventory = killer.inventory
                    if (playerInventory.hasSpace(clonedItemStack)) {
                        playerInventory.addItem(clonedItemStack)
                    } else {
                        location.dropItemNaturally(clonedItemStack)
                    }
                }
            }
        }
    }

    private fun Location.dropItemNaturally(itemStack: ItemStack) {
        world?.dropItemNaturally(this, itemStack)
    }
}