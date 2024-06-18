package kr.cosine.mythicmobsdrop.service

import io.lumine.mythic.bukkit.BukkitAdapter
import io.lumine.mythic.core.mobs.ActiveMob
import kr.cosine.mythicmobsdrop.factory.item.registry.BaseItemStackFactoryRegistry
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry
import kr.hqservice.framework.global.core.component.Service

@Service
class DropService(
    private val dropHolderRegistry: DropHolderRegistry,
    private val baseItemStackFactoryRegistry: BaseItemStackFactoryRegistry
) {

    fun drop(mob: ActiveMob) {
        val dropHolder = dropHolderRegistry.findDropHolder(mob.mobType) ?: return
        val location = BukkitAdapter.adapt(mob.location)
        dropHolder.getDrops().forEach { drop ->
            val baseItemStackFactory = baseItemStackFactoryRegistry.getBaseItemStackFactory(drop::class)
            val itemStacks = baseItemStackFactory.getItemStacks(drop)
            itemStacks.forEach { itemStack ->
                location.world?.dropItemNaturally(location, itemStack.clone())
            }
        }
    }
}