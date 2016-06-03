package de.nlinz.xeonSuite.chat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.nlinz.xeonSuite.bukkit.GlobalMessageDB;
import de.nlinz.xeonSuite.chat.Chatplugin;
import de.nlinz.xeonSuite.chat.api.CHStreamOutApi;
import de.nlinz.xeonSuite.chat.database.ChatDataTable;
import de.nlinz.xeonSuite.guild.database.GuildDataTable;
import de.nlinz.xeonSuite.guild.objects.Guild;
import de.nlinz.xeonSuite.guild.objects.GuildPlayer;

public class ChatListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent event) {
		if (!event.isCancelled()) {
			event.setCancelled(true);
			if (event.getPlayer().hasPermission("cookieApi.chat.use")) {
				String playername = event.getPlayer().getDisplayName();
				String rawtext = event.getMessage();
				String prefix = Chatplugin.inst().getVaultData().getPrefix(event.getPlayer()).replace("&", "§");
				String suffix = Chatplugin.inst().getVaultData().getSuffix(event.getPlayer()).replace("&", "§");
				String guildName = "NONE";
				GuildPlayer gPlayer = GuildDataTable.getGuildPlayer(event.getPlayer().getName());
				Guild guild = gPlayer.getGuild();
				if (guild != null) {
					guildName = guild.getGuildName();
				}

				CHStreamOutApi.channelChat(playername, rawtext, prefix, suffix, "NONE", guildName);
			} else {
				event.getPlayer().sendMessage(GlobalMessageDB.NO_PERMISSIONS);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onDisconnect(PlayerQuitEvent event) {
		if (ChatDataTable.isAfk(event.getPlayer().getName())) {
			CHStreamOutApi.setAfk(event.getPlayer().getName(), false);
			event.getPlayer().sendMessage("§aDu bist nicht mehr AFK!");
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMove(PlayerMoveEvent event) {
		if (event.getFrom() != event.getTo()) {
			if (ChatDataTable.isAfk(event.getPlayer().getName())) {
				if (!event.getPlayer().hasPermission("cookieApi.chat.bypass")) {
					CHStreamOutApi.setAfk(event.getPlayer().getName(), false);
					event.getPlayer().sendMessage("§aDu bist nicht mehr AFK!");
				}
			}
		}
	}

}
