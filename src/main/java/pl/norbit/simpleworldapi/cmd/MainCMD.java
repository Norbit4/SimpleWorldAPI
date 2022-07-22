package pl.norbit.simpleworldapi.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.norbit.simpleworldapi.WorldConfig;
import pl.norbit.simpleworldapi.WorldConfigManager;
import pl.norbit.simpleworldapi.WorldManager;
import pl.norbit.simpleworldapi.config.PluginConfig;
import pl.norbit.simpleworldapi.gui.GuiManager;
import pl.norbit.simpleworldapi.gui.GuiType;
import pl.norbit.simpleworldapi.utils.PermissionUtil;
import pl.norbit.simpleworldapi.utils.ChatUtil;

import java.io.IOException;

public class MainCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        if(args.length > 0){

            long t1 = System.currentTimeMillis();
            PermissionUtil permissionUtil = new PermissionUtil(p);
            String arg1 = args[0];

            if(arg1.equalsIgnoreCase("create")) {
                String[] permList = {"swapi.*", "*", "swapi.world.create", "swapi.world.*"};

                if(permissionUtil.hasPermission(permList)){

                    createWorld(p, args);
                }else {
                    p.sendMessage(ChatUtil.format(PluginConfig.PERMISSION_MESSAGE));
                }
            }else if(arg1.equalsIgnoreCase("tp")) {
                String[] permList = {"swapi.*", "*", "swapi.tp"};

                if(permissionUtil.hasPermission(permList)){

                    teleport(p, args);
                }else {
                    p.sendMessage(ChatUtil.format(PluginConfig.PERMISSION_MESSAGE));
                }
            }else if(arg1.equalsIgnoreCase("clone")) {
                String[] permList = {"swapi.*", "*", "swapi.world.clone", "swapi.world.*"};

                if(permissionUtil.hasPermission(permList)){

                    cloneWorld(p, args, t1);
                }else {
                    p.sendMessage(ChatUtil.format(PluginConfig.PERMISSION_MESSAGE));
                }
            }else if(arg1.equalsIgnoreCase("load")){
                String[] permList = {"swapi.*", "*", "swapi.world.load", "swapi.world.*"};

                if(permissionUtil.hasPermission(permList)){

                    loadWorld(p, args, t1);
                }else {
                    p.sendMessage(ChatUtil.format(PluginConfig.PERMISSION_MESSAGE));
                }
            }else if(arg1.equalsIgnoreCase("reload")){
                String[] permList = {"swapi.*", "*", "swapi.reload"};

                if(permissionUtil.hasPermission(permList)){

                    reloadConfig(p, t1);
                }else {
                    p.sendMessage(ChatUtil.format(PluginConfig.PERMISSION_MESSAGE));
                }
            }else{
                sendHelpMessage(p);
            }
        }else{
            sendHelpMessage(p);
        }
        return false;
    }

    private void sendHelpMessage(Player p){
        p.sendMessage("");
        p.sendMessage(ChatUtil.format("&7---------------&8[&aSimpleWorldAPI&8]&7---------------"));
        p.sendMessage(ChatUtil.format("&a* &8/&bswapi &acreate &a<name> &7- create new world"));
        p.sendMessage(ChatUtil.format("&a* &8/&bswapi &atp &a<world>  <x> <y> <z> &7- tp to world"));
        p.sendMessage(ChatUtil.format("&a* &8/&bswapi &aload &a<world> &7- load world"));
        p.sendMessage(ChatUtil.format("&a* &8/&bswapi &aclone &a<world> <new_world> &7- copy world"));
        p.sendMessage("");
    }

    private void reloadConfig(Player p, Long t1){
        try {
            WorldManager.reloadConfigWorlds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int timeMs = (int) (System.currentTimeMillis() - t1);

        String message = "&aConfig reloaded! &8" + timeMs + "ms";
        p.sendMessage(ChatUtil.format(message));
    }
    private void createWorld(Player p, String[] args){
        if(args.length > 1) {

            String worldName = args[1];
            GuiManager.open(p, GuiType.CREATOR_MENU, worldName, null);
        }else{
            String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}", "/swapi create <world>");
            p.sendMessage(ChatUtil.format(message));
        }
    }
    private void teleport(Player p, String[] args){
        if (args.length > 1) {

            String worldName = args[1];
            double x = 0;
            double y= 61;
            double z = 0;
            boolean correctString = true;

            if (args.length > 4){
                try {
                    x = Double.parseDouble(args[2]);
                    y = Double.parseDouble(args[3]);
                    z = Double.parseDouble(args[4]);
                }catch (NumberFormatException e){
                    correctString = false;
                }
            }else if(args.length > 3){
                try {
                    x = Double.parseDouble(args[2]);
                    z = Double.parseDouble(args[3]);
                }catch (NumberFormatException e){
                    correctString = false;
                }
            }

            if(correctString){
                //to center the player in block
                x = x + 0.5;
                z = z + 0.5;

                World world = Bukkit.getWorld(worldName);

                String message;
                if (world != null) {
                    message = PluginConfig.TP_MESSAGE
                            .replace("{WORLD}", worldName)
                            .replace("{PLAYER}", p.getName());

                    p.teleport(new Location(world, x, y, z));
                } else {
                    message = PluginConfig.UNLOADED_WORLD
                            .replace("{WORLD}", worldName);
                }
                p.sendMessage(ChatUtil.format(message));
            }else{
                String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}", "/swapi tp <world>");
                p.sendMessage(ChatUtil.format(message));
            }
        }else{
            String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}", "/swapi tp <world>");
            p.sendMessage(ChatUtil.format(message));
        }
    }

    private void cloneWorld(Player p, String[] args, long t1){

        if(args.length > 2) {

            boolean tempWorld = false;

            if (args.length > 3) {
                tempWorld = Boolean.parseBoolean(args[3].toLowerCase());
            }

            World clone = WorldManager.clone(args[1], args[2], tempWorld);

            if(clone != null) {

                long time = System.currentTimeMillis() - t1;

                String message = PluginConfig.WORLD_CLONE_MESSAGE
                        .replace("{WORLD}", clone.getName())
                        .replace("{TIME}", String.valueOf(time));

                p.sendMessage(ChatUtil.format(message));
            }else{
                String message = "&cError";

                p.sendMessage(ChatUtil.format(message));
            }
        }else{
            String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}",
                    "/swapi clone <world> <new_world>");
            p.sendMessage(ChatUtil.format(message));
        }
    }

    private void loadWorld(Player p, String[] args, long t1){
        if(args.length > 1){
            String worldName = args[1];

            World world = WorldManager.loadWorld(worldName);

            if(world != null){
                long time = System.currentTimeMillis() - t1;
                String message = PluginConfig.WORLD_LOAD_MESSAGE
                        .replace("{WORLD}", world.getName())
                        .replace("{TIME}",String.valueOf(time));

                p.sendMessage(ChatUtil.format(message));

            }else{
                String message = PluginConfig.NON_EXISTENT_WORLD
                        .replace("{WORLD}", worldName);

                p.sendMessage(ChatUtil.format(message));
            }
        }else{
            String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}", "/swapi load <world>");
            p.sendMessage(ChatUtil.format(message));
        }
    }
}
