package com.sagaciousdevelopment.NegatePetDamage.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.sagaciousdevelopment.NegatePetDamage.Core;

public class DamageHandler implements Listener{
	
	private boolean negateOnlySweeping = false;
	
	public DamageHandler() {
		Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
		negateOnlySweeping = Core.getInstance().getConfig().getBoolean("only-negate-sweep");
	}

	private boolean sweepable() {
		return Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().split("_")[1])>=11;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Tameable&&e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			Tameable t = (Tameable)e.getEntity();
			if(t.isTamed()) {
		    if(sweepable()&&negateOnlySweeping&&t.getOwner().getUniqueId().equals(p.getUniqueId())) {
		    	if(e.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) {
					e.setCancelled(true);
					return;
				}
		    }else if(t.getOwner().getUniqueId().equals(p.getUniqueId())){
		    	e.setCancelled(true);
		    }
		}
		}
	}
}
