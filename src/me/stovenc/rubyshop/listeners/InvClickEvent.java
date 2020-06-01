package me.stovenc.rubyshop.listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.stovenc.rubyshop.Core;
import me.stovenc.rubyshop.utils.CreateCustomItems;
import me.stovenc.rubyshop.utils.Utils;

public class InvClickEvent implements Listener{
	
	Core plugin = Core.hook();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		OfflinePlayer offp = (OfflinePlayer) e.getWhoClicked();
		
		if (e.getCurrentItem() == null) {
			return;
		}
		
		if (!(e.getCurrentItem().hasItemMeta())) {
			return;
		}
		
		if (e.getInventory().getName().contains("Shop")) {
			e.setCancelled(true);
			
			Integer coinamount = plugin.coinData.get(p.getUniqueId());
			
			if (coinamount == null) {
				return;
			}
			
			Integer price = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(1)); //gets price opf item
			
			if (coinamount < price) {
				p.sendMessage(Utils.chat("&4Not enough money!"));;
			} else {
				plugin.removeCurrencyFromPlayer(offp, price);
				p.sendMessage(Utils.chat("&aYou have purchased a %item% for %price%!".replaceAll(
						"%item%", e.getCurrentItem().getType().name()).replaceAll(
								"%price%", e.getCurrentItem().getItemMeta().getLore().get(1))));
				
				if (e.getRawSlot() == 0) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubyHelm()));
				} else if (e.getRawSlot() == 1) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubyChest()));
				} else if (e.getRawSlot() == 2) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubyLegs()));
				} else if (e.getRawSlot() == 3) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubyBoot()));
				} else if (e.getRawSlot() == 4) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubySword()));
				} else if (e.getRawSlot() == 5) {
					p.getInventory().addItem(new ItemStack(CreateCustomItems.createRubyBow()));
				}
			}
		}
		
		
	}
	
	
}
