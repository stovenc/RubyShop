package me.stovenc.rubyshop.utils;
import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import me.stovenc.rubyshop.Core;

public class Utils {
	static NumberFormat formatter = NumberFormat.getInstance(Locale.US);
	static Core plugin = Core.hook();

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static String getTokenBal(OfflinePlayer p) {
		return formatter.format(plugin.getPlayerCurrency(p));
	}
	
	public static String balFormat(int num) {
		return formatter.format(num);
	}
	
}

