package net.zangenbert.tnt.listener;
//
//import org.bukkit.Bukkit;
//import org.bukkit.GameMode;
//import org.bukkit.Location;
//import org.bukkit.Sound;
//import org.bukkit.entity.Player;
//import org.bukkit.scheduler.BukkitTask;
//
//import net.zangenbert.tnt.main.Main;
//
public class Countdown {
//	
//	private Main plugin;
//	
//	public int count = 10;
//	
//	private BukkitTask task;
//	
//	public Countdown(Main main) {
//		
//		plugin = main;
//		
//	}
//	
//	public void startCountdown() {
//		if(!Bukkit.getScheduler().getPendingTasks().contains(task)) {
//			if(plugin.cfg.contains("map.World")) {
//				if(plugin.players.size() >= plugin.PlayerSize) {
//					plugin.Events = true;
//					Location loc = Location.deserialize(plugin.cfg.getConfigurationSection("map").getValues(false));
//					
//					for(Player o : Bukkit.getOnlinePlayers()) {
//						o.setGameMode(GameMode.ADVENTURE);
//						if(o.getGameMode() == GameMode.ADVENTURE) {
//							o.setAllowFlight(false);
//							o.setFlying(false);
//							o.setHealth(20);
//							o.setFoodLevel(20);
//						}
//					}
//					
//					
//					task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
//						@Override
//						public void run() {
//							if(count == 10 || count == 5 || count == 4 || count == 3 || count == 2) {
//								Bukkit.broadcastMessage(plugin.pre + "§7Das Spiel startet in §e" + count + " §7Sekunden");
//								for(Player o : Bukkit.getOnlinePlayers()) {
//									o.playSound(o.getLocation(), Sound.NOTE_BASS, 1, 1);
//								}
//							}
//							if(count == 1) {
//								Bukkit.broadcastMessage(plugin.pre + "§7Das Spiel startet in §e" + count + " §7Sekunde");
//								for(Player o : Bukkit.getOnlinePlayers()) {
//									o.playSound(o.getLocation(), Sound.NOTE_BASS, 1, 1);
//								}
//							}
//							if(count == 0) {
//								Bukkit.broadcastMessage(plugin.pre + "§aDas Spiel ist gestartet");
//								for(Player o : Bukkit.getOnlinePlayers()) {
//									o.playSound(o.getLocation(), Sound.NOTE_PLING, 1, 1);
//									o.setFoodLevel(20);
//								}
//								plugin.InGame = true;
//								task.cancel();
//								count =+ 11;
//							}
//							count--;
//						}
//					}, 0, 20);
//				} else {
//					p.sendMessage(plugin.pre + "§cEs müssen mindestens §ezwei §cSpieler eingetragen sein");
//				}
//			} else {
//				p.sendMessage(plugin.pre + "§cEs konnte kein Map-Spawn gefunden werden");
//			}
//		} else {
//			p.sendMessage(plugin.pre + "§cDas Spiel wurde bereits gestartet");
//		}
//	}
//	
//	
//	public void stopCountdown(Player p) {
//		if(Bukkit.getScheduler().getPendingTasks().contains(task)) {
//			task.cancel();
//			count =+ 10;
//			plugin.InGame = false;
//			plugin.Events = false;
//			p.sendMessage(plugin.pre + "§cDas Spiel wurde erfoglreich gestoppt");
//		} else {
//			p.sendMessage(plugin.pre + "§cMomentan läuft kein Spiel");
//		}
//	}
//	
}
