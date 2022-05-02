package eu.playerunion.itemframework.customitem;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

public class CustomItemManager {

	private @Nonnull HashSet<CustomItemHandler> customItemHandlers = new HashSet<CustomItemHandler>();
	
	private @Nonnull HashSet<CustomItem> customItems = new HashSet<CustomItem>();

	/**
	 * Egyedi itemeket kezelő class beregisztrálása.
	 * 
	 * @param handler az egyedi itemeket kezelő class
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public void registerCustomItemHandler(@Nonnull CustomItemHandler handler) {
		Objects.requireNonNull(handler, "A megadott CustomItemHandler nem lehet null!");
		
		this.customItemHandlers.add(handler);
	}
	
	/**
	 * Egyedi item kezelése.
	 * 
	 * @param item az egyedi item.
	 * @since 1.0.0-SNAPSHOT
	 */

	public void handleItem(@Nonnull CustomItem item) {
		Objects.requireNonNull(item, "A megadott custom item nem lehet null!");
		
		this.customItemHandlers.stream().forEach(handler -> {
			Arrays.stream(handler.getClass().getDeclaredMethods())
					.filter(method -> method.isAnnotationPresent(HandleItem.class))
					.filter(method -> method.getAnnotation(HandleItem.class).key().equals(item.getKey()))
					.forEach(method -> {
						try {
							method.invoke(handler, item);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					});
		});
	}
	
	/**
	 * Egyedi item keresése név és type alapján.
	 * 
	 * @param item Bukkitos item.
	 * @return null: ha az egyedi itemet nem sikerült megtalálni, vagy a Bukkitos item nem társítható hozzá.
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nullable CustomItem findCustomItem(@Nonnull ItemStack item) {
		Objects.requireNonNull(item, "A megadott item nem lehet null!");
		
		if(!item.hasItemMeta())
			return null;
		
		return this.customItems.stream().filter(
				customItem -> (item.getItemMeta().getDisplayName().equals(customItem.getDisplayName()) && item.getType() == customItem.getItem().getType())).findFirst().get();
	}

}
