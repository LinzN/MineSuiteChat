package de.kekshaus.cookieApi.chat.events;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.chat.ChatApi;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("ServerStreamChat")
public class ServerStreamChatListener extends AbstractPacketListener {
	@PacketHandler
	public void onCookieApiMessageEvent(final ServerStreamChatEvent event) {
		CookieApiBukkit.getInstance().getServer().getScheduler().runTask(CookieApiBukkit.getInstance(), new Runnable() {

			public void run() {
				DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
				String servername = null;
				String ServerConfigName = CookieApiBukkit.getServerName();
				String task = null;
				try {
					servername = in.readUTF();
					if (!servername.equalsIgnoreCase("ALL")) {
						if (!servername.equalsIgnoreCase(ServerConfigName)) {
							return;
						}
					}

					task = in.readUTF();

					if (task.equals("GuildChat")) {
						String guild = in.readUTF();
						String sender = in.readUTF();
						String text = in.readUTF();
						ChatApi.sendguildChatMsg(guild, text);
					}

					if (task.equals("StaffChat")) {
						String text = in.readUTF();
						ChatApi.sendStaffChatMsg(text);
					}

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		});
	}

}