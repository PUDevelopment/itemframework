package eu.playerunion.itemframework.customitem;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.annotations.Expose;

import net.md_5.bungee.api.ChatColor;

public class CustomItem {

	@Expose
	private @Nonnull String key;

	@Expose
	private @Nonnull Material material;

	@Expose
	private @Nonnull String displayName;

	@Expose
	private @Nonnull List<String> lore;

	@Expose
	private @Nonnull boolean isEnchanted;

	@Expose
	private @Nullable List<EnchantmentModifier> enchantments;

	private @Nonnull Consumer<PlayerEvent> playerEvent;

	/**
	 * Objektum az egyedi itemeket tárolására. Az objektum magában tartalmazza azon
	 * event consumerjét, melyben őt meghívták.
	 * 
	 * @param key          az egyedi item egyedi azonosítója (példa: "customSword")
	 * @param material     az egyedi item Materialja
	 * @param displayName  az egyedi item neve színkódokkal (példa: "&cCustom
	 *                     Sword")
	 * @param lore         az egyedi item leírása
	 * @param isEnchanted  az item rendelkezik-e enchantokkal, vagy sem
	 * @param enchantments egy opcionális collection az esetleges enchantokról
	 * 
	 * @since 1.0.0-SNAPSHOT
	 */

	public CustomItem(@Nonnull String key, @Nonnull Material material, @Nonnull String displayName,
			@Nonnull List<String> lore, @Nonnull boolean isEnchanted,
			@Nullable List<EnchantmentModifier> enchantments) {

		Objects.requireNonNull(key, "Az egyedi azonosító nem lehet null!");
		Objects.requireNonNull(material, "A Material nem lehet null!");
		Objects.requireNonNull(displayName, "Az item neve nem lehet null!");
		Objects.requireNonNull(lore, "Az item leírása nem lehet null!");
		Objects.requireNonNull(isEnchanted, "Az item enchant befolyásolója nem lehet null!");

		this.key = key;
		this.material = material;
		this.displayName = displayName;
		this.lore = lore;
		this.isEnchanted = isEnchanted;
		this.enchantments = enchantments;
	}

	/**
	 * Az egyedi item azonosító lekérdezése.
	 * 
	 * @return egyedi item azonosító.
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nonnull String getKey() {
		return this.key;
	}

	/**
	 * Az egyedi item Materialjának lekérdezése.
	 * 
	 * @return az egyedi item Materialja.
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nonnull Material getMaterial() {
		return this.material;
	}

	/**
	 * Az egyedi item nevének lekérdezése. (színkód formázott)
	 * 
	 * @return az egyedi item neve.
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nonnull String getDisplayName() {
		return ChatColor.translateAlternateColorCodes('&', this.displayName);
	}

	/**
	 * Az egyedi item leírásának lekérdézése. (színkód formázott)
	 * 
	 * @return az egyedi item leírása
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nonnull List<String> getLore() {
		for (int i = 0; i < this.lore.size(); i++)
			this.lore.set(i, ChatColor.translateAlternateColorCodes('&', this.lore.get(i)));

		return this.lore;
	}

	/**
	 * Ellenőrzi, hogy az egyedi item rendelkezik-e enchanttal.
	 * 
	 * @return true, ha rendelkezik enchanttal.
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nonnull boolean isEnchanted() {
		return this.isEnchanted;
	}

	/**
	 * Az egyedi itemeket enchantjainak lekérdezése.
	 * 
	 * @return null, ha nincs egyetlen enchant sem rajta.
	 * @since 1.0.0-SNAPSHOT
	 */

	public @Nullable List<EnchantmentModifier> getEnchantments() {
		return this.enchantments;
	}

	/**
	 * Az egyedi itemhez tartozó Bukkitos item.
	 * 
	 * @return item
	 * @since 1.0.0-SNAPSHOT
	 */

	public ItemStack getItem() {
		ItemStack item = new ItemStack(this.getMaterial());
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(this.getDisplayName());
		meta.setLore(this.getLore());

		if (this.getEnchantments() != null)
			this.getEnchantments().forEach(enchantment -> meta.addEnchant(enchantment.getEnchantment(),
					enchantment.getLevel(), enchantment.ignoreRestriction()));

		item.setItemMeta(meta);

		return item;
	}

	/**
	 * Az egyedi itemhez tartozó event kezelése.
	 * 
	 * @param event Az event Consumerje.
	 * @since 1.0.0-SNAPSHOT
	 */

	public void handleEvent(Consumer<PlayerEvent> event) {
		this.playerEvent = event;
	}

	public void callEvent(@Nonnull PlayerEvent event) {
		this.playerEvent.accept(event);
	}

}
