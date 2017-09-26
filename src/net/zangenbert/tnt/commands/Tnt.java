package net.zangenbert.tnt.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zangenbert.tnt.main.Main;

public class Tnt implements CommandExecutor {
	
	private Main plugin;
	
	public Tnt(Main main) {
		
		plugin = main;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		Player p = (Player) sender;
		if(sender instanceof Player) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("setspawn")) {
						plugin.cfg.set("map.world", p.getWorld().getName());
						plugin.cfg.set("map.x", p.getLocation().getX());
						plugin.cfg.set("map.y", p.getLocation().getY());
						plugin.cfg.set("map.z", p.getLocation().getZ());
						plugin.cfg.set("map.yaw", p.getLocation().getYaw());
						plugin.cfg.set("map.pitch", p.getLocation().getPitch());
						
						try {
							plugin.cfg.save(plugin.file);
							p.sendMessage(plugin.pre + "§aDer Spawn wurde erfolgreich gesetzt");
						} catch(IOException e) {
							e.printStackTrace();
						}
						
					}
				} else {
					info(p);
				}
			
		}
		return true;
	}
	
	public void info(Player p) {
		p.sendMessage("§7----------[§6TNT Run§7]----------");
		p.sendMessage("§e✦ §7/tnt setspawn");
//		p.sendMessage("§e✦ §7/tnt <add/remove> <player>");
//		p.sendMessage("§e✦ §7/tnt <start/stop>");
		p.sendMessage("§7----------[§6TNT Run§7]----------");
	}
	
}
