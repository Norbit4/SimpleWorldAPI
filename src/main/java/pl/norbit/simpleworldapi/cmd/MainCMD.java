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
import pl.norbit.simpleworldapi.worldbuilder.SimpleWorld;

public class MainCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0){

            String arg1 = args[0];
            Player p = (Player) sender;
            if(arg1.equalsIgnoreCase("create")) {

                createWorld(p, args);
            }else if(arg1.equalsIgnoreCase("tp")) {

                teleportCmd(p, args);
            }else if(arg1.equalsIgnoreCase("clone")) {
                long l1 = System.currentTimeMillis();

                WorldManager.clone(args[1], args[2], true);

                p.sendMessage(System.currentTimeMillis() - l1 + " ms");
            }
        }
        return false;
    }

    private void createWorld(Player p, String[] args){
        if(args.length > 1) {
            String worldName = args[1];

            long l1 = System.currentTimeMillis();

            SimpleWorld simpleWorld = SimpleWorld.builder()
                    .worldName(worldName)
                    .difficulty(Difficulty.EASY)
                    .build();


            WorldManager.createWorld(simpleWorld);

            long time = System.currentTimeMillis() - l1;

            p.sendMessage(time + "ms");
        }
    }
    private void teleportCmd(Player p, String[] args){
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
            } else {
                p.sendMessage("world = null");
            }
        }
    }
}
