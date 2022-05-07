package net.focik.hr.employee.domain.utils;

import java.util.List;

public class PrivilegeHelper {

    /**
     *
     * @param roles list of access roles
     * @param searchRoles list of roles to be checked
     * @return true if any role from @roles is found in @searchRoles
     */
    public static boolean checkAccess(List<String> roles, List<String> searchRoles ){
        for (String role: roles ) {
            if(searchRoles.contains(role))
                return true;
        }
        return false;
    }
}
