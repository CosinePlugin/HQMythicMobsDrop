package kr.cosine.mythicmobsdrop.view.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.global.core.component.Bean

@Bean
class DropSettingViewModel(
    private val plugin: HQBukkitPlugin
) {

    private val scheduler = plugin.server.scheduler

    fun later(delay: Long = 1, runnable: Runnable) {
        scheduler.runTaskLater(plugin, runnable, delay)
    }

    fun save() = plugin.launch(Dispatchers.IO) {

    }
}