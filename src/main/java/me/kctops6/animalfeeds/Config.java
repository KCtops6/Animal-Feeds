package me.kctops6.animalfeeds;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = AnimalFeeds.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue LIMIT_FEED_TRADES = BUILDER
            .comment("If true, Level 1 Farmers will only offer a maximum of ONE type of animal feed.")
            .define("limitLevelOneFeedTrades", true);

    public static final ForgeConfigSpec.IntValue CHICKEN_LEVEL = BUILDER.defineInRange("chicken_feed_level", 1, 1, 5);
    public static final ForgeConfigSpec.IntValue COW_LEVEL = BUILDER.defineInRange("cow_feed_level", 1, 1, 5);
    public static final ForgeConfigSpec.IntValue PIG_LEVEL = BUILDER.defineInRange("pig_feed_level", 1, 1, 5);
    public static final ForgeConfigSpec.IntValue SHEEP_LEVEL = BUILDER.defineInRange("sheep_feed_level", 1, 1, 5);
    public static final ForgeConfigSpec.IntValue RABBIT_LEVEL = BUILDER.defineInRange("rabbit_feed_level", 1, 1, 5);
    public static final ForgeConfigSpec.IntValue HORSE_LEVEL = BUILDER.defineInRange("horse_feed_level", 2, 1, 5);

    public static final ForgeConfigSpec.IntValue CHICKEN_COST = BUILDER.defineInRange("chicken_feed_cost", 2, 1, 64);
    public static final ForgeConfigSpec.IntValue COW_COST = BUILDER.defineInRange("cow_feed_cost", 4, 1, 64);
    public static final ForgeConfigSpec.IntValue PIG_COST = BUILDER.defineInRange("pig_feed_cost", 4, 1, 64);
    public static final ForgeConfigSpec.IntValue SHEEP_COST = BUILDER.defineInRange("sheep_feed_cost", 4, 1, 64);
    public static final ForgeConfigSpec.IntValue RABBIT_COST = BUILDER.defineInRange("rabbit_feed_cost", 5, 1, 64);
    public static final ForgeConfigSpec.IntValue HORSE_COST = BUILDER.defineInRange("horse_feed_cost", 8, 1, 64);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean limitTrades;
    public static int chickenLvl, cowLvl, pigLvl, sheepLvl, rabbitLvl, horseLvl;
    public static int chickenPrice, cowPrice, pigPrice, sheepPrice, rabbitPrice, horsePrice;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        limitTrades = LIMIT_FEED_TRADES.get();

        chickenLvl = CHICKEN_LEVEL.get();
        cowLvl = COW_LEVEL.get();
        pigLvl = PIG_LEVEL.get();
        sheepLvl = SHEEP_LEVEL.get();
        rabbitLvl = RABBIT_LEVEL.get();
        horseLvl = HORSE_LEVEL.get();

        chickenPrice = CHICKEN_COST.get();
        cowPrice = COW_COST.get();
        pigPrice = PIG_COST.get();
        sheepPrice = SHEEP_COST.get();
        rabbitPrice = RABBIT_COST.get();
        horsePrice = HORSE_COST.get();
    }
}