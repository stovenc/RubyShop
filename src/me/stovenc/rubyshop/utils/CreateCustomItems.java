package me.stovenc.rubyshop.utils;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateCustomItems implements Listener{
	
	public static ItemStack createRubyHelm() {
		ItemStack i = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta im = i.getItemMeta();
		
		im.setDisplayName(Utils.chat("&b&lGod Sword &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(in coins)"), "777"));
		i.setItemMeta(im);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addEnchantment(Enchantment.DURABILITY, 4);
		return i;
	}
	
}
