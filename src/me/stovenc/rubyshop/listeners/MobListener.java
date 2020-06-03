package me.stovenc.rubyshop.listeners;

import java.util.Random;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.stovenc.rubyshop.Core;
import me.stovenc.rubyshop.utils.TitleManager;
import me.stovenc.rubyshop.utils.Utils;

public class MobListener implements Listener {
	Random ran = new Random();
	Core plugin = Core.hook();
	

	@EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
		
        if(event.getEntity() instanceof Monster) {
        	
            Monster monsterEnt = (Monster) event.getEntity();
            
            if(monsterEnt.getKiller() instanceof Player) {
            	
            	Player p = (Player) monsterEnt.getKiller();
                OfflinePlayer mcPlayer = (OfflinePlayer)monsterEnt.getKiller();
                
                if(mcPlayer == null) {
                    return;
                }
                
                int chance = ran.nextInt(9); //random number between 0-9
                
                //scheduler so that it informs user after set time of amount of mobs killed and 
                //their new balance wiothout spam
                if (chance > -1) {
	                if (plugin.rubyUpdater.get(p.getUniqueId()) == null) {
	                	plugin.rubyUpdater.put(p.getUniqueId(), new Entry(0, true));
	            	}
	                plugin.rubyUpdater.get(p.getUniqueId()).incrementRubyByOne();
	                if (plugin.getEntryCycle(p) == true) {
	                	plugin.rubyUpdater.get(p.getUniqueId()).setTurnToFalse();
	                	BukkitScheduler scheduler = Core.getInstance().getServer().getScheduler();
	                    scheduler.scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
	                        @Override
	                        public void run() {
	                        	TitleManager.sendActionBar(p, Utils.chat(
	                        			"&a[+] &6&l" + plugin.getRubyFromMob(p) + " Ruby"));
	                        	plugin.rubyUpdater.get(p.getUniqueId()).setRubyToZero();
	                        	plugin.rubyUpdater.get(p.getUniqueId()).setTurnToTrue();
	                        }
	                    }, 50L);
	                }
	                
	                plugin.addCurrencyToPlayer(mcPlayer, 1);
                }
            }
        } // if(event.getEntity() instanceof Monster)
    } // public void onEntityDeath(EntityDeathEvent event)
	
} // publ
