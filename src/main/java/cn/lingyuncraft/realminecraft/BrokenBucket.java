package cn.lingyuncraft.realminecraft;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

import java.util.Random;


/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public class BrokenBucket implements Listener {

    @EventHandler
    public void onUseBucket(PlayerBucketFillEvent e) {

        Random random = new Random();
        double number = random.nextDouble();
        double max = 1.0;
        double min = 0.8;
        boolean isBreak;

        isBreak = number <= max && number >= min;
        if (e.getBucket() == Main.TruelyBucket && e.getBlockClicked().getType() == Main.LAVA) {
            if (isBreak) {
                World playerWorld = e.getPlayer().getWorld();
                int playerX = e.getPlayer().getLocation().getBlockX();
                int playerY = e.getPlayer().getLocation().getBlockY();
                int playerZ = e.getPlayer().getLocation().getBlockZ();
                Block changedBlock = playerWorld.getBlockAt(playerX, playerY,playerZ);
                if (e.getPlayer().getLocation().getBlock().getType() == Main.AIR) {
                    e.setItemStack(Main.ChangedBucket);
                    e.getPlayer().sendMessage("§a你的桶看起来不太牢固......");
                    changedBlock.setType(Main.LAVA);
                }
            }
        }
        if (e.getBucket() == Main.TruelyBucket && e.getBlockClicked().getType() == Main.WATER) {
            if (isBreak) {
                World playerWorld = e.getPlayer().getWorld();
                int playerX = e.getPlayer().getLocation().getBlockX();
                int playerY = e.getPlayer().getLocation().getBlockY();
                int playerZ = e.getPlayer().getLocation().getBlockZ();
                Block changedBlock = playerWorld.getBlockAt(playerX, playerY, playerZ);
                if (e.getPlayer().getLocation().getBlock().getType() == Main.AIR) {
                    e.setItemStack(Main.ChangedBucket);
                    e.getPlayer().sendMessage("§a你的桶看起来不太牢固......");
                    changedBlock.setType(Main.WATER);
                }
            }
        }
    }
}
