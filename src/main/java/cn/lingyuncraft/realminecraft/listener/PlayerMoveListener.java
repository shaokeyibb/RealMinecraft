package cn.lingyuncraft.realminecraft.listener;

import cn.lingyuncraft.realminecraft.RealMinecraft;
import cn.lingyuncraft.realminecraft.enums.MessageType;
import cn.lingyuncraft.realminecraft.features.UnsafeSand;
import cn.lingyuncraft.realminecraft.manager.LocaleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerMoveListener implements Listener {

    private Player player;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        player = event.getPlayer();

        if(UnsafeSand.getInstance().isEnabled()) {
            if(UnsafeSand.getInstance().isEscaping(player.getName())){
                if(UnsafeSand.getInstance().isSandUnderFeet(player.getLocation().clone())){
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.getLocation().add(0, -0.1, 0);
                        }
                    }.runTaskLaterAsynchronously(RealMinecraft.getInstance(),UnsafeSand.getInstance().getFallingSpeed());
                } else {
                    player.sendMessage(LocaleManager.getInstance().getMessage(MessageType.INFO, "Feature", "UnsafeSand.EscapeSuccess"));
                    UnsafeSand.getInstance().escapeSuccess(player.getName());
                }
            }
        }
    }
}
