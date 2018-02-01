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

package de.linzn.mineSuite.chat.listener;

import de.linzn.mineSuite.chat.ChatPlugin;
import de.linzn.mineSuite.chat.socket.JClientChatOutput;
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import de.linzn.mineSuite.core.database.hashDatabase.ChatDataTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("mineSuite.chat.use")) {
                String playername = event.getPlayer().getDisplayName();
                String rawtext = event.getMessage();
                String prefix = ChatPlugin.inst().getVaultData().getPrefix(event.getPlayer()).replace("&", "§");
                String suffix = ChatPlugin.inst().getVaultData().getSuffix(event.getPlayer()).replace("&", "§");
                //String guildName = "NONE";
                //GuildPlayer gPlayer = GuildData.getGuildPlayer(event.getPlayer().getName());
                //Guild guild = gPlayer.getGuild();
                //if (guild != null) {
                //	guildName = guild.getGuildName();
                //}
                JClientChatOutput.channelChat(playername, rawtext, prefix, suffix, "NONE");
            } else {
                event.getPlayer().sendMessage(GeneralLanguage.global_NO_PERMISSIONS);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDisconnect(PlayerQuitEvent event) {
        if (ChatDataTable.isAfk(event.getPlayer().getName())) {
            JClientChatOutput.setAfk(event.getPlayer().getName(), false);
            event.getPlayer().sendMessage(GeneralLanguage.chat_AFK_ON);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if (event.getFrom() != event.getTo()) {
            if (ChatDataTable.isAfk(event.getPlayer().getName())) {
                if (!event.getPlayer().hasPermission("mineSuite.chat.bypass")) {
                    JClientChatOutput.setAfk(event.getPlayer().getName(), false);
                    event.getPlayer().sendMessage(GeneralLanguage.chat_AFK_OFF);
                }
            }
        }
    }

}
