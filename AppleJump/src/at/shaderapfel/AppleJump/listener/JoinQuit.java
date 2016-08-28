package at.shaderapfel.AppleJump.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import at.shaderapfel.AppleJump.Utils.GameManager;
import at.shaderapfel.AppleJump.Utils.LobbyCountdown;
import at.shaderapfel.AppleJump.main.Main;

public class JoinQuit implements Listener {

	@EventHandler
	public void on(PlayerJoinEvent event) {
		if (!Main.isInGame()) {
			event.setJoinMessage(Main.prefix + event.getPlayer().getDisplayName() + " §7ist dem Spiel beigetreten!");
			LobbyCountdown.start();
			event.getPlayer().getInventory().clear();
			GameManager.setCheckpoint(event.getPlayer());
			event.getPlayer().teleport((Location) Main.getInstance().getConfig().get("location.lobby"));
			GameManager.addInGame(event.getPlayer());
		} else {
			event.setJoinMessage(null);
			event.getPlayer().setGameMode(GameMode.SPECTATOR);
		}
	}

	@EventHandler
	public void on(PlayerQuitEvent event) {
		GameManager.removeInGame(event.getPlayer());
		if (!Main.isInGame()) {
			event.setQuitMessage(Main.prefix + event.getPlayer().getDisplayName() + " §7hat das Spiel verlassen!");
			LobbyCountdown.stop();
		} else {
			if (GameManager.isIngame(event.getPlayer())) {
				event.setQuitMessage(Main.prefix + event.getPlayer().getDisplayName() + " §7hat das Spiel verlassen!");
			} else {
				event.setQuitMessage(null);
			}
		}
		
		
	}
}
