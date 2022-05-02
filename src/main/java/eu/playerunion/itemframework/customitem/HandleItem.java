package eu.playerunion.itemframework.customitem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleItem {

	/**
	 * Egyedi item azonosítójának kezelése.
	 * 
	 * @return azonosító.
	 * @since 1.0.0-SNAPSHOT
	 */
	
	@Nonnull String key();
	
}
