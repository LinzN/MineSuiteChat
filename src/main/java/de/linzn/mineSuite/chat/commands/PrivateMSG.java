/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.mineSuite.chat.commands;

import de.linzn.mineSuite.chat.ChatPlugin;
import de.linzn.mineSuite.chat.socket.JClientChatOutput;
import de.linzn.mineSuite.chat.utils.VaultAccess;
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrivateMSG implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>());

	public PrivateMSG(ChatPlugin instance) {

	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("mineSuite.chat.pm")) {
			this.executorServiceCommands.submit(() -> {
				if (args.length <= 1) {
					sender.sendMessage(GeneralLanguage.chat_MSG_USAGE);
					return;
				}
				String receiver = args[0];
				String text = "";
				for (int i = 1; i < args.length; i++) {
					String arg = args[i] + " ";
					text = text + arg;
				}
				String prefix = VaultAccess.getPrefix(player).replace("&", "§");
				JClientChatOutput.privateMsg(player.getDisplayName(), receiver, text, prefix);

			});
		} else {
            sender.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
		}
		return false;
	}
}
