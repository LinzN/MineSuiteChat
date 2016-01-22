package de.kekshaus.cookieApi.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.MessageDB;
import de.kekshaus.cookieApi.bukkit.managerApi.ChatApi;
import de.kekshaus.cookieApi.bukkit.utils.Mapped;
import de.kekshaus.cookieApi.chat.Chatplugin;

public class Afk implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public Afk(Chatplugin instance) {

	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("cookieApi.chat.afk")) {
			this.executorServiceCommands.submit(new Runnable() {
				public void run() {
					if (Mapped.isAfk(player.getName())) {
						ChatApi.setAfk(player.getName(), false);
						player.sendMessage("§aDu bist nicht mehr AFK!");
					} else {
						ChatApi.setAfk(player.getName(), true);
						player.sendMessage("§aDu bist jetzt AFK!");
					}
				}
			});
		} else {
			sender.sendMessage(MessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
