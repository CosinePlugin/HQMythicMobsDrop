package kr.cosine.mythicmobsdrop.command

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.cosine.mythicmobsdrop.config.DropConfig
import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.data.drop.impl.ChanceDrop
import kr.cosine.mythicmobsdrop.data.drop.impl.DefaultDrop
import kr.cosine.mythicmobsdrop.service.DropViewService
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(label = "hqdrop", isOp = true)
class DropAdminCommand(
    private val dropConfig: DropConfig,
    private val dropViewService: DropViewService
) {

    @CommandExecutor("default-setting", "Set the required items to be dropped.", priority = 1)
    fun setDefaultDrop(
        player: Player,
        @ArgumentLabel("mob") dropHolder: DropHolder
    ) {
        dropViewService.openDefaultDropSettingView(dropHolder, player)
    }

    @CommandExecutor("default-reset", "Reset the list of items to drop as required.", priority = 2)
    fun resetDefaultDrop(
        sender: CommandSender,
        @ArgumentLabel("mob") dropHolder: DropHolder
    ) {
        if (dropHolder.removeDrop(DefaultDrop::class)) {
            sender.sendMessage("§aThe list of required items to drop has been reset.")
        } else {
            sender.sendMessage("§cThere are no items set.")
        }
    }

    @CommandExecutor("chance-setting", "Set the item to drop with probability.", priority = 3)
    fun setChanceDrop(
        player: Player,
        @ArgumentLabel("mob") dropHolder: DropHolder
    ) {
        dropViewService.openChanceDropSettingView(dropHolder, player)
    }

    @CommandExecutor("chance-reset", "Reset the list of items to drop with probability.", priority = 4)
    fun resetChanceDrop(
        sender: CommandSender,
        @ArgumentLabel("mob") dropHolder: DropHolder
    ) {
        if (dropHolder.removeDrop(ChanceDrop::class)) {
            sender.sendMessage("§aThe list of items to drop with probability has been reset.")
        } else {
            sender.sendMessage("§cThere are no items set.")
        }
    }

    @CommandExecutor("save", "Save changes manually.", priority = 5)
    suspend fun save(sender: CommandSender) {
        withContext(Dispatchers.IO) {
            dropConfig.save()
            sender.sendMessage("§aChanges were saved manually.")
        }
    }
}