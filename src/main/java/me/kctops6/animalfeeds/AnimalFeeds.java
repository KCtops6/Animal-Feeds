package me.kctops6.animalfeeds;

import me.kctops6.animalfeeds.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(AnimalFeeds.MODID)
public class AnimalFeeds {
    public static final String MODID = "animalfeeds";

    public AnimalFeeds() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.CHICKEN_FEED);
            event.accept(ModItems.COW_FEED);
            event.accept(ModItems.PIG_FEED);
            event.accept(ModItems.SHEEP_FEED);
            event.accept(ModItems.RABBIT_FEED);
            event.accept(ModItems.HORSE_FEED);
        }
    }

    @SubscribeEvent
    public void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            var tradesMap = event.getTrades();

            record FeedTrade(net.minecraft.world.item.Item item, int level, int cost) {}
            List<FeedTrade> feedTrades = List.of(
                    new FeedTrade(ModItems.CHICKEN_FEED.get(), Config.chickenLvl, Config.chickenPrice),
                    new FeedTrade(ModItems.COW_FEED.get(), Config.cowLvl, Config.cowPrice),
                    new FeedTrade(ModItems.PIG_FEED.get(), Config.pigLvl, Config.pigPrice),
                    new FeedTrade(ModItems.SHEEP_FEED.get(), Config.sheepLvl, Config.sheepPrice),
                    new FeedTrade(ModItems.RABBIT_FEED.get(), Config.rabbitLvl, Config.rabbitPrice),
                    new FeedTrade(ModItems.HORSE_FEED.get(), Config.horseLvl, Config.horsePrice)
            );

            List<BasicItemListing> level1Pool = new ArrayList<>();

            for (FeedTrade trade : feedTrades) {
                BasicItemListing listing = createListing(trade.item(), trade.cost());

                if (trade.level() == 1 && Config.limitTrades) {
                    level1Pool.add(listing);
                } else {
                    tradesMap.computeIfAbsent(trade.level(), lvl -> new ArrayList<>()).add(listing);
                }
            }

            if (!level1Pool.isEmpty() && Config.limitTrades) {
                tradesMap.computeIfAbsent(1, lvl -> new ArrayList<>()).add((entity, random) -> {
                    BasicItemListing selectedListing = level1Pool.get(random.nextInt(level1Pool.size()));
                    return selectedListing.getOffer(entity, random);
                });
            }
        }
    }

    private BasicItemListing createListing(net.minecraft.world.item.Item item, int cost) {
        return new BasicItemListing(new ItemStack(Items.EMERALD, cost), new ItemStack(item), 12, 2, 0.05f);
    }
}