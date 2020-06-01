package me.stovenc.rubyshop.commands;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import me.stovenc.rubyshop.Core;
import me.stovenc.rubyshop.utils.CreateCustomItems;
import me.stovenc.rubyshop.utils.Utils;

public class RubyShopCommands implements CommandExecutor {
	Core plugin = Core.hook();

	Inventory inv;

	@SuppressWarnings({ "deprecation" })
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if (!(s instanceof Player)) {
			s.sendMessage(Utils.chat("&4You can't perform this command!"));
			return true;
		}
		if (a.length == 0) {
			s.sendMessage(Utils.chat("&e/ruby <bal:pay:shop:baltop:set:add:remove>"));
			return true;
		} else if (a.length == 1) {
			OfflinePlayer pl = (OfflinePlayer) s;
			if (a[0].equalsIgnoreCase("shop")) {
				if (s.hasPermission("ruby.shop")) {
					openShop(pl);
					return false;
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
					return false;
				}
			} else if (a[0].equalsIgnoreCase("add")) {
				if (s.hasPermission("ruby.add")) {
					s.sendMessage(Utils.chat("&e/ruby add <player> <amount>"));
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("top")) {
				if (s.hasPermission("ruby.baltop")) {
					Map<UUID, Integer> hm1 = sortByValue(plugin.coinData);
					int i = 0;
					int maxpages = (hm1.size() / 10) + 1;
					s.sendMessage(Utils.chat("&d________[ &b&lRuby Top &f<" + "1" + "/" + maxpages + ">&d]________"));
					for (Entry<UUID, Integer> en : hm1.entrySet()) {
						if (i == 10)
							break;
						s.sendMessage(ChatColor.AQUA + "" + (i + 1) + ". " + ChatColor.WHITE
								+ getName(en.getKey().toString()) + ": " + ChatColor.GREEN + en.getValue());
						i++;
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("remove")) {
				if (s.hasPermission("ruby.remove")) {
					s.sendMessage(Utils.chat("&e/ruby remove <player> <amount>"));
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("set")) {
				if (s.hasPermission("ruby.set")) {
					s.sendMessage(Utils.chat("&e/ruby set <player> <amount>"));
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("pay")) {
				if (s.hasPermission("ruby.pay")) {
					s.sendMessage(Utils.chat("&e/ruby pay <player>"));
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("bal")) {
				if (s.hasPermission("ruby.bal")) {
					s.sendMessage(
							Utils.chat("&6" + pl.getName() + " &3Ruby Balance: &6" + plugin.getPlayerCurrency(pl)));
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else {
				s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
			}
		} else if (a.length == 2) {
			if (a[0].equalsIgnoreCase("bal")) {
				if (s.hasPermission("ruby.bal")) {
					OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
					if ((p != null) && (p.hasPlayedBefore())) {
						s.sendMessage(
								Utils.chat("&6" + p.getName() + " &3Ruby Balance: &6" + plugin.getPlayerCurrency(p)));
						return true;
					} else {
						s.sendMessage(Utils.chat("&4Player does not exist!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You don't have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("top")) {
				if (s.hasPermission("ruby.baltop")) {
					if (isInteger(a[1])) {
						int pagenumber = Integer.parseInt(a[1]);
						Map<UUID, Integer> hm1 = sortByValue(plugin.coinData);
						int maxpages = (hm1.size() / 10) + 1;
						int i = 0;

						if (!(pagenumber > maxpages)) {
							s.sendMessage(Utils.chat(
									"&d________[ &b&lRuby Top &f<" + pagenumber + "/" + maxpages + ">&d]________"));
							for (Entry<UUID, Integer> en : hm1.entrySet()) {
								if (i == pagenumber * 10)
									break;
								if (i > (pagenumber * 10) - 10) {
									s.sendMessage(ChatColor.AQUA + "" + (i + 1) + ". " + ChatColor.WHITE
											+ getName(en.getKey().toString()) + ": " + ChatColor.GREEN + en.getValue());
								}
								i++;
							}
						} else {
							s.sendMessage(Utils.chat("&4No more pages!"));
						}
					} else {
						s.sendMessage(Utils.chat("&4You must use a number!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else {
				s.sendMessage(Utils.chat("&e/ruby <add:remove:set:bal:pay:shop>"));
			}
		} else if (a.length == 3) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(a[1]);
			OfflinePlayer p2 = (OfflinePlayer) s;

			if (a[0].equalsIgnoreCase("add")) {
				if (s.hasPermission("ruby.add")) {
					if (p != null && p.hasPlayedBefore() && isInteger(a[2])) {
						int amount = Integer.parseInt(a[2]);
						if (amount > 0) {
							plugin.addCurrencyToPlayer(p, amount);
							s.sendMessage(Utils
									.chat("&aYou have successfully added &6" + a[2] + " Rubies &ato &e" + p.getName()));
						} else if (amount < 0) {
							s.sendMessage(Utils.chat("&4Amount can't be negative!"));
						}
					} else if (!isInteger(a[2])) {
						s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
					} else {
						s.sendMessage(Utils.chat("&ePlayer &c" + a[1] + " &ecould not be found!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("pay")) {
				if (s.hasPermission("ruby.pay")) {
					if (p.getName() == s.getName()) {
						s.sendMessage(Utils.chat("&4You can't pay yourself!"));
					} else if (p != null && p.hasPlayedBefore() && isInteger(a[2])) {
						int amount = Integer.parseInt(a[2]);
						if (plugin.getPlayerCurrency(p2) >= amount && amount > 0) {
							Player ptemp = (Player) p;
							plugin.removeCurrencyFromPlayer(p2, amount);
							plugin.addCurrencyToPlayer(p, amount);
							ptemp.sendMessage(Utils.chat("&aYou have received &6" + amount + " Rubies&a!"));
							s.sendMessage(Utils.chat("&aYou have paid &6" + p.getName() + " &6$" + amount + "&a!"));
						} else if (amount < 0) {
							s.sendMessage(Utils.chat("&4Amount can't be negative!"));
						} else if (!isInteger(a[2])) {
							s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
						} else {
							s.sendMessage(Utils.chat("&eYou don't have enough money!"));
						}
					} else {
						s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("remove")) {
				if (s.hasPermission("ruby.remove")) {
					if (p != null && p.hasPlayedBefore() && isInteger(a[2])) {
						int amount = Integer.parseInt(a[2]);
						if (plugin.getPlayerCurrency(p) >= amount && amount > 0) {
							plugin.removeCurrencyFromPlayer(p, amount);
							s.sendMessage(Utils.chat(
									"&aYou have successfully removed &6" + a[2] + " Rubies &afrom &e" + p.getName()));
						} else if (amount < 0) {
							s.sendMessage(Utils.chat("&4Amount can't be negative!"));
						} else {
							s.sendMessage(Utils.chat("&4Player does not have that much!"));
						}
					} else if (!isInteger(a[2])) {
						s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
					} else {
						s.sendMessage(Utils.chat("&ePlayer &c" + a[1] + " &ecould not be found!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("set")) {
				if (s.hasPermission("ruby.set")) {
					if (p != null && p.hasPlayedBefore() && isInteger(a[2])) {
						int amount = Integer.parseInt(a[2]);
						if (amount >= 0) {
							plugin.setPlayerCurrency(p, amount);
							s.sendMessage(
									Utils.chat("&6" + p.getName() + " &6Ruby &abalance set to &6" + a[2] + "&a!"));
						} else if (amount < 0) {
							s.sendMessage(Utils.chat("&4Amount can't be negative!"));
						}
					} else if (!isInteger(a[2])) {
						s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
					} else {
						s.sendMessage(Utils.chat("&ePlayer &c" + a[1] + " &ecould not be found!"));
					}
				} else {
					s.sendMessage(Utils.chat("&4You do not have permission to execute this command!"));
				}
			} else if (a[0].equalsIgnoreCase("bal")) {
				s.sendMessage(Utils.chat("&4Incorrect Syntax!"));
			} else {
				s.sendMessage(Utils.chat("&e/ruby <add:remove:set> <player> <amount>"));
			}
		} else if (a.length > 3) {
			s.sendMessage(Utils.chat("&e/ruby <add:remove:set> <player> <amount>"));
		}
		return false;
	}

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public void openShop(OfflinePlayer s) {
		Player p = (Player) s;
		ItemStack testItem = new ItemStack(Material.DIAMOND);
		ItemMeta tiMeta = testItem.getItemMeta();
		tiMeta.setDisplayName(Utils.chat("&3Dismond &9- &7(Click to Purachse)"));
		tiMeta.setLore(Arrays.asList(Utils.chat("&cPrice &7(in coins)"), "10"));

		testItem.setItemMeta(tiMeta);
		inv = Bukkit.createInventory(null, 27, "Shop");
		inv.setItem(1, CreateCustomItems.createRubyHelm());
		p.openInventory(inv);
	}

	public HashMap<UUID, Integer> sortByValue(Map<UUID, Integer> coinData) {
		// Create a list from elements of HashMap
		List<Map.Entry<UUID, Integer>> list = new LinkedList<Map.Entry<UUID, Integer>>(coinData.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<UUID, Integer>>() {
			public int compare(Map.Entry<UUID, Integer> o1, Map.Entry<UUID, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Collections.reverse(list);

		// put data from sorted list to hashmap
		HashMap<UUID, Integer> temp = new LinkedHashMap<UUID, Integer>();
		for (Map.Entry<UUID, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public String getName(String uuid) {
		String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
		try {
			String nameJson = IOUtils.toString(new URL(url));
			JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
			String playerSlot = nameValue.get(nameValue.size() - 1).toString();
			JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
			return nameObject.get("name").toString();
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return "error";
	}
}
