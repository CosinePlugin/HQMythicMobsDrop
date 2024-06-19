package kr.cosine.mythicmobsdrop.command.provider

import io.lumine.mythic.bukkit.MythicBukkit
import kr.cosine.mythicmobsdrop.data.drop.holder.DropHolder
import kr.cosine.mythicmobsdrop.registry.DropHolderRegistry
import kr.hqservice.framework.command.CommandArgumentProvider
import kr.hqservice.framework.command.CommandContext
import kr.hqservice.framework.command.argument.exception.ArgumentFeedback
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.Location

@Component
class DropHolderArgumentProvider(
    private val dropHolderRegistry: DropHolderRegistry
) : CommandArgumentProvider<DropHolder> {

    private val mythicBukkit = MythicBukkit.inst()
    private val mobNames get() = mythicBukkit.mobManager.mobNames

    override suspend fun cast(context: CommandContext, argument: String?): DropHolder {
        if (argument == null) {
            throw ArgumentFeedback.Message("§cPlease enter the mob you want to set.")
        }
        if (!mobNames.contains(argument)) {
            throw ArgumentFeedback.Message("§cThis mob does not exist.")
        }
        return dropHolderRegistry.getDropHolder(argument)
    }

    override suspend fun getTabComplete(context: CommandContext, location: Location?): List<String> {
        return mobNames.toList()
    }
}