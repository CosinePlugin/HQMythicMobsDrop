package kr.cosine.mythicmobsdrop.data.item

import kr.hqservice.framework.nms.extension.getNmsItemStack
import kr.hqservice.framework.nms.extension.nms
import org.bukkit.inventory.ItemStack

class ChanceItemStack(
    private val itemStack: ItemStack
) : BaseItemStack {

    private val tag get() = itemStack.getNmsItemStack().getTag()
    private var chance = tag.getDouble(CHANCE_KEY, 0.0)

    fun getChance(): Double = chance

    fun setChance(chance: Double) {
        itemStack.nms {
            tag {
                setDouble(CHANCE_KEY, chance)
                this@ChanceItemStack.chance = chance
            }
        }
    }

    override fun toItemStack(): ItemStack = itemStack.clone()

    override fun toOriginalItemStack(): ItemStack {
        return toItemStack().nms {
            tag {
                remove(CHANCE_KEY)
            }
        }
    }

    companion object {
        private const val CHANCE_KEY = "HQMythicMobsDropChance"

        fun of(itemStack: ItemStack, chance: Double = 100.0): ChanceItemStack {
            return ChanceItemStack(
                itemStack.nms {
                    tag {
                        setDouble(CHANCE_KEY, chance)
                    }
                }
            )
        }
    }
}