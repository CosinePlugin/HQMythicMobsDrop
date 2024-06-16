package kr.cosine.mythicmobsdrop.extension

import org.bukkit.Sound
import org.bukkit.entity.Player

internal fun Player.playButtonSound() {
    playSound(location, Sound.UI_BUTTON_CLICK, 1f, 1f)
}