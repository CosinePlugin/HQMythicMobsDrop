package kr.cosine.mythicmobsdrop.config.component

import kr.hqservice.framework.global.core.component.HQComponent

interface DropConfigComponent : HQComponent {

    fun load()

    fun save()
}