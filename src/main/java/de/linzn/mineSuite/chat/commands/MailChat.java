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

import de.linzn.mineSuite.chat.socket.JClientChatOutput;
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MailChat implements CommandExecutor {
    public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
        final Player player = (Player) sender;
        this.executorServiceCommands.submit(() -> {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("send")) {
                    sendMail(player, args);
                } else if (args[0].equalsIgnoreCase("show")) {
                    showMail(player, args);
                } else if (args[0].equalsIgnoreCase("list")) {
                    listMail(player, args);
                } else if (args[0].equalsIgnoreCase("delete")) {
                    deleteMail(player, args);
                } else {
                    mailHelp(player);
                }
            } else {
                mailHelp(player);
            }
        });

        return false;
    }

    private void deleteMail(Player player, String[] args) {
        if (!player.hasPermission("mineSuite.chat.mail.delete")) {
            player.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
        }

        if (args.length < 2) {
            player.sendMessage("Wrong usage: /mail delete <mailid>");
            return;
        }
        int mailId = Integer.parseInt(args[1]);

        JClientChatOutput.deleteMail(player.getUniqueId(), mailId);

    }

    private void listMail(Player player, String[] args) {
        if (!player.hasPermission("mineSuite.chat.mail.list")) {
            player.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
        }

        int page = 1;
        if (args.length >= 2) {
            page = Integer.parseInt(args[1]);
        }
        if (page <= 0) {
            page = 1;
        }
        System.out.println("Page" + "->" + page);
        JClientChatOutput.listMails(player.getUniqueId(), page);

    }

    private void sendMail(Player player, String[] args) {
        if (!player.hasPermission("mineSuite.chat.mail.send")) {
            player.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
        }

        if (args.length < 3) {
            player.sendMessage("Wrong usage: /mail send <Player> <text>");
            return;
        }

        String receiver = args[1];

        String input = "";

        for (int i = 2; i < args.length; i++) {
            String arg = args[i] + " ";
            input = input + arg;
        }
        JClientChatOutput.sendMail(player.getUniqueId(), receiver, input);
    }

    private void showMail(Player player, String[] args) {
        if (!player.hasPermission("mineSuite.chat.mail.show")) {
            player.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
        }

        if (args.length < 2) {
            player.sendMessage("Wrong usage: /mail show <mailid>");
            return;
        }
        int mailId = Integer.parseInt(args[1]);

        JClientChatOutput.showMail(player.getUniqueId(), mailId);
    }

    private void mailHelp(Player player) {
        if (!player.hasPermission("mineSuite.chat.mail.help")) {
            player.sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
        }

        player.sendMessage("/mail send <Player> <Text> - Send Mail");
        player.sendMessage("/mail list - Show mails");
        player.sendMessage("/mail show <Mailid> - Show a mail");
        player.sendMessage("/mail delete <Mailid> - Delete a mail");
    }
}
