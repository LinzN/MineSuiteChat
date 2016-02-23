package de.kekshaus.cookieApi.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.GlobalMessageDB;
import de.kekshaus.cookieApi.chat.Chatplugin;
import de.kekshaus.cookieApi.chat.api.CHStreamOutApi;

public class StaffChat implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public StaffChat(Chatplugin instance) {

	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("cookieApi.chat.team")) {
			this.executorServiceCommands.submit(new Runnable() {
				public void run() {
					if (args.length == 0) {
						CHStreamOutApi.channelSwitch(sender.getName(), "STAFF");
						sender.sendMessage("§aDu schreibst jetzt im TeamChat!");
						return;
					}
					String text = "";
					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						text = text + arg;
					}
					String prefix = Chatplugin.inst().getVaultData().getPrefix(player).replace("&", "§");
					String suffix = Chatplugin.inst().getVaultData().getSuffix(player).replace("&", "§");
					CHStreamOutApi.channelChat(sender.getName(), text, prefix, suffix, "STAFF", "none");

				}
			});
		} else {
			sender.sendMessage(GlobalMessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
