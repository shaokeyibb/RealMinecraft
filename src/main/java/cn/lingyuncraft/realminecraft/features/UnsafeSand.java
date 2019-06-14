package cn.lingyuncraft.realminecraft.features;

import cn.lingyuncraft.realminecraft.RealMinecraft;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaokeyibb贺兰星辰
 * @date 2019/5/12
 */
public class UnsafeSand{

    private static UnsafeSand instance;

    public static UnsafeSand getInstance() {
        if(instance == null) {
            instance = new UnsafeSand();
        }
        return instance;
    }

    @Getter boolean enabled = RealMinecraft.getInstance().getConfig().getBoolean("Feature.UnsafeSand.Enabled");
    @Getter int possibility = RealMinecraft.getInstance().getConfig().getInt("Feature.UnsafeSand.Possibility");
    @Getter int checkPeriod = RealMinecraft.getInstance().getConfig().getInt("Feature.UnsafeSand.CheckPeriod") * 20;
    @Getter int fallingSpeed = RealMinecraft.getInstance().getConfig().getInt("Feature.UnsafeSand.FallingSpeed") * 20;

    private List<String> unsafeModeState = new ArrayList<>();

    public boolean isSandUnderFeet(Location location) {
        return location.add(0, -1, 0).getBlock().getType() == Material.SAND;
    }

    public void start(String playerName) {
        unsafeModeState.add(playerName);
    }

    public void escapeSuccess(String playerName) {
        unsafeModeState.remove(playerName);
    }

    public boolean isEscaping(String playerName) {
        return unsafeModeState.contains(playerName);
    }
}
