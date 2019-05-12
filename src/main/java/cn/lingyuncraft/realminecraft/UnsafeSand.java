package cn.lingyuncraft.realminecraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public class UnsafeSand implements Listener {
    private Plugin mainPlugin = getPlugin(Main.class);
    private boolean isRun=false;
    private boolean isRunDone=false;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        boolean isSand = e.getPlayer().getLocation().clone().add(0, -1, 0).getBlock().getType()==Material.SAND;
        if(isSand){
            Random random = new Random();
            double number = random.nextDouble();
            double magicNumber = 0.7;
            if(number>= magicNumber){
                isRun = true;
            }
        }
        if(isRun){
            isRun=false;
            e.getPlayer().sendMessage("§c你感到地面似乎有些不对劲");
            while(!isRunDone){
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getPlayer().getLocation().add(0, -0.1, 0);
                    }
                }.runTaskLaterAsynchronously(mainPlugin,20L);
                if(!isSand){
                    e.getPlayer().sendMessage("§c你逃出了沙丘的监禁");
                    isRunDone = true;
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        if(e.getEntity().getLocation().clone().add(0,-1,0).getBlock().getType()==Material.SAND){
            e.setDeathMessage(e.getEntity().getDisplayName()+"终究未能从沙丘的魅力中挣脱......");
            isRunDone = false;
        }
    }
}
