package de.nlinz.xeonSuite.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nlinz.xeonSuite.bukkit.utils.languages.GlobalLanguage;
import de.nlinz.xeonSuite.bukkit.utils.tables.ChatDataTable;
import de.nlinz.xeonSuite.chat.Chatplugin;
import de.nlinz.xeonSuite.chat.api.CHStreamOutApi;

public class Afk implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public Afk(Chatplugin instance) {

	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("xeonSuite.chat.afk")) {
			this.executorServiceCommands.submit(new Runnable() {
				@Override
				public void run() {
					if (ChatDataTable.isAfk(player.getName())) {
						CHStreamOutApi.setAfk(player.getName(), false);
						player.sendMessage("§aDu bist nicht mehr AFK!");
					} else {
						CHStreamOutApi.setAfk(player.getName(), true);
						player.sendMessage("§aDu bist jetzt AFK!");
					}
				}
			});
		} else {
			sender.sendMessage(GlobalLanguage.NO_PERMISSIONS);
		}
		return false;
	}
}
