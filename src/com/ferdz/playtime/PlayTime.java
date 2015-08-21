package com.ferdz.playtime;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.ferdz.playtime.handler.ChatHandler;
import com.ferdz.playtime.handler.LoggingHandler;

public class PlayTime extends JavaPlugin {

	public static PlayTime instance;
	public static HashMap<UUID, Date> players;

	@Override
	public void onEnable() {
		instance = this;

		if (this.getConfig().getConfigurationSection("players") == null) {
			this.getConfig().createSection("players").createSection("0a08b956-8944-4cd9-b237-4f278a7aa560").set("time", 0);
		}

		this.saveConfig();

		populatePlayers();

		getServer().getPluginManager().registerEvents(new LoggingHandler(), this);
		getServer().getPluginManager().registerEvents(new ChatHandler(), this);
//		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("treied");
//				for (Player player : Bukkit.getOnlinePlayers()) {
//					Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
//					Objective objective = board.registerNewObjective("time", "dummy");
//					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//					objective.setDisplayName("hrs");
//					Score score = objective.getScore(ChatColor.GREEN + "time"); 
//					score.setScore((int) TimeUnit.SECONDS.toHours(PlayTime.getTotalPlayTime(player.getUniqueId())));
//					player.setScoreboard(board);
//				}
//			}
//		}, 20 * 3, 20 * 60);
	}

	private void populatePlayers() {
		players = new HashMap<UUID, Date>();
	}
	
	public static long getTotalPlayTime(UUID uuid) {
		ConfigurationSection cs = PlayTime.instance.getConfig().getConfigurationSection("players");
		if(!cs.contains(uuid.toString())) {
			cs.createSection(uuid.toString());
		}
		return (new Date().getTime() - PlayTime.players.get(uuid).getTime()) / 1000 + cs.getConfigurationSection(uuid.toString()).getLong("time");
	}
}
