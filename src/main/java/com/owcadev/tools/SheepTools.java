package com.owcadev.tools;

import com.owcadev.tools.utils.config;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SheepTools implements ModInitializer {
	public static final String MOD_ID = "sheeptools";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static config SheepConfig;

	@Override
	public void onInitialize() {
		LOGGER.info("Initialize SheepTools");
		SheepConfig = config.load();

		new commands().InitialCommands();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
