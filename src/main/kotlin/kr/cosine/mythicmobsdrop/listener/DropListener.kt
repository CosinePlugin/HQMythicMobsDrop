package kr.cosine.mythicmobsdrop.listener

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import kr.cosine.mythicmobsdrop.service.DropService
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe

@Listener
class DropListener(
    private val dropService: DropService
) {

    @Subscribe
    fun onMythicMobsDeath(event: MythicMobDeathEvent) {
        dropService.drop(event.killer, event.mob)
    }
}