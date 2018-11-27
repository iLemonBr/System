package rush.configuracoes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.manager.ConfigManager;

public class Locations {

	private static Location padrao = new Location(Bukkit.getWorlds().get(0), 1.0, 100.0, 1.0, 1.0F, 1.0F);
	public static Location spawn = padrao;
	public static Location spawnVip = padrao;
	public static Location areaVip = padrao;
	public static Location areaNaoVip = padrao;
	
	public static void loadLocations() {
		new BukkitRunnable() {
			@Override
			public void run() {
				setAreaVip();
				setAreaNaoVip();
				setSpawn();
				setSpawnVip();
				validarLocations();
				setDefaultServerSpawn();
			}
		}.runTaskLaterAsynchronously(Main.get(), 30 * 20);
	}
	
	private static void validarLocations() {
		List<World> worlds = Bukkit.getWorlds();
		World worldSpawn = spawn.getWorld();
		World worldSpawnVip = spawnVip.getWorld();
		World worldVip = areaVip.getWorld();
		World worldNaoVip = areaNaoVip.getWorld();
		if (!worlds.contains(worldSpawn)) spawn = padrao;
		if (!worlds.contains(worldSpawnVip)) spawnVip = padrao;
		if (!worlds.contains(worldVip)) areaVip = padrao;
		if (!worlds.contains(worldNaoVip)) areaNaoVip = padrao;
	}

	private static void setAreaVip() {
		try {
         areaVip = new Location(Main.get().getServer().getWorld(
           ConfigManager.getConfig("locations").getString("AreaVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.pitch")));
		} catch (Exception | Error e) {
			Bukkit.getServer().getConsoleSender().sendMessage("�c[System] Nao foi possivel carregar a localizacao da AreaVip!");
		}
	}
	
	private static void setAreaNaoVip() {
		try {
        areaNaoVip = new Location(Main.get().getServer().getWorld(
           ConfigManager.getConfig("locations").getString("AreaNaoVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.pitch")));
		} catch (Exception | Error e) {
			Bukkit.getServer().getConsoleSender().sendMessage("�c[System] Nao foi possivel carregar a localizacao da AreaNaoVip!");
		}
	}
	
	private static void setSpawn() {
		try {
	    spawn = new Location(Main.get().getServer().getWorld(
	       ConfigManager.getConfig("locations").getString("Spawn.world")), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.x"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.y"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.z"), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.yaw")), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.pitch")));
		} catch (Exception | Error e) {
			Bukkit.getServer().getConsoleSender().sendMessage("�c[System] Nao foi possivel carregar a localizacao da Spawn!");
		}
	}
	
	private static void setSpawnVip() {
		try {
	    spawnVip = new Location(Main.get().getServer().getWorld(
	       ConfigManager.getConfig("locations").getString("SpawnVip.world")), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.x"), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.y"), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.z"), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("SpawnVip.yaw")), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("SpawnVip.pitch")));
		} catch (Exception | Error e) {
			Bukkit.getServer().getConsoleSender().sendMessage("�c[System] Nao foi possivel carregar a localizacao da SpawnVip!");
		}
	}
	
	private static void setDefaultServerSpawn() {
		List<World> worlds = Bukkit.getWorlds();
		World worldSpawn = spawn.getWorld();
		if (!worlds.contains(worldSpawn)) {
			worlds.get(0).setSpawnLocation(padrao.getBlockX(), padrao.getBlockY(), padrao.getBlockZ());
		} else {
			worldSpawn.setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
		}
	}
}