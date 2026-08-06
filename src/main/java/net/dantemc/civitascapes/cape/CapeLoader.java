package net.dantemc.civitascapes.cape;

public class CapeLoader {
    //read capes.json and fill CapeManager class

    public static void load() {
        CapeRegistry.register(
                new CapeDefinition(
                        "test",
                        "Test Cape",
                        CapeType.RESOURCEPACK,
                        "civitas_capes:textures/capes/test.png"
                )
        );
    }
}