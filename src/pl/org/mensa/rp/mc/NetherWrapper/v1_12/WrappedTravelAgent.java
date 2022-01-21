package pl.org.mensa.rp.mc.NetherWrapper.v1_12;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftTravelAgent;

import net.minecraft.server.v1_12_R1.WorldServer;

public class WrappedTravelAgent extends CraftTravelAgent {
	
	private static final int CHANGE_TYPE = 1;
	public static final int MAP_MAX_X, MAP_MIN_X, MAP_MAX_Z, MAP_MIN_Z, WRAP_SIZE_X, WRAP_SIZE_Z;
	static {
		MAP_MAX_X = 21503;
		MAP_MIN_X = -21503;
		MAP_MAX_Z = 10751;
		MAP_MIN_Z = -10751;
		
		WRAP_SIZE_X = MAP_MAX_X - MAP_MIN_X;
		WRAP_SIZE_Z = MAP_MAX_Z - MAP_MIN_Z;
	}
	
	Location playerpos;
	
	public WrappedTravelAgent(WorldServer worldserver, Location playerpos) {
		super(worldserver);
		this.playerpos = playerpos;
	}
	
	private Location torusWrap(Location l) {
		int x = l.getBlockX();
		int z = l.getBlockZ();
		
		while (x > MAP_MAX_X) x -= WRAP_SIZE_X;
		while (x < MAP_MIN_X) x += WRAP_SIZE_X;
		while (z > MAP_MAX_Z) z -= WRAP_SIZE_Z;
		while (z < MAP_MIN_Z) z += WRAP_SIZE_Z;
		
		l.set(x, l.getY(), z);
		
		return l;
	}
	private Location linearScaling(Location l, double scale) {
		int x = playerpos.getBlockX();
		int y = playerpos.getBlockY();
		int z = playerpos.getBlockZ();
		
		l.set(x, y, z);
		
		return l;
	}
	
	private Location convertCoordinates(Location l) {
		switch (CHANGE_TYPE) {
			case 0: {
				return torusWrap(l);
			}
			case 1: {
				return linearScaling(l, 1.0D);
			}
			case 2: {
				return linearScaling(l, 2.0D);
			}
			default: {
				return l;
			}
		}
	}
	
	@Override
	public Location findPortal(Location l) {
		return super.findPortal(convertCoordinates(l));
	}
	
	@Override
	public boolean createPortal(Location l) {
		return super.createPortal(convertCoordinates(l));
	}
}
