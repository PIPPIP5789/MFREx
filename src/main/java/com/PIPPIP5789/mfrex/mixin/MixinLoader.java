package com.pippip5789.mfrex.mixin;

import betterwithmods.BWMod;
import com.animania.Animania;
import com.codetaylor.mc.pyrotech.ModPyrotech;
import dynamicswordskills.DynamicSwordSkills;
import minefantasy.mfr.item.ItemWeaponMFR;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import zone.rong.mixinbooter.IEarlyMixinLoader;
import zone.rong.mixinbooter.ILateMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MixinLoader implements ILateMixinLoader, IFMLLoadingPlugin {

    @Override
    public List<String> getMixinConfigs() {
        MixinBootstrap.init();
        ArrayList<String> ret = new ArrayList<>();
        ret.add("mixins.mfrex.json");

        if(Loader.isModLoaded(Animania.MODID)) {
            ret.add("mixins.mfrex.animania.json");
        }
        if(Loader.isModLoaded(BWMod.MODID)) {
            ret.add("mixins.mfrex.betterwitheverything.json");
        }
        if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            ret.add("mixins.mfrex.pyrotech.json");
        }
        if(Loader.isModLoaded(BWMod.MODID) || Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            ret.add("mixins.mfrex.bwe-pyrotech.json");
        }
        if(Loader.isModLoaded("dynamicswordskills")) {
            ret.add("mixins.mfrex.dynamicswordskills.json");
        }

        return ret;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return mixinConfig.equals("mixins.mfrex.json") || mixinConfig.equals("mixins.mfrex.animania,json") || mixinConfig.equals("mixins.mfrex.betterwitheverything,json") || mixinConfig.equals("mixins.mfrex.pyrotech.json") || mixinConfig.equals("mixins.mfrex.bwe-pyrotech,json") || mixinConfig.equals("mixins.mfrex.dynamicswordskills.json");
    }

    @Override
    public void onMixinConfigQueued(String mixinConfig) {
        ILateMixinLoader.super.onMixinConfigQueued(mixinConfig);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}