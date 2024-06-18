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

    @CommandExecutor("기본초기화", "필수로 드랍할 아이템 목록을 초기화합니다.", priority = 2)
    fun resetDefaultDrop(
        sender: CommandSender,
        @ArgumentLabel("몹") dropHolder: DropHolder
    ) {
        if (dropHolder.removeDrop(DefaultDrop::class)) {
            sender.sendMessage("§a필수로 드랍할 아이템 목록을 초기화하였습니다.")
        } else {
            sender.sendMessage("§c설정되어 있는 아이템이 없습니다.")
        }
    }

    @CommandExecutor("확률설정", "확률적으로 드랍할 아이템을 설정합니다.", priority = 3)
    fun setChanceDrop(
        player: Player,
        @ArgumentLabel("몹") dropHolder: DropHolder
    ) {
        dropViewService.openChanceDropSettingView(dropHolder, player)
    }

    @CommandExecutor("확률초기화", "확률적으로 드랍할 아이템 목록을 초기화합니다.", priority = 4)
    fun resetChanceDrop(
        sender: CommandSender,
        @ArgumentLabel("몹") dropHolder: DropHolder
    ) {
        if (dropHolder.removeDrop(ChanceDrop::class)) {
            sender.sendMessage("§a확률적으로 드랍할 아이템 목록을 초기화하였습니다.")
        } else {
            sender.sendMessage("§c설정되어 있는 아이템이 없습니다.")
        }
    }

    @CommandExecutor("저장", "변경된 사항을 수동으로 저장합니다.", priority = 5)
    suspend fun save(sender: CommandSender) {
        withContext(Dispatchers.IO) {
            dropConfig.save()
            sender.sendMessage("§a변경된 사항을 수동으로 저장하였습니다.")
        }
    }
}