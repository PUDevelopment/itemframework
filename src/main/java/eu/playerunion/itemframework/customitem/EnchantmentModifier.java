package eu.playerunion.itemframework.customitem;

import java.util.Objects;

import javax.annotation.Nonnull;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentModifier {
	
	private @Nonnull Enchantment enchantment;
	private @Nonnull int level;
	private @Nonnull boolean ignoreRestriction;
	
	/**
	 * Objektum enchantmentek tárolására.
	 * 
	 * @param enchantment az enchantment
	 * @param level az enchantment szintje
	 * @param ignoreRestriction false: nem veszi figyelembe az enchanment maximális szintjét.
	 * 
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public EnchantmentModifier(@Nonnull Enchantment enchantment, @Nonnull int level, @Nonnull boolean ignoreRestriction) {
		Objects.requireNonNull(enchantment, "Az enchantment nem lehet null!");
		Objects.requireNonNull(level, "Az enchantment szintje nem lehet null!");
		Objects.requireNonNull(ignoreRestriction, "A védelmi szabályzó nem lehet null!");
		
		this.enchantment = enchantment;
		this.level = level;
		this.ignoreRestriction = ignoreRestriction;
	}
	
	/**
	 * Enchantment lekérdezése.
	 * 
	 * @return enchantment
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nonnull Enchantment getEnchantment() {
		return this.enchantment;
	}
	
	/**
	 * Enchantment szintjének lekérdezése.
	 * 
	 * @return szint
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nonnull int getLevel() {
		return this.level;
	}
	
	/**
	 * Enchantmentre vonatkozó védelmi szabály lekérdezése.
	 * 
	 * @return false: nem veszi figyelembe az enchanment maximális szintjét.
	 * @since 1.0.0-SNAPSHOT
	 */
	
	public @Nonnull boolean ignoreRestriction() {
		return this.ignoreRestriction;
	}

}
