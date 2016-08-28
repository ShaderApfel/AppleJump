package at.shaderapfel.AppleJump.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GameManager {

	private static List<Player> playerInGame = new ArrayList<>();
	public static HashMap<Player, Location> checkpoints = new HashMap<>();
	private static List<Block> resetCheckpoints = new ArrayList<>();

	public static List<Player> getPlayerInGame() {
		return playerInGame;
	}

	public static boolean isIngame(Player player) {
		return playerInGame.contains(player);
	}

	public static void addInGame(Player player) {
		playerInGame.add(player);
	}

	public static void removeInGame(Player player) {
		if (isIngame(player)) {
			playerInGame.remove(player);
		}
	}

	public static Location getCheckPoint(Player player) {

		if (checkpoints.containsKey(player)) {
			return checkpoints.get(player);
		}
		return null;
	}

	public static void setCheckpoint(Player player) {
		if (getCheckPoint(player) != null) {
			checkpoints.remove(player);
		}
		checkpoints.put(player, player.getLocation());
	}

	public static void addCheckpointReset(Block block) {
		resetCheckpoints.add(block);
	}

	public static void resetCheckpoints() {
		for (Block block : resetCheckpoints) {
			block.setType(Material.IRON_PLATE);
		}
	}
}
