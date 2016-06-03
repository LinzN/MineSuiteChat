package de.nlinz.xeonSuite.chat.listener;

import java.io.DataInputStream;
import java.io.IOException;

import de.nlinz.javaSocket.client.api.XeonSocketClientManager;
import de.nlinz.javaSocket.client.events.SocketDataEvent;
import de.nlinz.javaSocket.client.interfaces.IDataListener;
import de.nlinz.xeonSuite.chat.api.CHStreamInApi;

public class XeonChat implements IDataListener {

	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return channelName;
	}

	public static String channelName = "xeonChat";

	@Override
	public void onDataRecieve(SocketDataEvent event) {
		// TODO Auto-generated method stub
		DataInputStream in = XeonSocketClientManager.readDataInput(event.getStreamBytes());
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
