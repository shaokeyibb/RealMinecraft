package cn.lingyuncraft.realminecraft.tasks;

import cn.lingyuncraft.realminecraft.enums.MessageType;
import cn.lingyuncraft.realminecraft.features.UnsafeSand;
import cn.lingyuncraft.realminecraft.manager.LocaleManager;
import cn.lingyuncraft.realminecraft.utils.CommonUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CheckPlayerFeetUnder extends BukkitRunnable {

    private Random random;
    private int possibility;

    @Override
    public void run() {
        for(Player onlinePlayer : CommonUtil.getOnlinePlayers()) {
            if(UnsafeSand.getInstance().isSandUnderFeet(onlinePlayer.getLocation().clone())){
                random = new Random();
                possibility = UnsafeSand.getInstance().getPossibility();

                if(random.nextInt(100) <= possibility){
                    UnsafeSand.getInstance().start(onlinePlayer.getName());
                }

                onlinePlayer.sendMessage(LocaleManager.getInstance().getMessage(MessageType.WARN, "Feature", "UnsafeSand.Starting"));
            }
        }
    }
}
