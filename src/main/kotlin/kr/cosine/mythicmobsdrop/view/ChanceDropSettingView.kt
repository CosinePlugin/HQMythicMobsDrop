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

    private val title = TextComponent("확률 설정")
    private val baseItemStack = ItemStack(Material.PAPER).editMeta { setDisplayName("§r") }
    private val resultItemStack = ItemStack(Material.PAPER).editMeta { setDisplayName("§a설정하기") }

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
                        player.sendMessage("§c숫자만 입력할 수 있습니다.")
                        return@setConfirmHandler false
                    }
                    if (chance <= 0.0) {
                        player.sendMessage("§c양수만 입력할 수 있습니다.")
                        return@setConfirmHandler false
                    }
                    chanceItemStack.setChance(chance)
                    player.sendMessage("§a확률을 ${chance.format()}퍼센트로 설정하였습니다.")
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