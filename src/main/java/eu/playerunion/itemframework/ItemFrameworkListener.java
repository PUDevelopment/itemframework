package eu.playerunion.itemframework;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.playerunion.itemframework.customitem.CustomItem;
import eu.playerunion.itemframework.customitem.CustomItemManager;

public class ItemFrameworkListener implements Listener {
	
	private ItemFramework itemFramework = ItemFramework.getInstance();
	private CustomItemManager customItemManager = this.itemFramework.getCustomItemManager();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(this.customItemManager.findCustomItem(event.getItem()) != null) {
			CustomItem item = this.customItemManager.findCustomItem(event.getItem());
			
			item.callEvent(event);
			
			this.customItemManager.handleItem(item);
		}
	}

}
