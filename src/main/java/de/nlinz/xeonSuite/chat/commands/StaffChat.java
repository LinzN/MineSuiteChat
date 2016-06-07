package de.nlinz.xeonSuite.chat.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.nlinz.xeonSuite.bukkit.utils.languages.GlobalLanguage;
import de.nlinz.xeonSuite.chat.Chatplugin;
import de.nlinz.xeonSuite.chat.api.CHStreamOutApi;

public class StaffChat implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public StaffChat(Chatplugin instance) {

	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("xeonSuite.chat.team")) {
			this.executorServiceCommands.submit(new Runnable() {
				@Override
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
			sender.sendMessage(GlobalLanguage.NO_PERMISSIONS);
		}
		return false;
	}
}
