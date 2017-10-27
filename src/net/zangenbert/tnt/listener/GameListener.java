package net.zangenbert.tnt.listener;

import static net.zangenbert.tnt.main.Main.plugin;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import de.wolfi.minopoly.Main;
import de.wolfi.minopoly.components.minigames.Minigame;
import de.wolfi.minopoly.components.minigames.MinigameHook;
import de.wolfi.minopoly.utils.CancelConstants;
import de.wolfi.minopoly.utils.Messages;
import de.wolfi.utils.TimedEntity;

public class GameListener extends MinigameHook {
	private boolean started = false;
	private ArrayList<de.wolfi.minopoly.components.Player> alive;

	protected static volatile HashMap<Vector, Entry<Material, Byte>> restore = new HashMap<Vector, Entry<Material, Byte>>();

	public GameListener(Minigame minigame) {
		super(minigame);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (this.started) {
			final Player p = e.getPlayer();
			if (this.isAlive(p)) {
				final Location loc = p.getLocation().subtract(0.0, 1.0, 0.0);
				final Vector vec = p.getLocation().getDirection().normalize().multiply(-0.5D).setY(1.0D);

				if (loc.getBlock().getType() == Material.BEDROCK) {
					this.alive.remove(this.getBoard().getByBukkitPlayer(p));
					Messages.MINIGAME_DEATH.broadcast(this.getBoard().getByBukkitPlayer(p).getDisplay(),
							this.getName());
					// Bukkit.broadcastMessage(//XXX
					// plugin.pre + "§cDer Spieler §6" + p.getName() + "
					// §cist ausgeschieden");
					Bukkit.broadcastMessage(plugin.pre + "�e" + this.alive.size() + " �7Spieler verbleibend.");
					Vector v = p.getLocation().getDirection().multiply(0.1).setY(1.4D);
					p.setVelocity(v);
					Bukkit.getScheduler().runTaskLater(plugin, () -> {
						p.setAllowFlight(true);
						p.setFlying(true);
					}, 20);
					if (this.alive.size() == 1) {
						this.started = false;
						Bukkit.getScheduler().runTask(plugin, () -> win(this.alive.get(0)));
						return;
					}
				} else {
					if ((loc.getBlock().getType() == Material.STAINED_CLAY || loc.getBlock().getData() != (byte) 0)
							|| loc.getBlock().getType() == Material.AIR) {
						return;
					}
					restore.put(loc.toVector(), new AbstractMap.SimpleEntry<Material, Byte>(loc.getBlock().getType(),
							loc.getBlock().getData()));

					Bukkit.getScheduler().runTask(plugin, () -> loc.getBlock().setData((byte) 5));

					// GELB
					Bukkit.getScheduler().runTaskLater(plugin, () -> loc.getBlock().setData((byte) 4), 1 * 10);

					// ORANGE
					Bukkit.getScheduler().runTaskLater(plugin, () -> loc.getBlock().setData((byte) 1), 2 * 10);

					// ROT
					Bukkit.getScheduler().runTaskLater(plugin, () -> loc.getBlock().setData((byte) 14), 3 * 10);

					// LUFT
					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
						@Override
						public void run() {
							loc.getBlock().setType(Material.AIR);
							loc.add(0, 1, 0);
							loc.getBlock().setType(Material.STAINED_CLAY);
							loc.getBlock().setData((byte) 14);

							TimedEntity block = new TimedEntity(EntityType.FALLING_BLOCK, loc, 20);
							block.metadata(CancelConstants.CANCEL_BLOCK_CHANGE,
									new FixedMetadataValue(Main.getMain(), true));
							block.vector(vec);

						}
					}, 4 * 10);
				}

			}

		}

	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		e.setCancelled(true);
	}

	// @EventHandler
	// public void onQuit(PlayerQuitEvent e) {
	// Player p = e.getPlayer();
	// if(plugin.players.contains(p.getName())) {
	// plugin.players.remove(p.getName());
	// Bukkit.broadcastMessage(plugin.pre + "§cDer Spieler §6" + p.getName() +
	// " §chat den Server verlassen");
	// Bukkit.broadcastMessage(plugin.pre + "§e" + plugin.players.size() + "
	// §7Spieler verbleibend.");
	// }
	// if(plugin.players.size() == 1) {
	// Bukkit.broadcastMessage(plugin.pre + "§aDer Spieler §6" +
	// plugin.players.toString().replace("[", "").replace("]", "") + " §ahat
	// das Spiel gewonnen!");
	// plugin.InGame = false;
	// plugin.Events = false;
	// plugin.players.clear();
	// for(Player o : Bukkit.getOnlinePlayers()) {
	// o.playSound(o.getLocation(), Sound.LEVEL_UP, 1, 1);
	// }
	// }
	// }

	protected boolean isAlive(Player player) {
		return this.alive.contains(this.getBoard().getByBukkitPlayer(player));
	}

	@Override
	public void addPlayer(de.wolfi.minopoly.components.Player player) {
		player.getHook().setGameMode(GameMode.ADVENTURE);
		this.alive.add(player);
		super.addPlayer(player);
	}

	@Override
	public void start() {
		this.started = true;
	}

	@Override
	public void endGame() {

		resetMap();

		this.started = false;
	}

	private void resetMap() {

		for (Entry<Vector, Entry<Material, Byte>> entry : restore.entrySet()) {
			Block b = entry.getKey().toLocation(this.getMinigame().getLocation().getWorld()).getBlock();

			b.setType(entry.getValue().getKey(), false);
			b.setData(entry.getValue().getValue(), false);
		}

		restore.clear();

	}

	@Override
	protected void init() {
		this.alive = new ArrayList<>();
	}

}
