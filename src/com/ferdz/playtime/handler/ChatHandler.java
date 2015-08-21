package com.ferdz.playtime.handler;

import java.util.concurrent.TimeUnit;

import net.minecraft.server.v1_8_R3.EnumChatFormat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.ferdz.playtime.PlayTime;

public class ChatHandler implements Listener {

	@EventHandler
	public void onChatMessage(AsyncPlayerChatEvent event) {
		event.setFormat(EnumChatFormat.RED + "["+ TimeUnit.SECONDS.toHours(PlayTime.getTotalPlayTime(event.getPlayer().getUniqueId())) +"h]" + EnumChatFormat.RESET+ " %s %s");
	}
}
