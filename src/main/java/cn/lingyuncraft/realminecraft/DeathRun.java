package cn.lingyuncraft.realminecraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.entity.EntityType.*;
import static org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.PLUGIN;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;


/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public class DeathRun implements Listener {

    private int spawnTime = 5;
    private Plugin mainPlugin = getPlugin(Main.class);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        Random random = new Random();
        double number = random.nextDouble();
        double max = 1.0;
        double min = 0.9;
        boolean isDeathrun;

        if (!e.getEntity().getWorld().hasStorm()) {
            isDeathrun = number <= max && number >= min;
            if (isDeathrun) {
                String playerName = e.getEntity().getDisplayName();
                Location playerLocation = e.getEntity().getLocation();
                e.getEntity().getWorld().setStorm(true);
                e.getEntity().sendTitle("§c你无限制的重生惹恼了神！", "§c接受上天带来的惩罚吧！", 10, 70, 20);
                Bukkit.broadcastMessage(playerName + "§c由于无限制的重生惹恼了神，他将接受来自上天对他的考验！");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getEntity().teleport(playerLocation, PLUGIN);
                        e.getEntity().getWorld().strikeLightning(playerLocation);
                        for (int i = 0; i <= spawnTime; i++) {
                            e.getEntity().getWorld().spawnEntity(playerLocation, ZOMBIE);
                            e.getEntity().getWorld().spawnEntity(playerLocation, SKELETON);
                            e.getEntity().getWorld().spawnEntity(playerLocation, WITCH);
                        }
                    }
                }.runTaskLater(mainPlugin, 20L);
            }
        }
    }
}