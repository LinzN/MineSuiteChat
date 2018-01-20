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

package de.linzn.mineSuite.chat.utils;

import de.linzn.mineSuite.chat.ChatPlugin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;

public class VaultAccess {

    public String getPrefix(Player player) {
        Chat chat = ChatPlugin.inst().getChat();
        String group = chat.getPrimaryGroup(player);

        if (chat.getPlayerPrefix(player) != null) {
            return chat.getPlayerPrefix(player);
        } else if (chat.getGroupPrefix(player.getWorld(), group) != null) {
            return chat.getGroupPrefix(player.getWorld(), group);
        }

        return "";
    }

    public String getSuffix(Player player) {
        Chat chat = ChatPlugin.inst().getChat();
        String group = chat.getPrimaryGroup(player);

        if (chat.getPlayerSuffix(player) != null) {
            return chat.getPlayerSuffix(player);
        } else if (chat.getGroupSuffix(player.getWorld(), group) != null) {
            return chat.getGroupSuffix(player.getWorld(), group);
        }

        return "";
    }

    public String getGroup(Player player) {
        Chat chat = ChatPlugin.inst().getChat();
        return chat.getPrimaryGroup(player);
    }

}
