package de.nlinz.xeonSuite.chat.database;

import java.util.HashMap;

public class ChatHASHDB {
	private static HashMap<String, Boolean> isafk = new HashMap<String, Boolean>();

	public static boolean isAfk(String player) {
		if (isafk.containsKey(player)) {
			return true;
		}
		return false;

	}

	public static void setAfk(String player, boolean value) {
		if (value) {
			isafk.put(player, true);
		} else {
			isafk.remove(player);
		}
	}

}