package kr.cosine.mythicmobsdrop.config

import kr.cosine.mythicmobsdrop.config.component.DropConfigComponent
import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.data.item.BaseItemStack
import kr.cosine.mythicmobsdrop.factory.drop.registry.DropFactoryRegistry
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry
import kr.hqservice.framework.bukkit.core.extension.toByteArray
import kr.hqservice.framework.bukkit.core.extension.toItemArray
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.File

@Component
class DropConfig(
    plugin: Plugin,
    private val dropFactoryRegistry: DropFactoryRegistry,
    private val dropHolderRegistry: DropHolderRegistry
) : DropConfigComponent {

    private val file = File(plugin.dataFolder, "drop.yml")
    private val config = YamlConfiguration.loadConfiguration(file)

    override fun load() {
        if (!file.exists()) return
        config.getConfigurationSection("drop")?.apply {
            getKeys(false).forEach keyForEach@ { key ->
                val drops = mutableListOf<Drop>()
                val dropHolder = DropHolder(key, drops)
                getConfigurationSection(key)?.apply {
                    getKeys(false).forEach clazzForEach@ { clazz ->
                        val compressed = getString(clazz) ?: return@clazzForEach
                        val itemStacks = compressed.toDecompressed()
                        val dropFactory = dropFactoryRegistry.getDropFactory(clazz)
                        val drop = dropFactory.createDrop(itemStacks)
                        drops.add(drop)
                    }
                }
                dropHolderRegistry.setDropHolder(key, dropHolder)
            }
        }
    }

    override fun save() {
        if (DropHolderRegistry.isChanged) {
            config.set("drop", null)
            dropHolderRegistry.getDropHolderMap().forEach { (key, dropHolder) ->
                dropHolder.getDrops().forEach { drop ->
                    config.set("drop.$key.${drop.key}", drop.toCompressed())
                }
            }
            config.save(file)
            DropHolderRegistry.isChanged = false
        }
    }

    private fun String.toDecompressed(): List<ItemStack> {
        return Base64Coder.decodeLines(this).toItemArray().toList()
    }

    @Suppress("unchecked_cast")
    private fun Drop.toCompressed(): String {
        val itemStacks = baseItemStacks.map(BaseItemStack::toItemStack)
        val byteArray = (itemStacks.toTypedArray() as Array<ItemStack?>).toByteArray()
        return Base64Coder.encodeLines(byteArray)
    }
}