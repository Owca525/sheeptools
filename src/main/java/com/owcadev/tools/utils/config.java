package com.owcadev.tools.utils;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class config {
    private static final Path CONFIG_PATH =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("sheeptools.ini");

    public double MaxbeaconRange = -1;
    public int MaxEnchatmentLevel = -1;

    public boolean OverWriteCompatible = false;
    public boolean IgnoreExpense = false;
    public boolean DragonDropEgg = false;

    public static config load() {
        config config = new config();
        Properties properties = new Properties();

        try {
            if (Files.exists(CONFIG_PATH)) {
                try (var reader = Files.newBufferedReader(CONFIG_PATH)) {
                    properties.load(reader);
                }

                config.MaxbeaconRange = Double.parseDouble(
                        properties.getProperty(
                                "MaxbeaconRange",
                                Double.toString(config.MaxbeaconRange)
                        )
                );
                config.MaxEnchatmentLevel = Integer.parseInt(
                        properties.getProperty(
                                "MaxEnchatmentLevel",
                                Integer.toString(config.MaxEnchatmentLevel)
                        )
                );

                config.OverWriteCompatible = Boolean.parseBoolean(
                        properties.getProperty(
                                "OverWriteCompatible",
                                Boolean.toString(config.OverWriteCompatible)
                        )
                );
                config.IgnoreExpense = Boolean.parseBoolean(
                        properties.getProperty(
                                "IgnoreExpense",
                                Boolean.toString(config.IgnoreExpense)
                        )
                );
                config.DragonDropEgg = Boolean.parseBoolean(
                        properties.getProperty(
                                "DragonDropEgg",
                                Boolean.toString(config.DragonDropEgg)
                        )
                );
            }

            config.save();
            return config;

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(
                    "Failed Load Config of sheeptools (sheeptools.ini)", e);
        }
    }

    public void save() throws IOException {
        Files.createDirectories(CONFIG_PATH.getParent());

        Properties properties = new Properties();
        properties.setProperty(
                "OverWriteCompatible", Boolean.toString(OverWriteCompatible));
        properties.setProperty(
                "IgnoreExpense", Boolean.toString(IgnoreExpense));
        properties.setProperty(
                "DragonDropEgg", Boolean.toString(DragonDropEgg));

        properties.setProperty(
                "MaxbeaconRange", Double.toString(MaxbeaconRange));
        properties.setProperty(
                "MaxEnchatmentLevel", Integer.toString(MaxEnchatmentLevel));

        try (var writer = Files.newBufferedWriter(CONFIG_PATH)) {
            properties.store(writer, "");
        }
    }
}
