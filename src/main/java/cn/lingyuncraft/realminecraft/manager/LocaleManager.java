package cn.lingyuncraft.realminecraft.manager;

import cn.lingyuncraft.realminecraft.RealMinecraft;
import cn.lingyuncraft.realminecraft.enums.MessageType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocaleManager {

    private static LocaleManager instance;

    public static LocaleManager getInstance() {
        if(instance == null) {
            instance = new LocaleManager();
        }
        return instance;
    }

    private File dataFile = new File(RealMinecraft.getInstance().getDataFolder() + File.separator + "Language.yml");
    @Getter private FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);

    private String prefix;
    private String info;
    private String warn;
    private String error;
    private String debug;
    private String targetMessageText;
    private String debugPrefix;

    public void loadLanguage() {
        if(!dataFile.exists()) {
            RealMinecraft.getInstance().saveResource("Language.yml", true);
            data = YamlConfiguration.loadConfiguration(dataFile);
            Bukkit.getLogger().info("  > 未找到语言文件, 已自动生成.");
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        Bukkit.getLogger().info("  > 已加载语言文件.");
    }

    public String getMessage(MessageType messageType, String section, String path) {
        prefix = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.Prefix"));
        targetMessageText = ChatColor.translateAlternateColorCodes('&', data.getConfigurationSection(section).getString(path));
        switch (messageType) {
            case INFO:
                info = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.INFO"));
                return prefix + info + targetMessageText;
            case WARN:
                warn = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.WARN"));
                return prefix + warn + targetMessageText;
            case ERROR:
                error = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.ERROR"));
                return prefix + error + targetMessageText;
            case DEBUG:
                debug = ChatColor.translateAlternateColorCodes('&', "&d&l&o>> ");
                return prefix + debug + targetMessageText;
            default:
                return prefix + ChatColor.translateAlternateColorCodes('&', "&7语言信息读取遇到问题.");
        }
    }

    /*public void debug(String message) {
        if(CityLifeMood.getInstance().isDebugMode()) {
            Bukkit.getLogger().info(ChatColor.stripColor(buildMessage(MessageType.DEBUG, message)));
        }
    }*/

    public String buildMessage(MessageType messageType, String message) {
        prefix = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.Prefix"));
        debugPrefix = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.Prefix").replace(" ", "") + "&9(&d&lDEBUG&9) ");
        targetMessageText = ChatColor.translateAlternateColorCodes('&', message);
        switch (messageType) {
            case INFO:
                info = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.INFO"));
                return prefix + info + targetMessageText;
            case WARN:
                warn = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.WARN"));
                return prefix + warn + targetMessageText;
            case ERROR:
                error = ChatColor.translateAlternateColorCodes('&', data.getString("Plugin.ERROR"));
                return prefix + error + targetMessageText;
            case DEBUG:
                debug = ChatColor.translateAlternateColorCodes('&', "&d&l&o>> ");
                return debugPrefix + debug + targetMessageText;
            default:
                return prefix + ChatColor.translateAlternateColorCodes('&', "&7语言信息构建遇到问题.");
        }
    }

    public String getString(String section, String path) {
        return ChatColor.translateAlternateColorCodes('&', data.getConfigurationSection(section).getString(path));
    }
}
