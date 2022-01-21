package pl.org.mensa.rp.mc.NetherWrapper;

import org.bukkit.plugin.java.JavaPlugin;

import pl.org.mensa.rp.mc.NetherWrapper.v1_12.PlayerPortalListener;

public class NetherWrapperPlugin extends JavaPlugin {
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new PlayerPortalListener(), this);
	}
}
