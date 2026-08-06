package net.dantemc.civitascapes.cape.wrapper;

import net.dantemc.civitascapes.cape.CapeDefinition;

import java.util.List;

public class CapeFile {
    private List<CapeDefinition> capes;

    public List<CapeDefinition> getCapes() {
        return capes;
    }

    public void setCapes(List<CapeDefinition> capes) {
        this.capes = capes;
    }
}
