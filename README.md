# itemframework
A PlayerUnion Szerverhálózat egyedi item kezelő API-ja.
Ez az API teszi majd lehetővé azt számunkra, hogy egyedi itemeket írhassunk és kezelhessünk.
Az itemeket maga az API, a szerver gyökérkönyvtárában fogja tárolni.

# Használat
A tervek szerint a használata a következő módon fog kinézni:

```java
public class ExampleHandler implements CustomItemHandler {
	
	@HandleItem(key = "peldaItem")
	public void handle(CustomItem item) {
		item.handleEvent(event -> {
			if(!(event instanceof PlayerInteractEvent))
				return;
			
			PlayerInteractEvent e = (PlayerInteractEvent) event;
			Player player = e.getPlayer();
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
				player.sendMessage("Az API működik! Yey!");
		});
	}
	
}
```
Handler beregisztrálása: (tervezet)
```java
ItemFramework itemFramework = new ItemFramework();
			
itemFramework.getCustomItemManager().registerCustomItemHandler(new ExampleHandler());
```

Példa item beregisztrálása: (tervezet)

```json
{
    "key": "peldaItem",
    "material": "STONE_PICKAXE",
    "displayName": "Példa item",
    "lore": [
        "&7Ez itt egy példa item"
    ],
    "enchanted": true,
    "enchantments": [
        {
            "enchantment": "SILK_TOUCH",
            "level": 1,
            "ignoreRestriction": false
        }
    ]
}
```
Mentsd el a fájlt a ``%szervered%/customItems`` mappába ``peldaItem.json`` néven.

# Buildelés
``$ git clone https://github.com/PUDevelopment/itemframework.git``

A projekt fejlesztésénél Mavent használunk!
