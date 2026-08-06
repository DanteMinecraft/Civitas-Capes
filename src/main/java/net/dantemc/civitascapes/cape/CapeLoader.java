package net.dantemc.civitascapes.cape;

import com.google.gson.Gson;
import net.dantemc.civitascapes.cape.wrapper.CapeFile;
import net.dantemc.civitascapes.data.CapeDataInitializer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CapeLoader {

    private static final Gson GSON = new Gson();

    public static void load() {

        //Read json
        try (Reader reader = new FileReader(CapeDataInitializer.CAPES_FILE.toFile())) {

            CapeFile file = GSON.fromJson(reader, CapeFile.class);
            CapeRegistry.clear();

            for (CapeDefinition cape : file.getCapes()) {
                CapeRegistry.register(cape);
            }

            System.out.println("Loaded " + file.getCapes().size() + " capes.");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}