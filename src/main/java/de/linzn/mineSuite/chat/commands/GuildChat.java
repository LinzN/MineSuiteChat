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
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GuildChat implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>());

	public GuildChat(ChatPlugin instance) {

	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("mineSuite.chat.gchat")) {
			this.executorServiceCommands.submit(() -> {
 /*             GuildPlayer gPlayer = GuildData.getGuildPlayer(sender.getName());
                Guild guild = gPlayer.getGuild();
                if (guild == null) {
                    sender.sendMessage(ChatColor.GOLD + "Du bist in keiner Gilde!");
                    return;
                }
                if (args.length == 0) {
                    JClientChatOutput.channelSwitch(sender.getName(), "GUILD");
                    sender.sendMessage(ChatColor.GREEN + "Du schreibst jetzt im GildenChat!");
                    return;
                }

                String text = "";
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i] + " ";
                    text = text + arg;
                }

                JClientChatOutput.channelChat(sender.getName(), text, "none", "none", "GUILD", guild.getGuildName());
*/
			});
		} else {
            sender.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
		}
		return false;
	}
}
