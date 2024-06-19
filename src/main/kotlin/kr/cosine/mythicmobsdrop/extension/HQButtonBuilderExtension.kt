package kr.cosine.mythicmobsdrop.extension

import kr.hqservice.framework.inventory.button.HQButtonBuilder
import kr.hqservice.framework.inventory.event.ButtonClickEvent
import org.bukkit.event.inventory.ClickType

fun HQButtonBuilder.setLeftClickFunction(block: (ButtonClickEvent) -> Unit): HQButtonBuilder {
    return setClickFunction { event ->
        if (event.getClickType() != ClickType.LEFT) return@setClickFunction
        block(event)
    }
}