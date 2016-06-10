package de.nlinz.xeonSuite.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nlinz.xeonSuite.bukkit.utils.languages.GeneralLanguage;
import de.nlinz.xeonSuite.chat.Chatplugin;
import de.nlinz.xeonSuite.chat.api.CHStreamOutApi;

public class PrivateReply implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public PrivateReply(Chatplugin instance) {

	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("xeonSuite.chat.reply")) {
			this.executorServiceCommands.submit(new Runnable() {
				@Override
				public void run() {
					if (args.length == 0) {
						sender.sendMessage("Du musst einen Text eingeben!");
						return;
					}
					String text = "";
					for (int i = 0; i < args.length; i++) {
						String arg = args[i] + " ";
						text = text + arg;
					}
					String prefix = Chatplugin.inst().getVaultData().getPrefix(player).replace("&", "§");
					CHStreamOutApi.privateReply(player.getDisplayName(), text, prefix);

				}
			});
		} else {
			sender.sendMessage(GeneralLanguage.NO_PERMISSIONS);
		}
		return false;
	}
}
