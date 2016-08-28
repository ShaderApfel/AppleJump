package at.shaderapfel.AppleJump.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import at.shaderapfel.AppleJump.Utils.GameManager;
import at.shaderapfel.AppleJump.main.Main;

public class Move implements Listener {

	@EventHandler
	public void on(PlayerMoveEvent event) {
		if (event.getPlayer().getLocation().getY() <= 10) {
			event.getPlayer().teleport(GameManager.getCheckPoint(event.getPlayer()));
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.PIG_DEATH, 1, 1);
		}
		Location loc = GameManager.getCheckPoint(event.getPlayer());
		if (event.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE) {
			GameManager.setCheckpoint(event.getPlayer());
			event.getPlayer().sendMessage(Main.prefix + "§aDu hast einen Checkpoint erreicht!");
			event.getPlayer().getLocation().getBlock().setType(Material.STONE_PLATE);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.LEVEL_UP, 1, 1);
		}
	}
}
