package me.lynxplay.supermario.engine.util;

import me.lynxplay.supermario.SuperMario;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileLoader {


    public static File getFile(String file) {
        try {
            File datafolder = new File(System.getProperty("user.dir") + "/VoidEngine");
            File config = new File(datafolder, file);
            datafolder.mkdirs();
            datafolder.mkdir();

            if (!config.exists()) {
                try (InputStream in = SuperMario.class.getClassLoader().getResourceAsStream(file)) {
                    if (in != null) {
                        if (!config.createNewFile()) {
                            throw new RuntimeException("Failed to create default file");
                        }
                        Files.copy(in , config.toPath() , StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        config.createNewFile();
                    }
                }
            }
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
