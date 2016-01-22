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
import de.kekshaus.cookieApi.chat.Chatplugin;
import de.kekshaus.cookieApi.guild.objects.Guild;
import de.kekshaus.cookieApi.guild.objects.GuildPlayer;
import de.kekshaus.cookieApi.guild.objects.ObjectMapping;

public class GuildChat implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public GuildChat(Chatplugin instance) {

	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("cookieApi.chat.gchat")) {
			this.executorServiceCommands.submit(new Runnable() {
				public void run() {
					GuildPlayer gPlayer = ObjectMapping.getGuildPlayer(sender.getName());
					Guild guild = gPlayer.getGuild();
					if (guild == null) {
						sender.sendMessage("Du bist in keiner Gilde!");
						return;
					}
					if (args.length == 0) {
						sender.sendMessage("Du musst einen Text eingeben!");
						return;
					}

					String text = "";
					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						text = text + arg;
					}

					ChatApi.guildChat(guild.getGuildName(), sender.getName(), text);

				}
			});
		} else {
			sender.sendMessage(MessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
