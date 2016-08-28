package at.shaderapfel.AppleJump.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.shaderapfel.AppleJump.main.Main;

public class CMD_AppleJump implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("AppleJump.setup")){
			if (args.length == 1 && args[0].equalsIgnoreCase("setlobby")) {
				Main.getInstance().getConfig().set("location.lobby", ((Player) sender).getLocation());
				Main.getInstance().saveConfig();
				sender.sendMessage(Main.prefix + "§aDu hast die Lobby gesetzt!");
			}else if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
				Main.getInstance().getConfig().set("location.spawn."+args[1], ((Player) sender).getLocation());
				Main.getInstance().saveConfig();
				sender.sendMessage(Main.prefix + "§aDu hast Spawn §6[ID="+args[1]+"] §agesetzt!");
			}else if(args.length == 1 && args[0].equalsIgnoreCase("status")){
				if(Main.inJump){
					sender.sendMessage("JUMPPHASE");
				}else{
					sender.sendMessage("KEINE JUMPPHASE");
				}
			}
		}
		

		return false;
	}

}
