package cn.lingyuncraft.realminecraft.utils;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommonUtil {
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[一-龥]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("\\d{1,4}");
        return pattern.matcher(str).matches();
    }

    public static Object getKey(Map map, Object value){
        List<Object> keyList = new ArrayList<>();
        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                keyList.add(key);
            }
        }
        return keyList;
    }

    public static List<Player> getOnlinePlayers() {
        // 实例化两个List用于存放Player和World
        List<Player> players = Lists.newArrayList();
        List<World> worlds = Lists.newArrayList();
        worlds.addAll(Bukkit.getWorlds());
        // 遍历所有的世界
        for (int i = 0; i < worlds.size(); i++) {
            // 如果第i个世界的玩家是空的则进行下一次循环
            if (worlds.get(i).getPlayers().isEmpty()) {
                continue;
            } else {
                // 不是空的则添加到players集合中
                players.addAll(worlds.get(i).getPlayers());
            }
        }
        return players;
    }

    public static boolean isSnowy(Location loc) {
        World world = loc.getWorld();
        if(world.hasStorm()) {
            switch(world.getBiome((int) loc.getX(), (int) loc.getZ())) {
                case COLD_BEACH:
                case TAIGA_COLD:
                case TAIGA_COLD_HILLS:
                case MUTATED_TAIGA_COLD:
                    return true;
                default:
                    if((int) loc.getY() >= 95) {
                        return true;
                    }
                    return false;
            }
        } else {
            return false;
        }
    }

    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
