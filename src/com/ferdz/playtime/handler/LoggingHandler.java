package com.ferdz.playtime.handler;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ferdz.playtime.PlayTime;

public class LoggingHandler implements Listener {

	@EventHandler
	public void onPlayerLoggin(PlayerJoinEvent event) {
		PlayTime.players.put(event.getPlayer().getUniqueId(), new Date());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		UUID uuid = event.getPlayer().getUniqueId();
		PlayTime.instance.getConfig().getConfigurationSection("players").getConfigurationSection(uuid.toString()).set("time", PlayTime.getTotalPlayTime(uuid));
		PlayTime.instance.saveConfig();
	}
}
