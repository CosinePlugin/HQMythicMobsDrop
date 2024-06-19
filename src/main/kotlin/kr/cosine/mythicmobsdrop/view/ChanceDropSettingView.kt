package kr.cosine.mythicmobsdrop.view

import kr.cosine.mythicmobsdrop.data.item.ChanceItemStack
import kr.cosine.mythicmobsdrop.extension.format
import kr.hqservice.framework.bukkit.core.extension.editMeta
import kr.hqservice.framework.nms.extension.virtual
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object ChanceDropSettingView {

    private val title = TextComponent("Probability settings")
    private val baseItemStack = ItemStack(Material.PAPER).editMeta { setDisplayName("§r") }
    private val resultItemStack = ItemStack(Material.PAPER).editMeta { setDisplayName("§aSetting up") }

    fun open(
        player: Player,
        chanceItemStack: ChanceItemStack,
        onSuccess: () -> Unit,
        onClose: () -> Unit
    ) {
        player.closeInventory()
        player.virtual {
            anvil(title) {
                setBaseItem(baseItemStack)
                setResultItem(resultItemStack)
                setConfirmHandler {
                    val chance = it.toDoubleOrNull() ?: run {
                        player.sendMessage("§cYou can only enter numbers.")
                        return@setConfirmHandler false
                    }
                    if (chance <= 0.0) {
                        player.sendMessage("§cOnly positive numbers can be entered.")
                        return@setConfirmHandler false
                    }
                    chanceItemStack.setChance(chance)
                    player.sendMessage("§aThe probability is set to ${chance.format()} percent.")
                    onSuccess()
                    onClose()
                    return@setConfirmHandler true
                }
                setCloseHandler {
                    onClose()
                }
            }
        }
    }
}