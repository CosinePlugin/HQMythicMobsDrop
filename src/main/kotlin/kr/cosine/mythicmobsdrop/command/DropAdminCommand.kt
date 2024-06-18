package kr.cosine.mythicmobsdrop.command

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.cosine.mythicmobsdrop.config.DropConfig
import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.service.DropViewService
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command(label = "드랍관리", isOp = true)
class DropAdminCommand(
    private val dropConfig: DropConfig,
    private val dropViewService: DropViewService
) {

    @CommandExecutor("기본설정", "필수로 드랍할 아이템을 설정합니다.", priority = 1)
    fun setDefaultDrop(
        player: Player,
        @ArgumentLabel("몹") dropHolder: DropHolder
    ) {
        dropViewService.openDefaultDropSettingView(dropHolder, player)
    }

    @CommandExecutor("확률설정", "확률적으로 드랍할 아이템을 설정합니다.", priority = 2)
    fun setChanceDrop(
        player: Player,
        @ArgumentLabel("몹") dropHolder: DropHolder
    ) {
        dropViewService.openChanceDropSettingView(dropHolder, player)
    }

    @CommandExecutor("저장", "변경된 사항을 수동으로 저장합니다.", priority = 3)
    suspend fun save(sender: CommandSender) {
        withContext(Dispatchers.IO) {
            dropConfig.save()
            sender.sendMessage("§a변경된 사항을 수동으로 저장하였습니다.")
        }
    }
}