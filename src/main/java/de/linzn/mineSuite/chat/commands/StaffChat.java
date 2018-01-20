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
import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class StaffChat implements CommandExecutor {
    public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    public StaffChat(ChatPlugin instance) {

    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
        final Player player = (Player) sender;
        if (player.hasPermission("mineSuite.chat.team")) {
            this.executorServiceCommands.submit(() -> {
                if (args.length == 0) {
                    JClientChatOutput.channelSwitch(sender.getName(), "STAFF");
                    sender.sendMessage("§aDu schreibst jetzt im TeamChat!");
                    return;
                }
                String text = "";
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i] + " ";
                    text = text + arg;
                }
                String prefix = ChatPlugin.inst().getVaultData().getPrefix(player).replace("&", "§");
                String suffix = ChatPlugin.inst().getVaultData().getSuffix(player).replace("&", "§");
                JClientChatOutput.channelChat(sender.getName(), text, prefix, suffix, "STAFF");

            });
        } else {
            sender.sendMessage(MineSuiteCorePlugin.getInstance().getMineConfigs().generalLanguage.NO_PERMISSIONS);
        }
        return false;
    }
}