package me.stovenc.rubyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.stovenc.rubyshop.commands.RubyShopCommands;
import me.stovenc.rubyshop.listeners.Entry;
import me.stovenc.rubyshop.listeners.InvClickEvent;
import me.stovenc.rubyshop.listeners.MobListener;

public class Core extends JavaPlugin {

	private static Core instance;

	public Map<UUID, Integer> coinData = new HashMap<UUID, Integer>();
	public Map<UUID, Entry> rubyUpdater = new HashMap<UUID, Entry>();

	@Override
	public void onEnable() {
		try {
			loadCurrencyFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instance = this;
		getCommand("ruby").setExecutor(new RubyShopCommands());
		getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
		getServer().getPluginManager().registerEvents(new MobListener(), this);
	}

	@Override
	public void onDisable() {
		try {
			saveCurrencyFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Core getInstance() {
		return instance;
	}

	public static Core hook() {
		return instance;
	}

	public void saveCurrencyFile() throws FileNotFoundException, IOException {

		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

			File file = new File("RubyCurrencyData/rubycurrency.dat");
			ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

			UUID uuid = p.getUniqueId();

			if (coinData.get(uuid) != null) {
				coinData.put(uuid, coinData.get(uuid));
			}

			try {
				output.writeObject(coinData);
				output.flush();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loadCurrencyFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("RubyCurrencyData/rubycurrency.dat");

		if (file != null) {

			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();

			if (!(readObject instanceof HashMap)) {
				throw new IOException("Data is not a hashmap");
			}
			coinData = (HashMap<UUID, Integer>) readObject;

			for (UUID key : coinData.keySet()) {
				coinData.put(key, coinData.get(key));
			}

		}

	}

	public void addCurrencyToPlayer(OfflinePlayer p, int amount) {
		if (coinData.get(p.getUniqueId()) != null) {
			coinData.put(p.getUniqueId(), coinData.get(p.getUniqueId()) + amount);
		} else {
			coinData.put(p.getUniqueId(), amount);
		}
	}

	public void removeCurrencyFromPlayer(OfflinePlayer p, int amount) {
		if ((coinData.get(p.getUniqueId()) != null) && (coinData.get(p.getUniqueId()) - amount >= 0)) {
			coinData.put(p.getUniqueId(), coinData.get(p.getUniqueId()) - amount);
		}
	}

	
	public void setPlayerCurrency(OfflinePlayer p, int amount) {
		coinData.put(p.getUniqueId(), amount);
	}

	public int getPlayerCurrency(OfflinePlayer p) {
		if (coinData.get(p.getUniqueId()) != null) {
			return coinData.get(p.getUniqueId());
		} else {
			return 0;
		}
	}
	
	public Boolean getEntryCycle(Player p) {
		if (rubyUpdater.get(p.getUniqueId()) != null) {
			return rubyUpdater.get(p.getUniqueId()).getCycle();
		} return false;
	}
	
	public Integer getRubyFromMob(Player p) {
		if (rubyUpdater.get(p.getUniqueId()) != null) {
			return rubyUpdater.get(p.getUniqueId()).getAmount();
		} return 0;
	}

}
