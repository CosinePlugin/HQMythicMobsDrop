package kr.cosine.mythicmobsdrop.config.component.handler

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kr.cosine.mythicmobsdrop.config.DropConfig
import kr.cosine.mythicmobsdrop.config.component.DropConfigComponent
import kr.cosine.mythicmobsdrop.factory.drop.handler.DropFactoryComponentHandler
import kr.cosine.mythicmobsdrop.factory.item.handler.BaseItemStackFactoryComponentHandler
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.coroutine.bukkitDelay
import kr.hqservice.framework.bukkit.core.coroutine.element.TeardownOptionCoroutineContextElement
import kr.hqservice.framework.global.core.component.handler.ComponentHandler
import kr.hqservice.framework.global.core.component.handler.HQComponentHandler
import kr.hqservice.framework.yaml.config.HQYamlConfiguration

@ComponentHandler([DropFactoryComponentHandler::class, BaseItemStackFactoryComponentHandler::class])
class DropConfigComponentHandler(
    private val plugin: HQBukkitPlugin,
    private val config: HQYamlConfiguration
) : HQComponentHandler<DropConfigComponent> {

    override fun setup(element: DropConfigComponent) {
        val autoSavePeriod = config.getLong("auto-save-period") * 20
        element.load()
        plugin.launch(
            Dispatchers.IO + TeardownOptionCoroutineContextElement(true)
        ) {
            while (isActive) {
                bukkitDelay(autoSavePeriod)
                element.save()
            }
        }
    }

    override fun teardown(element: DropConfigComponent) {
        element.save()
    }
}