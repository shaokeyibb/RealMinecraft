package cn.lingyuncraft.realminecraft.listener;

import cn.lingyuncraft.realminecraft.features.UnsafeSand;
import cn.lingyuncraft.realminecraft.manager.LocaleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private Player player;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        player = event.getEntity();

        if(UnsafeSand.getInstance().isEnabled()) {
            if(UnsafeSand.getInstance().isSandUnderFeet(player.getLocation().clone())){
                event.setDeathMessage(event.getEntity().getDisplayName() + " " + LocaleManager.getInstance().getString("Feature", "UnsafeSand.EscapeFailure"));
                UnsafeSand.getInstance().escapeSuccess(player.getName());
            }
        }
    }
}
