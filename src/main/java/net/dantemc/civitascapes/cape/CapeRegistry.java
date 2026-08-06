package net.dantemc.civitascapes.cape;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CapeRegistry {

    private static final Map<String, CapeDefinition> ALL_CAPES = new HashMap<>();

    public static void register(CapeDefinition cape) {
        ALL_CAPES.put(cape.getId(), cape);
    }

    public static CapeDefinition get(String id) {
        return ALL_CAPES.get(id);
    }

    public static Collection<CapeDefinition> getAll() {
        return ALL_CAPES.values();
    }

    public static void clear() {
        ALL_CAPES.clear();
    }

}
