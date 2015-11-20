package com.indielink.indielink.Profile;

import android.text.format.Time;

import com.facebook.Profile;
import com.indielink.indielink.Network.GetProfilePicture;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProfileContent {

    /**
     * An array of sample (dummy) items.
     */

    public static List<ProfileItem> ITEMS = new ArrayList<ProfileItem>();
    public static Map<String, ProfileItem> ITEM_MAP = new HashMap<String, ProfileItem>();
    public static String ProfilePictureURL;


    public static void InitializeProfile(JSONObject object)
    {
        try {
            String url = object.getJSONObject("picture").getJSONObject("data").getString("url");
            if(ITEMS.size()==0 || ITEM_MAP.size()==0)
            {
                ITEMS = new ArrayList<ProfileItem>();
                ITEM_MAP = new HashMap<String, ProfileItem>();

                String gender = object.get("gender").toString();
                int birth = Integer.parseInt(object.get("birthday").toString().substring(6, 10));
                Time currentTime = new Time();
                currentTime.setToNow();
                String age = String.valueOf(currentTime.year-birth);

                Profile profile = Profile.getCurrentProfile();
                addItem(new ProfileItem("1", "Name : "));
                addItem(new ProfileItem("2", profile.getName()));
                addItem(new ProfileItem("3", "Age :"));
                addItem(new ProfileItem("4", age));
                addItem(new ProfileItem("5", "Gender :"));
                addItem(new ProfileItem("6",gender));
                //addItem(new ProfileItem("7", "About Me :")); //commented out for debug purpose
            }
            if(url != ProfilePictureURL && !url.isEmpty())
            {
                ProfilePictureURL = url;
                new GetProfilePicture(ProfileContent.ProfilePictureURL,null).execute();
            }
            return;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addItem(ProfileItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ProfileItem {
        public String id;
        public String content;

        public ProfileItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
