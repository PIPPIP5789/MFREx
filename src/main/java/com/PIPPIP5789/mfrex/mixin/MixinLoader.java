package com.pippip5789.mfrex.mixin;

import com.codetaylor.mc.pyrotech.ModPyrotech;
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
public class MixinLoader implements IEarlyMixinLoader, IFMLLoadingPlugin {

    @Override
    public List<String> getMixinConfigs() {
        MixinBootstrap.init();
        ArrayList<String> ret = new ArrayList<>();
        ret.add("mixins.mfrex.json");
        if(Loader.isModLoaded(ModPyrotech.MOD_ID)) {
            ret.add("mixins.mfrex.pyrotech.json");
        }
        return ret;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return mixinConfig.equals("mixins.mfrex.json");
    }

    @Override
    public void onMixinConfigQueued(String mixinConfig) {
        IEarlyMixinLoader.super.onMixinConfigQueued(mixinConfig);
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