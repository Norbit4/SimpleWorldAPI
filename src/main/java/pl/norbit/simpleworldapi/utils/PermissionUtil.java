package pl.norbit.simpleworldapi.utils;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class PermissionUtil {
    private final HashSet<String> permissionsSet;

    public PermissionUtil(Player p) {
        permissionsSet = new HashSet<>();

        p.getEffectivePermissions().forEach(permissionAttachmentInfo -> {
            permissionsSet.add(permissionAttachmentInfo.getPermission());
        });
    }

    public boolean hasPermission(String [] perms){

        for (String perm : perms) {

            if(permissionsSet.contains(perm)){
                return true;
            }
        }
        return false;
    }
}
