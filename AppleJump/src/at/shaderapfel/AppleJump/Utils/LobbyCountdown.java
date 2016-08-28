package at.shaderapfel.AppleJump.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.shaderapfel.AppleJump.main.Main;

public class LobbyCountdown {

	private static int sched;

	public static void start() {
		if (Bukkit.getOnlinePlayers().size() == 2) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

				int count = 30;

				@Override
				public void run() {
					if (count <= 0) {
						int playerno = 1;
						for (Player all : Bukkit.getOnlinePlayers()) {
							all.sendMessage(Main.prefix + "§aDas Spiel startet nun!");
							all.playSound(all.getLocation(), Sound.NOTE_PLING, 3, 1);
							all.teleport((Location)Main.getInstance().getConfig().get("location.spawn."+playerno));
							playerno++;
							Main.inJump = true;
							ActionBar.sendActionBar(all, "§aDas Spiel startet nun!");
							Bukkit.getScheduler().cancelAllTasks();
						}
					} else if (count == 60 || count == 50 || count == 40 || count == 30 || count == 20 || count == 10
							|| count == 5 || count == 4 || count == 3 || count == 2) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							all.sendMessage(Main.prefix + "Das Spiel startet in §6" + count + " §7Sekunden!");
							ActionBar.sendActionBar(all, "§7Das Spiel startet in §6" + count + " §7Sekunden!");
							all.playSound(all.getLocation(), Sound.NOTE_PLING, 3, 1);
						}
					} else if (count == 1) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							all.sendMessage(Main.prefix + "Das Spiel startet in §6" + count + " §7Sekunde!");
							ActionBar.sendActionBar(all, "§7Das Spiel startet in §6" + count + " §7Sekunde!");
							all.playSound(all.getLocation(), Sound.NOTE_PLING, 3, 1);
						}
					}
					count--;
				}
			}, 20, 20);
		}

	}

	public static void stop() {
		if (Bukkit.getOnlinePlayers().size() == 1) {
			Bukkit.broadcastMessage(Main.prefix+"§cDer Countdown wurde abgebrochen!");
			Bukkit.getScheduler().cancelTask(sched);
		}
	}
}
