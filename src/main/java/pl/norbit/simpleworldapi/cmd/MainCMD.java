package pl.norbit.simpleworldapi.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.norbit.simpleworldapi.WorldManager;
import pl.norbit.simpleworldapi.config.PluginConfig;
import pl.norbit.simpleworldapi.utils.ChatUtil;
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

public class MainCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        if(args.length > 0){

            long t1 = System.currentTimeMillis();
            String arg1 = args[0];

            if(arg1.equalsIgnoreCase("create")) {

                createWorld(p, args, t1);
            }else if(arg1.equalsIgnoreCase("tp")) {

                teleport(p, args);
            }else if(arg1.equalsIgnoreCase("clone")) {

                cloneWorld(p, args, t1);
            }else if(arg1.equalsIgnoreCase("load")){

                loadWorld(p, args, t1);
            }
        }else{
            p.sendMessage(ChatUtil.format(""));
            p.sendMessage(ChatUtil.format("&7---------------&8[&aSimpleWorldAPI&8]&7---------------"));
            p.sendMessage(ChatUtil.format("&a* &8/&bswapi &acreate &a<name> &7- create new world"));
            p.sendMessage(ChatUtil.format("&a* &8/&bswapi &atp &a<world>  <x> <y> <z> &7- tp to world"));
            p.sendMessage(ChatUtil.format("&a* &8/&bswapi &aload &a<world> &7- load world"));
            p.sendMessage(ChatUtil.format("&a* &8/&bswapi &aclone &a<world> <new_world> &7- copy world"));
            p.sendMessage(ChatUtil.format(""));
        }
        return false;
    }

    private void createWorld(Player p, String[] args, long t1){
        if(args.length > 1) {
            String worldName = args[1];

            SimpleWorld simpleWorld = SimpleWorld.builder()
                    .worldName(worldName)
                    .difficulty(Difficulty.EASY)
                    .temporaryWorld(true)
                    .templateWorld(true)
                    .build();

            WorldManager.createWorld(simpleWorld);

            long time = System.currentTimeMillis() - t1;
            String message = PluginConfig.WORLD_CREATE_MESSAGE
                    .replace("{WORLD}", worldName)
                    .replace("{TIME}",String.valueOf(time));

            p.sendMessage(ChatUtil.format(message));
        }else{
            String message = PluginConfig.WRONG_ARGS_PREFIX.replace("{CMD}", "/swapi create <world>");
            p.sendMessage(ChatUtil.format(message));
        }
    }
    private void teleport(Player p, String[] args){
        if (args.length > 1) {

            String worldName = args[1];
            double x;
            double y;
            double z;

            if (args.length > 4){
                x = Double.parseDouble(args[2]);
                y = Double.parseDouble(args[3]);
                z = Double.parseDouble(args[4]);
            }else if(args.length > 3){
                x = Double.parseDouble(args[2]);
                y = 61;
                z = Double.parseDouble(args[3]);
            }else{
                x = 0;
                y = 61;
                z = 0;
            }

            //to center the player in block
            x = x + 0.5;
            z = z + 0.5;

            World world = Bukkit.getWorld(worldName);

            if (world != null) {

                p.teleport(new Location(world, x, y, z));
                String message = PluginConfig.TP_MESSAGE
                        .replace("{WORLD}", worldName)
                        .replace("{PLAYER}",p.getName());
                p.sendMessage(ChatUtil.format(message));
            } else {
                String message = PluginConfig.UNLOADED_WORLD
                        .replace("{WORLD}", worldName);

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

            long time = System.currentTimeMillis() - t1;

            String message = PluginConfig.WORLD_CLONE_MESSAGE
                    .replace("{WORLD}", clone.getName())
                    .replace("{TIME}",String.valueOf(time));

            p.sendMessage(ChatUtil.format(message));
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
