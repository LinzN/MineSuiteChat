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

package de.linzn.mineSuite.chat.api;

import de.linzn.mineLib.title.MineTitle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class ChatManager {

    public static void sendStaffChatMsg(String text) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("mineSuite.chat.staff")) {
                p.sendMessage(text);
            }
        }
        Bukkit.getConsoleSender().sendMessage("STAFF" + "-> " + text);

    }

    public static void sendTitleMsg(String title, String subTitle, int time){
        new MineTitle(title, subTitle, 20, time * 20, 40).sendToAll();
    }

}
