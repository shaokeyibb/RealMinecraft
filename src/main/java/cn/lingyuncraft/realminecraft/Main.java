package cn.lingyuncraft.realminecraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.MetricsLite;
/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public final class Main extends JavaPlugin {

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
        MetricsLite metrics = new MetricsLite(this);
        getLogger().info("§aRealMinecraft插件正在启动中......");
        getLogger().info("§b正在注册桶事件监听器......");
        Bukkit.getPluginManager().registerEvents(new BrokenBucket(), this);
        getLogger().info("§c注册桶事件监听器完成！");
        getLogger().info("§b正在注册死亡惩罚事件监听器......");
        Bukkit.getPluginManager().registerEvents(new DeathRun(), this);
        getLogger().info("§c注册死亡惩罚事件监听器完成！");
        getLogger().info("§b正在注册下雨天雷击事件监听器......");
        Bukkit.getPluginManager().registerEvents(new DoNOTStayInTrees(), this);
        getLogger().info("§c注册下雨天雷击事件监听器完成！");
        /*
        getLogger().info("§b正在注册流沙事件监听器......");
        Bukkit.getPluginManager().registerEvents(new UnsafeSand(), this);
        getLogger().info("§c注册流沙事件监听器完成!");
        */
        getLogger().info("§aRealMinecraft插件正在启动完成！欢迎使用！作者shaokeyibb贺兰星辰");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("RealMinecraft插件已成功关闭！");
    }
}
