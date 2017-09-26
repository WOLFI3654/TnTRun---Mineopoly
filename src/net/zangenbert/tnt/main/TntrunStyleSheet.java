package net.zangenbert.tnt.main;

import java.util.UUID;

import org.bukkit.Location;

import de.wolfi.minopoly.MinigameRegistry.MinigameStyleSheet;
import de.wolfi.minopoly.components.minigames.MinigameHook;
import net.zangenbert.tnt.listener.GameListener;

public class TntrunStyleSheet implements MinigameStyleSheet{
	public static final UUID UUID = java.util.UUID.fromString("ef0978c7-97ab-49b3-8f7c-34e8a52d2367");
	
	
	private Location lobby = null;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TnT Run";
	}

	@Override
	public String getShortDesc() {
		// TODO Auto-generated method stub
		return "Versuche nicht runter zu fallen!";
	}

	@Override
	public Class<? extends MinigameHook> getClazz() {
		// TODO Auto-generated method stub
		return GameListener.class;
	}

	@Override
	public int getMinPlayer() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getMaxPlayers() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Location getLobbyLocation() {
		return lobby!=null?lobby:(lobby = Location.deserialize(Main.plugin.cfg.getConfigurationSection("map").getValues(false)));
	}

	@Override
	public UUID getUniqIdef() {
		return TntrunStyleSheet.UUID;
	}

}
