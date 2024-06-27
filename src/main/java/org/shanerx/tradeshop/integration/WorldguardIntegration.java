package org.shanerx.tradeshop.integration;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.shanerx.tradeshop.TradeShop;

public class WorldguardIntegration {

    private static BooleanFlag FLAG;
    private RegionQuery regionQuery;

    public WorldguardIntegration() {
        //load stage
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        String flagName = "tradeshop";
        try {
            FLAG = new BooleanFlag(flagName);
            registry.register(FLAG);
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get(flagName);
            if (existing instanceof BooleanFlag) {
                FLAG = (BooleanFlag) existing;
            }
            TradeShop.getPlugin().getLogger().warning("Flag " + flagName + " already exists!");
        }
    }

    public void init(){
        //enable stage
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        regionQuery = container.createQuery();
    }

    public boolean evaluate(Player player, Location location) {
        Boolean value = regionQuery.queryValue(
                BukkitAdapter.adapt(location),
                WorldGuardPlugin.inst().wrapPlayer(player),
                FLAG
        );
        if( value == null)
            return true;
        return value;
    }

}
