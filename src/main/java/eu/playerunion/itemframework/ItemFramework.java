package eu.playerunion.itemframework;

import javax.annotation.Nonnull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.playerunion.itemframework.customitem.CustomItemManager;

public class ItemFramework {
	
	private @Nonnull Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().enableComplexMapKeySerialization().create();
	
	private @Nonnull CustomItemManager customItemManager = new CustomItemManager();
	
	private static @Nonnull ItemFramework instance;
	
	public ItemFramework() {
		instance = this;
	}
	
	/**
	 * ItemFramework instance lekérése.
	 * 
	 * @return ItemFramework instance
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public static ItemFramework getInstance() {
		return instance;
	}
	
	/**
	 * Gson instance lekérése.
	 * 
	 * @return gson.
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nonnull Gson getGson() {
		return this.gson;
	}
	
	/**
	 * CustomItemManager lekérése.
	 * 
	 * @return customItemManager.
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nonnull CustomItemManager getCustomItemManager() {
		return this.customItemManager;
	}

}
