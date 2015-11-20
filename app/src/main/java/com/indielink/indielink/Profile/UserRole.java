package com.indielink.indielink.Profile;

/**
 * Created by lawFuck on 2015/11/20.
 */
public class UserRole {
    private static boolean IsBand;

    public static void IsBand()
    {
        IsBand=true;
    }

    public static void IsMusician()
    {
        IsBand=false;
    }

    public static boolean GetUserRole()
    {
        return IsBand;
    }
}
