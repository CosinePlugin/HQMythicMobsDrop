package kr.cosine.mythicmobsdrop.service

import kr.cosine.mythicmobsdrop.data.drop.Drop
import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.data.drop.impl.ChanceDrop
import kr.cosine.mythicmobsdrop.data.drop.impl.DefaultDrop
import kr.cosine.mythicmobsdrop.view.DropSettingView
import kr.cosine.mythicmobsdrop.view.model.DropSettingViewModel
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.entity.Player
import kotlin.reflect.KClass

@Service
class DropViewService(
    private val dropSettingViewModel: DropSettingViewModel
) {

    fun openDefaultDropSettingView(dropHolder: DropHolder, player: Player) {
        openDropSettingView(dropHolder, DefaultDrop::class, player)
    }

    fun openChanceDropSettingView(dropHolder: DropHolder, player: Player) {
        openDropSettingView(dropHolder, ChanceDrop::class, player)
    }

    private fun <T : Drop> openDropSettingView(dropHolder: DropHolder, clazz: KClass<T>, player: Player) {
        val drop = dropHolder.getDrop(clazz)
        DropSettingView(dropSettingViewModel, dropHolder.key, drop).open(player)
    }
}