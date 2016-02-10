package de.kekshaus.cookieApi.chat;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.utils.Mapped;
import de.kekshaus.cookieApi.chat.events.SendBungeeChatMessage;
import de.kekshaus.cookieApi.guild.database.DataBaseHASHApi;
import de.kekshaus.cookieApi.guild.objects.Guild;
import de.kekshaus.cookieApi.guild.objects.GuildPlayer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatApi {

	public static void guildChat(String guild, String sender, String text) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("GuildChat");
			out.writeUTF(guild);
			out.writeUTF(sender);
			out.writeUTF(text);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

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

	public static void channelChat(String sender, String rawtext, String prefix, String suffix, String channel) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ChannelChat");
			out.writeUTF(sender);
			out.writeUTF(rawtext);
			out.writeUTF(prefix);
			out.writeUTF(suffix);
			out.writeUTF(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

	public static void privateMsg(String sender, String receiver, String text, String prefix) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PrivateMsg");
			out.writeUTF(sender);
			out.writeUTF(receiver);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

	public static void privateReply(String sender, String text, String prefix) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PrivateReply");
			out.writeUTF(sender);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

	public static void channelSwitch(String sender, String channel) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ChannelSwitch");
			out.writeUTF(sender);
			out.writeUTF(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

	public static void sendStaffChatMsg(String text) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("cookieApi.chat.staff")) {
				p.sendMessage(text);
			}
		}
		Bukkit.getConsoleSender().sendMessage("STAFF" + "-> " + text);

	}

	public static void setAfk(String sender, boolean value) {
		Mapped.setAfk(sender, value);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SetAfk");
			out.writeUTF(sender);
			out.writeBoolean(value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

	public static void setSocialSpy(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SocialSpy");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeChatMessage(b));
	}

}
