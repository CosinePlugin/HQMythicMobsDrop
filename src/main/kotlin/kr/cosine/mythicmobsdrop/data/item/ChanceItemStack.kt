package kr.cosine.mythicmobsdrop.data.item

import kr.hqservice.framework.nms.extension.getNmsItemStack
import kr.hqservice.framework.nms.extension.nms
import org.bukkit.inventory.ItemStack

class ChanceItemStack(
    private val itemStack: ItemStack
) : BaseItemStack {

    private val tag get() = itemStack.getNmsItemStack().getTag()

    fun getChance(): Double = tag.getDouble(CHANCE_KEY, 0.0)

    fun setChance(chance: Double) {
        itemStack.nms {
            tag {
                setDouble(CHANCE_KEY, chance)
            }
        }
    }

    override fun getItemStack(): ItemStack = itemStack.clone()

    override fun getOriginalItemStack(): ItemStack {
        return itemStack.nms {
            tag {
                remove(CHANCE_KEY)
            }
        }
    }

    companion object {
        private const val CHANCE_KEY = "HQMythicMobsDropChance"

        fun of(itemStack: ItemStack): ChanceItemStack {
            return ChanceItemStack(
                itemStack.nms {
                    tag {
                        setDouble(CHANCE_KEY, 100.0)
                    }
                }
            )
        }
    }
}