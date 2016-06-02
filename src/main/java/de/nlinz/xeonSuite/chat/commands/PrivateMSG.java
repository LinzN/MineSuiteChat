package de.nlinz.xeonSuite.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nlinz.xeonSuite.bukkit.GlobalMessageDB;
import de.nlinz.xeonSuite.chat.Chatplugin;
import de.nlinz.xeonSuite.chat.api.CHStreamOutApi;

public class PrivateMSG implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public PrivateMSG(Chatplugin instance) {

	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("cookieApi.chat.pm")) {
			this.executorServiceCommands.submit(new Runnable() {
				public void run() {
					if (args.length == 0 && args.length == 1) {
						sender.sendMessage("Du musst einen Text eingeben!");
						return;
					}
					String receiver = args[0];
					String text = "";
					for (int i = 1; i < args.length; i++) {
						String arg = args[i] + " ";
						text = text + arg;
					}
					String prefix = Chatplugin.inst().getVaultData().getPrefix(player).replace("&", "§");
					CHStreamOutApi.privateMsg(player.getDisplayName(), receiver, text, prefix);

				}
			});
		} else {
			sender.sendMessage(GlobalMessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
