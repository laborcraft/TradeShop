/*
 *
 *                         Copyright (c) 2016-2023
 *                SparklingComet @ http://shanerx.org
 *               KillerOfPie @ http://killerofpie.github.io
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

package org.shanerx.tradeshop.commands;

import com.google.common.collect.Lists;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.shanerx.tradeshop.player.PermStatus;
import org.shanerx.tradeshop.player.Permissions;

import java.util.List;

/**
 * Enum holding all commands as well as aliases,
 * required permissions, minimum and maximum
 * arguments, and whether the command must be
 * run by a player
 **/

public enum CommandType {

    // Shop other management commands
    OPEN(Lists.newArrayList("apri"), Permissions.NONE, 1, 1, true, "Apri negozio", "/negozi $cmd$"),
    CLOSE(Lists.newArrayList("chiudi"), Permissions.NONE, 1, 1, true, "Chiudi negozio", "/negozi $cmd$"),
    SWITCH(Lists.newArrayList("cambia"), Permissions.EDIT, 1, 1, true, "Cambia tipo di shop", "/negozi $cmd$"),
    EDIT(Lists.newArrayList("modifica", "m"), Permissions.EDIT, 1, 1, true, "Apre la GUI di modifica del negozio", "/negozi $cmd$"),
    CREATE_TRADE(Lists.newArrayList("crea"), Permissions.CREATE, 1, 1, true, "Crea negozio", "/negozi $cmd$"),
    CREATE_BITRADE(Lists.newArrayList("crea-bidirezionale"), Permissions.CREATEBI, 1, 1, true, "Crea negozio bidirezionale", "/negozi $cmd$"),
    CREATE_ITRADE(Lists.newArrayList("crea-server"), Permissions.CREATEI, 1, 1, true, "Create negozio del server", "/negozi $cmd$"),

    // Shop user management commands
    REMOVE_USER(Lists.newArrayList("rimuoviUtente"), Permissions.NONE, 2, 3, true, "Rimuove un utente dal negozio", "/negozi $cmd$ <Name> [all shops]"),
    ADD_MANAGER(Lists.newArrayList("aggiungiGestore"), Permissions.NONE, 2, 3, true, "Aggiunge un gestore ad un negozio", "/negozi $cmd$ <name> [all shops]"),
    ADD_MEMBER(Lists.newArrayList("aggiungiMembro"), Permissions.NONE, 2, 3, true, "Aggiunge un membro ad un negozio", "/negozi $cmd$ <name> [all shops]"),
    SET_MANAGER(Lists.newArrayList("impostaGestore"), Permissions.NONE, 2, 3, true, "Imposta un giocatore come gestore di un negozio", "/negozi $cmd$ <name> [all shops]"),
    SET_MEMBER(Lists.newArrayList("impostaMembro"), Permissions.NONE, 2, 3, true, "Imposta un giocatore come membro di un negozio", "/negozi $cmd$ <name> [all shops]"),

    // Shop item management commands
    //ADD_PRODUCT(Lists.newArrayList("aggiungiProdotto"), Permissions.NONE, 1, 3, true, "Aggiungi un prodotto al negozio", "/negozi $cmd$ [Amount] [Material]"),
    //ADD_COST(Lists.newArrayList("aggiungiPagamento"), Permissions.NONE, 1, 3, true, "Aggiungi un pagamento al negozio", "/negozi $cmd$ [Amount] [Material]"),
    SET_PRODUCT(Lists.newArrayList("impostaProdotto"), Permissions.NONE, 1, 3, true, "Imposta il prodotto del negozio", "/negozi $cmd$ [Amount] [Material]"),
    SET_COST(Lists.newArrayList("impostaPagamento"), Permissions.NONE, 1, 3, true, "Imposta il pagamendo del negozio", "/negozi $cmd$ [Amount] [Material]"),
    REMOVE_PRODUCT(Lists.newArrayList("rimuoviProdotto"), Permissions.NONE, 1, 2, true, "Rimuovi un prodotto dal negozio", "/negozi $cmd$ <List #>"),
    REMOVE_COST(Lists.newArrayList("rimuoviPagamento"), Permissions.NONE, 1, 2, true, "Rimuovi un pagamento dal negozio", "/negozi $cmd$ <List #>"),
    //LIST_PRODUCT(Lists.newArrayList("listProduct"), Permissions.NONE, 1, 1, true, "Lista i prodotti del negozio", "/negozi $cmd$"),
    //LIST_COST(Lists.newArrayList("listCost"), Permissions.NONE, 1, 1, true, "Lisa i pagamenti del negozio", "/negozi $cmd$"),

    // Shop info/Player commands
    WHO(Lists.newArrayList("membri"), Permissions.INFO, 1, 1, true, "Mostra i membri del negozio", "/negozi $cmd$"),
    WHAT(Lists.newArrayList("inventario"), Permissions.INFO, 1, 1, true, "Mostra lo stato dell'inventario del negozio", "/negozi $cmd$"),
    MULTI(Lists.newArrayList("moltiplicatore"), Permissions.NONE, 1, 2, true, "Cambia il moltiplicatore di scambi per questa sessione", "/negozi $cmd$ <Amount>"),
    STATUS(Lists.newArrayList("stato"), Permissions.INFO, 1, 2, true, "Mostra lo stato di tutti i negozi in cui il giocatore è coinvolto", "/negozi $cmd$ [Name]"),
    TOGGLE_STATUS(Lists.newArrayList("cambiastatus"), Permissions.INFO, 1, 1, true, "Cambia il messaggio di login contenente la lista dei negozi in cui il giocatore è coinvolto", "/negozi $cmd$"),
    FIND(Lists.newArrayList("trova"), Permissions.FIND, 2, 50, true, "Trova negozi vicini. <Parametri> può includere \n'prezzo=item1,item2,item3;'\n'prodotto=item1,item2,item3;'\n'distanza=numero'\n    '|' può essere usato al posto delle virgole nella lista se preferisci.\n    Le liste si leggono da destra a sinistra, quindi 'minecraft:dirt|minecraft:stone,minecraft:dirt' sarebbe terra o (pietra e terra). Un negozio con solo pietra non verrebbe trovato così.", "/negozi $cmd$ <Parametri>"),

    // Other commands
    HELP(Lists.newArrayList("aiuto", "?"), Permissions.HELP, 1, 2, false, "Mostra il messaggio di aiuto", "/negozi $cmd$ [command]"),
    SETUP(Lists.newArrayList("tutorial"), Permissions.HELP, 1, 1, false, "Mostra il tutorial", "/negozi $cmd$"),
    //BUGS(Lists.newArrayList("bug"), Permissions.NONE, 1, 1, false, "Riporta i bug al developer", "/negozi $cmd$"),
    //TOGGLE_ADMIN(Lists.newArrayList("admin"), Permissions.ADMIN, 1, 1, true, "Attiva/disattiva la modalità amministratore", "/negozi $cmd$"),
    ADMIN(Lists.newArrayList("admin"), Permissions.ADMIN, 1, 2, true, "Mostra lo stato della modalità amministratore, o la cambia con la variabile opzionale", "/negozi $cmd$ [True/False]"),
    METRICS(Lists.newArrayList("statistiche"), Permissions.ADMIN, 1, 1, false, "Mostra le statistiche.", "/negozi $cmd$"),
    RELOAD(Lists.newArrayList("ricarica"), Permissions.MANAGE_PLUGIN, 1, 1, false, "Ricarica la configurazione", "/negozi $cmd$");


    /**
     * Name of the permission
     **/
    private final String name;

    /**
     * All names that can be used to call the command
     **/
    private final List<String> names;

    /**
     * Minimum and Maximum arguments required for the command
     **/
    private final int minArgs;
    private final int maxArgs;

    /**
     * Permission required for the command
     **/
    private final Permissions perm;

    /**
     * Whether the command requires a player to run
     **/
    private final boolean needsPlayer;

    /**
     * Description for command
     */
    private final String description;

    /**
     * Command usage
     */
    private final String usage;

    CommandType(List<String> names, Permissions perm, int minArgs, int maxArgs, boolean needsPlayer, String description, String usage) {
        this.names = names;
        this.perm = perm;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
        this.needsPlayer = needsPlayer;
        this.description = description;
        this.usage = usage;
        name = name();
    }

    /**
     * Returns Enum Value if present of the string variable
     *
     * @param toCheck String to check if enum exists for
     * @return Commands Enum value of string
     */
    public static CommandType getType(String toCheck) {
        for (CommandType cmd : values()) {
            for (String str : cmd.getNames()) {
                if (str.equalsIgnoreCase(toCheck)) {
                    return cmd;
                }
            }
        }

        return null;
    }

    /**
     * Returns list of all usable names for the command
     *
     * @return String List containing names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * Returns true if the sent string is contained within a command or its aliases
     *
     * @param cmd String to test for partial command match
     *
     * @return true if the sent string is contained within a command or its aliases
     */
    public boolean isPartialName(String cmd) {
        for (String name : getNames()) {
            if (name.toLowerCase().contains(cmd.toLowerCase()))
                return true;
        }

        return false;
    }


    /**
     * Returns list of all usable names for the command
     *
     * @return String List containing names
     */
    public String getFirstName() {
        return names.get(0);
    }

    /**
     * Returns minimum required arguments
     *
     * @return int minimum arguments
     */
    public int getMinArgs() {
        return minArgs;
    }

    /**
     * Returns maximum required arguments
     *
     * @return int maximum arguments
     */
    public int getMaxArgs() {
        return maxArgs;
    }

    /**
     * Returns true if string has equivalent command enum
     *
     * @param toCheck String to check if enum exists for
     * @return true if enum exists
     */
    public boolean isName(String toCheck) {
        for (String test : names) {
            if (test.equalsIgnoreCase(toCheck)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns Permission enum required for command
     *
     * @return Permissions enum required for command
     */
    public Permissions getPerm() {
        return perm;
    }

    /**
     * Returns true if command needs a player to run
     *
     * @return true if command requires player
     */
    public boolean needsPlayer() {
        return needsPlayer;
    }

    /**
     * Returns the description for the command
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns command usage
     *
     * @return usage
     */
    public String getUsage() {
        return usage.replace("$cmd$", getFirstName());
    }

    /**
     * Returns command aliases
     *
     * @return usage
     */
    public String getAliases() {
        int namesSize = getNames().size();
        if (namesSize == 1)
            return "&eNone";

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < namesSize; i++) {
            sb.append("&e");
            sb.append(getNames().get(i));
            if (i < namesSize - 1)
                sb.append("&b | ");
        }

        return sb.toString();
    }

    /**
     * Checks if the player has permission for the command
     *
     * @param sender sender to check perm for
     * @return true is player has perm
     */
    public PermStatus checkPerm(CommandSender sender) {
        if (sender instanceof Player) {
            if (!Permissions.hasPermission((Player) sender, getPerm()))
                return PermStatus.NO_PERM;
        } else {
            if (needsPlayer())
                return PermStatus.PLAYER_ONLY;
        }

        return PermStatus.GOOD;
    }
}