package cn.lingyuncraft.realminecraft;

import cn.lingyuncraft.realminecraft.features.UnsafeSand;
import cn.lingyuncraft.realminecraft.listener.PlayerDeathListener;
import cn.lingyuncraft.realminecraft.listener.PlayerMoveListener;
import cn.lingyuncraft.realminecraft.manager.LocaleManager;
import cn.lingyuncraft.realminecraft.tasks.CheckPlayerFeetUnder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import cn.lingyuncraft.realminecraft.MetricsLite;

/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public final class RealMinecraft extends JavaPlugin {

    @Getter private static RealMinecraft instance;
    public static final String PLUGIN_VERSION = "1.0-RELEASE";

    private String[] enableMsg = {
            "&7----------------------------------------",
            "",
            "  &6&lReal &b&lMinecraft &9| &7真实世界 >>>",
            "",
            "  &7作者: &cshaokeyibb 贺兰星辰",
            "  &7版本: &c" + RealMinecraft.PLUGIN_VERSION,
            "",
            "  &7正在启动 &a>>>",
            "",
            "&7----------------------------------------"
    };
    private String[] disableMsg = {
            "&7----------------------------------------",
            "",
            "  &6&lReal &b&lMinecraft &9| &7真实世界 >>>",
            "",
            "  &7作者: &cshaokeyibb 贺兰星辰",
            "  &7版本: &c" + RealMinecraft.PLUGIN_VERSION,
            "",
            "  &7正在关闭 &c>>>",
            "",
            "&7----------------------------------------"
    };
    private String loadEndSuffix = "&7----------------------------------------";

    static Material TruelyBucket = Material.BUCKET;
    static Material LAVA = Material.LAVA;
    static Material WATER = Material.WATER;
    static Material AIR = Material.AIR;
    static ItemStack ChangedBucket = new ItemStack(Material.IRON_INGOT);
    static ItemStack TreesGet = new ItemStack(Material.IRON_HELMET);
    static ItemStack TreesGet2 = new ItemStack(Material.GOLD_HELMET);
    static Material TREELEAVES = Material.LEAVES;
    static Material TREELEAVES2 = Material.LEAVES_2;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        init();

        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getLogger().info("  &a&l> &7注册 &c事件监听器 &7成功.");

        if(UnsafeSand.getInstance().isEnabled()) {
            new CheckPlayerFeetUnder().runTaskTimerAsynchronously(this, 60 * 20, UnsafeSand.getInstance().getCheckPeriod());
            Bukkit.getLogger().info("  &a&l> &7启动 &c[隐藏功能] &7相关检测任务.");
        }

        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', loadEndSuffix));
    }

    private void init() {
        for(String msg : enableMsg) {
            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', msg));
        }

        MetricsLite metrics = new MetricsLite(this);
        Bukkit.getLogger().info("  &a&l> &7启动 &cbStats &7数据统计服务成功.");

        LocaleManager.getInstance().loadLanguage();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(String msg : disableMsg) {
            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}
