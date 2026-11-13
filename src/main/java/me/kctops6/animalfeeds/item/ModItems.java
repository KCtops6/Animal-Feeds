package me.kctops6.animalfeeds.item;

import me.kctops6.animalfeeds.AnimalFeeds;
import me.kctops6.animalfeeds.item.custom.AnimalFeedItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AnimalFeeds.MODID);

    public static final RegistryObject<Item> CHICKEN_FEED = ITEMS.register("chicken_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.CHICKEN));

    public static final RegistryObject<Item> COW_FEED = ITEMS.register("cow_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.COW));

    public static final RegistryObject<Item> PIG_FEED = ITEMS.register("pig_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.PIG));

    public static final RegistryObject<Item> SHEEP_FEED = ITEMS.register("sheep_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.SHEEP));

    public static final RegistryObject<Item> RABBIT_FEED = ITEMS.register("rabbit_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.RABBIT));

    public static final RegistryObject<Item> HORSE_FEED = ITEMS.register("horse_feed",
            () -> new AnimalFeedItem(new Item.Properties().rarity(Rarity.UNCOMMON),
                    () -> EntityType.HORSE));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}