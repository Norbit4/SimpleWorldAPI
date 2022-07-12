package pl.norbit.simpleworldapi.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String format(String message){
       return ChatColor.translateAlternateColorCodes('&',message);
    }
}
