package org.shanerx.tradeshop.integration;

import net.alex9849.arm.adapters.WGRegion;
import net.alex9849.arm.events.RestoreRegionEvent;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.shanerx.tradeshop.TradeShop;
import org.shanerx.tradeshop.data.storage.DataStorage;
import org.shanerx.tradeshop.framework.events.ServerShopDestroyEvent;
import org.shanerx.tradeshop.shop.Shop;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class AdvancedRegionMarketIntegration implements Listener {

    public AdvancedRegionMarketIntegration(Logger logger) {
        this.logger = logger;
        logger.info("AdvancedRegionMarket integration enabled.");
    }

    private final Logger logger;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRegionRestore(RestoreRegionEvent event) {

        if(event.isCancelled())
            return;

        WGRegion region = event.getRegion().getRegion();
        World world = event.getRegion().getRegionworld();
        Set<ChunkSnapshot> chunks = new HashSet<>();

        //get region extremes
        int y = region.getMinPoint().getBlockY();
        int xMin = region.getMinPoint().getBlockX();
        int xMax = region.getMaxPoint().getBlockX();
        int zMin = region.getMinPoint().getBlockZ();
        int zMax = region.getMaxPoint().getBlockZ();

        //note: this logic is very crude but works for sanely dimensioned regions (as ARM regions should be)
        //iterate over the region area (at minpoint_y of region) and find all chunks.
        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                Chunk chunk = world.getChunkAt(new Location(world, x, y, z));
                if(chunks.stream().noneMatch(c->c.getX() == chunk.getX() && c.getZ() == chunk.getZ())) {
                    //snapshot the chunk and add it into the set
                    chunks.add(chunk.getChunkSnapshot());
                }
            }
        }

        DataStorage dataStorage = TradeShop.getPlugin().getDataStorage();

        //for each found chunk
        for(ChunkSnapshot chunk : chunks){
            //iterate over shops in that chunk
            for(Shop shop : dataStorage.getShopsInChunk(chunk)){
                //check that shop is actually in the region
                Location loc = shop.getShopLocation();
                if(region.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())){
                    logger.info("[AdvancedRegionMarket integration] Removing shop at "+loc.getBlockX()+", "+loc.getBlockY()+", "+loc.getBlockZ());
                    //call event
                    Bukkit.getPluginManager().callEvent(new ServerShopDestroyEvent(shop));
                    //then delete it
                    shop.remove();
                }
            }
        }
    }

}
