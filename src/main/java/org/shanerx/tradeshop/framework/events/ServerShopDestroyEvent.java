/*
 *
 *                         Copyright (c) 2016-2023
 *                SparklingComet @ http://shanerx.org
 *               KillerOfPie @ http://killerofpie.github.io
 *                   Tig3r98 @ https://www.github.com/Tig3r98
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *                http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  NOTICE: All modifications made by others to the source code belong
 *  to the respective contributor. No contributor should be held liable for
 *  any damages of any kind, whether be material or moral, which were
 *  caused by their contribution(s) to the project. See the full License for more information.
 *
 */

package org.shanerx.tradeshop.framework.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.shanerx.tradeshop.TradeShop;
import org.shanerx.tradeshop.shop.Shop;

/**
 * This class represents the event being fired upon shop destruction by the server.
 * It is not cancellable, but it gets called before the shop gets actually deleted.
 */
public class ServerShopDestroyEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Shop shop;

    /**
     * Constructor for the object.
     * @param shop The {@link Shop} object which represents the shop the server is destroying.
     */
    public ServerShopDestroyEvent(Shop shop) {
        this.shop = shop;
        TradeShop.getPlugin().getVarManager().adjustShops(-1);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns the {@link Shop} object representing the player shop this event is about.
     * @return the shop.
     */
    public Shop getShop() {
        return shop;
    }
}
