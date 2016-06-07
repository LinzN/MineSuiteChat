package de.nlinz.xeonSuite.chat.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.nlinz.xeonSuite.guild.database.DataBaseHASHApi;
import de.nlinz.xeonSuite.guild.objects.Guild;
import de.nlinz.xeonSuite.guild.objects.GuildPlayer;

public class CHStreamInApi {

	public static void sendguildChatMsg(String gname, String text) {
		Guild guild = DataBaseHASHApi.getGuildHASH(gname);
		if (guild != null) {
			Bukkit.getConsoleSender().sendMessage(guild.getGuildName() + "-> " + text);
			for (GuildPlayer gp : DataBaseHASHApi.getOnlineGuildPlayers()) {
				if (gp.getGuild() == guild) {
					gp.sendText(text);
				}

			}

		}

	}

	public static void sendStaffChatMsg(String text) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("xeonSuite.chat.staff")) {
				p.sendMessage(text);
			}
		}
		Bukkit.getConsoleSender().sendMessage("STAFF" + "-> " + text);

	}

}
