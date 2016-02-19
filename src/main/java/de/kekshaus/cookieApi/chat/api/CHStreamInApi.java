package de.kekshaus.cookieApi.chat.api;

import de.kekshaus.cookieApi.guild.database.DataBaseHASHApi;
import de.kekshaus.cookieApi.guild.objects.Guild;
import de.kekshaus.cookieApi.guild.objects.GuildPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
			if (p.hasPermission("cookieApi.chat.staff")) {
				p.sendMessage(text);
			}
		}
		Bukkit.getConsoleSender().sendMessage("STAFF" + "-> " + text);

	}

}
