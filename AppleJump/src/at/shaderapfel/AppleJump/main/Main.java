package at.shaderapfel.AppleJump.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import at.shaderapfel.AppleJump.Utils.GameManager;
import at.shaderapfel.AppleJump.commands.CMD_AppleJump;
import at.shaderapfel.AppleJump.listener.JoinQuit;
import at.shaderapfel.AppleJump.listener.Move;

public class Main extends JavaPlugin {

	private static Main instance;
	public static String prefix = "§8[§6AppleJump§8] §7";
	private static boolean inGame = false;
	public static boolean inJump = false;

	public void onEnable() {

		instance = this;
		Bukkit.getPluginManager().registerEvents(new JoinQuit(), this);
		Bukkit.getPluginManager().registerEvents(new Move(), this);

		Bukkit.getPluginCommand("AppleJump").setExecutor(new CMD_AppleJump());
	}

	public void onDisable() {

		GameManager.resetCheckpoints();
	}

	public static Main getInstance() {
		return instance;
	}

	public static boolean isInGame() {
		return inGame;
	}

	public static void setInGame(boolean inGame) {
		Main.inGame = inGame;
	}
}