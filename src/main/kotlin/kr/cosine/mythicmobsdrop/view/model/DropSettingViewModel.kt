package kr.cosine.mythicmobsdrop.view.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.coroutine.bukkitDelay
import kr.hqservice.framework.bukkit.core.coroutine.extension.BukkitMain
import kr.hqservice.framework.global.core.component.Bean

@Bean
class DropSettingViewModel(
    private val plugin: HQBukkitPlugin
) {

    fun later(scope: () -> Unit) {
        plugin.launch(Dispatchers.BukkitMain) {
            bukkitDelay(1)
            scope()
        }
    }
}