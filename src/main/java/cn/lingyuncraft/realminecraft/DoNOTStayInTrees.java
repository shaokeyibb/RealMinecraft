package cn.lingyuncraft.realminecraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public class DoNOTStayInTrees implements Listener {

    private int originalTime = 0;
    private int originalTime2 = 0;

    private static boolean isSimilar(ItemStack stack, ItemStack itemStack2) {
        if (stack == null || itemStack2 == null) {
            return false;
        }
        if (stack == itemStack2) {
            return true;
        } else {
            return stack.getTypeId() == itemStack2.getTypeId();
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        boolean underTree = false;
        Location playerLocation = e.getTo();
        Location original = playerLocation.clone();
        boolean hasStorm = e.getPlayer().getWorld().hasStorm();
        int treeHeight = 8;
        for (int i = 0; i < treeHeight; i++) {
            if (original.add(0, 1, 0).getBlock().getType().equals(Main.TREELEAVES) || original.add(0, 1, 0).getBlock().getType().equals(Main.TREELEAVES2)) {
                underTree = true;
                break;
            }
        }
        ItemStack playerIS = e.getPlayer().getInventory().getHelmet();
        if (hasStorm && underTree) {
            int timePassthrough = 30;
            if (isSimilar(Main.TreesGet, playerIS)) {
                originalTime++;
                if (originalTime >= timePassthrough) {
                    e.getPlayer().getWorld().strikeLightning(playerLocation);
                    e.getPlayer().sendMessage("§a下雨天戴着金属头盔站在树下小心被雷劈......");
                    Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + "§c由于人品太差被雷劈了！");
                    originalTime = 0;
                }
            } else if (isSimilar(Main.TreesGet2, playerIS)) {
                originalTime2++;
                if (originalTime2 >= timePassthrough) {
                    e.getPlayer().getWorld().strikeLightning(playerLocation);
                    e.getPlayer().getWorld().strikeLightning(playerLocation);
                    e.getPlayer().sendMessage("§a下雨天戴着金属头盔站在树下小心被雷劈......");
                    Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + "§c由于人品太差被雷劈了！");
                    originalTime2 = 0;
                }
            }
        }
    }
}