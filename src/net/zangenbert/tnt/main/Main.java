package net.zangenbert.tnt.main;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.wolfi.minopoly.MinigameRegistry;
import net.zangenbert.tnt.commands.Tnt;


public class Main extends JavaPlugin {
//	public Countdown cd = new Countdown(this);
	
	public static Main plugin;
	
	public String pre = "§8[§6TNT-Run§8]§r ";
		
	public File file = new File("plugins/TNT-Run", "config,yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public void onEnable() {
		Main.plugin = this;
		getServer().getConsoleSender().sendMessage("§7[§6TNT-Run§7] Pluginversion §e" + getDescription().getVersion() + " §7succesfully §aenabled");
		registerEvents();
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§7[§6TNT-Run§7] Pluginversion §e" + getDescription().getVersion() + " §7succesfully §cdisabled");
	}
	
	public void registerEvents() {
		MinigameRegistry.registerMinigame(new TntrunStyleSheet());		
		Tnt tnt = new Tnt(this);
		getCommand("tntrun").setExecutor(tnt);
	}
	
}
