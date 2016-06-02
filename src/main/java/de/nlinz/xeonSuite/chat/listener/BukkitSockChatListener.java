package de.nlinz.xeonSuite.chat.listener;

import java.io.DataInputStream;
import java.io.IOException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import de.keks.socket.bukkit.events.plugin.BukkitSockChatEvent;
import de.keks.socket.core.ByteStreamConverter;
import de.nlinz.xeonSuite.chat.api.CHStreamInApi;

public class BukkitSockChatListener implements Listener {

	@EventHandler
	public void onBukkitSockChatEvent(BukkitSockChatEvent e) {
		DataInputStream in = ByteStreamConverter.toDataInputStream(e.readBytes());
		String task = null;
		try {
			task = in.readUTF();

			if (task.equals("GuildChat")) {
				String guild = in.readUTF();
				String text = in.readUTF();
				CHStreamInApi.sendguildChatMsg(guild, text);
			}

			if (task.equals("StaffChat")) {
				String text = in.readUTF();
				CHStreamInApi.sendStaffChatMsg(text);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
