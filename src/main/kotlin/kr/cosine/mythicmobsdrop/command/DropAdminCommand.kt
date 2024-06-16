package kr.cosine.mythicmobsdrop.command

import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.service.DropViewService
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.entity.Player

@Command(label = "드랍관리", isOp = true)
class DropAdminCommand(
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
}