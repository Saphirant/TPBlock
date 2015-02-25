package me.saphirant.teleportBlock;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener{
	
	double ht = 1.5;

	@Override
	public void onDisable() {
		
		
	}
	
	@Override
	public void onEnable() {
	
	PluginManager pm = getServer().getPluginManager();
	pm.registerEvents(this, this);
		
	}
	
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent e){
		Player p =e.getPlayer();
		String message = e.getMessage();
		String[] params = message.split(" ");
		if(params[0].equalsIgnoreCase("/ht")){
			if (params.length >= 2) {
				if(params[1] != null){
					e.setCancelled(true);
					ht = Integer.valueOf(params[1]);
					p.sendMessage(ChatColor.GOLD + "Vous avez mis la hauteur de saut des block a " + ht);
				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	@EventHandler
	public void Click(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		Action a = e.getAction();
		Location bloc = b.getLocation();
		if(a == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInHand().getType() == Material.STICK){
				p.playEffect(bloc, Effect.MOBSPAWNER_FLAMES, 30);
				p.playSound(bloc, Sound.DIG_SAND, 2, 5);
				FallingBlock fb = Bukkit.getWorld(p.getWorld().getName()).spawnFallingBlock(bloc, b.getType(), b.getData());
				b.setType(Material.AIR);
				float x = 0;
		        float y = (float) ht;
		        float z = 0;
				fb.setVelocity(new Vector(x,y,z));
				
				
			}
		
		
		}
	}

}