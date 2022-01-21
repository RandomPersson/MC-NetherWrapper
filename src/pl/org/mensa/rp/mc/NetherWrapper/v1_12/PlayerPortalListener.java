package pl.org.mensa.rp.mc.NetherWrapper.v1_12;

import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortalListener implements Listener {
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerPortal(PlayerPortalEvent e) {
		if (e.getTo().getWorld().getName().equalsIgnoreCase("w_earth") || e.getFrom().getWorld().getName().equalsIgnoreCase("w_earth")) {
			e.setPortalTravelAgent(new WrappedTravelAgent(((CraftWorld) e.getTo().getWorld()).getHandle(), e.getPlayer().getLocation()));
		}
	}
}
