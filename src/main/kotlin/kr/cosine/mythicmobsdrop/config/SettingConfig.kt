package kr.cosine.mythicmobsdrop.config

import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration

@Bean
class SettingConfig(
    private val config: HQYamlConfiguration
) {
    val autoSavePeriod get() = config.getLong("auto-save-period") * 20

    val drop get() = config.getBoolean("drop")

    fun reload() {
        config.reload()
    }
}