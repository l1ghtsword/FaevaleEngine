package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;
import ca.lightnet.FaevaleEngine.listeners.GiveBlockDropsListener;
import ca.lightnet.FaevaleEngine.listeners.RegenerateBlockListener;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockBreakListener;

public class BlockBreakComponent extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockBreakListener(this.getComponentName()));
        registerListener(new RegenerateBlockListener(this.getComponentName()));
        registerListener(new GiveBlockDropsListener(this.getComponentName()));


        registerConfig();
    }

    @Override
    public void onSave(){
        this.saveConfig();
    }

    @Override
    public void onUnload(){
        this.saveConfig();
    }
}
