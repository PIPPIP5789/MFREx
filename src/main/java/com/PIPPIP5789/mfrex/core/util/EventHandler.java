package com.pippip5789.mfrex.core.util;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void playerJoined(PlayerEvent event) {
        if (event.getEntityPlayer() != null) {
            FoodBonus.preInit(event.getEntityPlayer().getEntityWorld().getSeed());
        }
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        //ItemStack stack = event.getEntityLiving().getActiveItemStack();
        //NBTTagCompound tag = ItemNBTFood.getNBT(stack, true, "test_tag");
        //System.out.println("Waka Tag -> " + tag);
    }

}
