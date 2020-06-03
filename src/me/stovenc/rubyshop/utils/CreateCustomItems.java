package me.stovenc.rubyshop.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.stovenc.rubyshop.Core;

public class CreateCustomItems implements Listener {
	static Core plugin = Core.hook();

	/**
	 * Creates an enchanted helm
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubyHelm() {
		ItemStack i = new ItemStack(Material.DIAMOND_HELMET, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		i.addEnchantment(Enchantment.OXYGEN, 3);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Helmet &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubyHelm() {
		ItemStack i = new ItemStack(Material.DIAMOND_HELMET, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		i.addEnchantment(Enchantment.OXYGEN, 3);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Helmet"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * Creates an enchanted chest
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubyChest() {
		ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Chestplate &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubyChest() {
		ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Chestplate"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * creates an enchanted legging
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubyLegs() {
		ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Leggings &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubyLegs() {
		ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Leggings"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * creates an enchanted boot
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubyBoot() {
		ItemStack i = new ItemStack(Material.DIAMOND_BOOTS, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
		i.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Boots &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubyBoot() {
		ItemStack i = new ItemStack(Material.DIAMOND_BOOTS, 1);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
		i.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Boots"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * creates an enchanted sword
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubySword() {
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);
		i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Sword &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubySword() {
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);
		i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Sword"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * creates an enchanted bow
	 * 
	 * @return
	 */
	public static ItemStack shopCreateRubyBow() {
		ItemStack i = new ItemStack(Material.BOW, 1);
		i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		i.addEnchantment(Enchantment.ARROW_FIRE, 1);
		i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Bow &9- &7(Click to Purachse)"));
		im.setLore(Arrays.asList(Utils.chat("&cPrice &7(tokens)"), "5000"));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createRubyBow() {
		ItemStack i = new ItemStack(Material.BOW, 1);
		i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		i.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		i.addEnchantment(Enchantment.ARROW_FIRE, 1);
		i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		ItemMeta im = i.getItemMeta();

		im.setDisplayName(Utils.chat("&b&lToken Bow"));
		i.setItemMeta(im);
		return i;
	}

	/**
	 * Spacer Glass Panes
	 */
	public static ItemStack createSpacer(){
        ItemStack adminspacer = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta meta = adminspacer.getItemMeta();
        meta.setDisplayName(Utils.chat(" "));
        adminspacer.setItemMeta(meta);
        return adminspacer;
    }
	
	public static ItemStack createTokenBal(OfflinePlayer p) {
		ItemStack i = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(Utils.chat("&b&lToken Balance"));
		im.setLore(Arrays.asList(Utils.chat("&7Balance: &b" + Utils.getTokenBal(p)), "", Utils.chat("&7Send Tokens to other players using"), Utils.chat("&b/tokens send <player> <amount>")));
		i.setItemMeta(im);
		return i;
	}
}
