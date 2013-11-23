package com.github.mcqueenorama.proj1;

import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public final class proj1 extends JavaPlugin {
	
	private final ThorListener playerListener = new ThorListener(this);
	private final HashMap<String, Boolean> immortals = new HashMap<String, Boolean>();
 
    @Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
    	getLogger().info("onEnable has been invoked!");
    	
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("onDisable has been invoked!");
    }
    
    public void setImmortals(String player, final boolean value) {
        immortals.put(player, value);
    }
    
    public Boolean getImmortals(String player) {
        return immortals.get(player);
    }

    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("basic")){ // If the player typed /basic then do the following...
    		// doSomething
        	getLogger().info("basic method called");
    		return true;
    	}   else if (cmd.getName().equalsIgnoreCase("pignite")) {
            // Make sure that the player specified exactly one argument (the name of the player to ignite).
            if (args.length != 1) {
                // When onCommand() returns false, the help message associated with that command is displayed.
                return false;
            }
     
            // Get the player who should be set on fire. Remember that indecies start with 0, not 1.
            Player target = Bukkit.getServer().getPlayer(args[0]);
     
            // Make sure the player is online.
            if (target == null) {
                sender.sendMessage(args[0] + " is not currently online.");
                return true;
            }
     
            // Sets the player on fire for 1,000 ticks (there are ~20 ticks in second, so 50 seconds total).
            target.setFireTicks(1000);
            return true;
        }   else if (cmd.getName().equalsIgnoreCase("immt")) {
     
            sender.sendMessage(sender.getName() + " is immortal.");
            getLogger().info(sender.getName() + " is immortal.");
            immortals.put(sender.getName(), true);
            return true;
            
        }   else if (cmd.getName().equalsIgnoreCase("immf")) {
     
            sender.sendMessage(sender.getName() + " is NOT immortal.");
            getLogger().info(sender.getName() + " is NOT immortal.");
            immortals.put(sender.getName(), false);
            return true;
        }
    	return false; 
    }
	
}


