package com.sagaciousdevelopment.NegatePetDamage;

import org.bukkit.plugin.java.JavaPlugin;

import com.sagaciousdevelopment.NegatePetDamage.handler.DamageHandler;

public class Core extends JavaPlugin{
	
	private static Core instance;
	public static Core getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance=this;
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		new DamageHandler();
	}

}
