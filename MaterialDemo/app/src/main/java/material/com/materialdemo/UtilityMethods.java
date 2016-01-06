package material.com.materialdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 */
public class UtilityMethods {

    static String VALUE_ADDED_ICON_ONLINE =
            "//d16rliti0tklvn.cloudfront.net/5/1376916272203.373873069.gif";

    static String VALUE_ADDED_ICON_MORE_COLORS =
            "//d16rliti0tklvn.cloudfront.net/5/1376916393655.1217695000.gif";

    static String VALUE_ADDED_ICON_BOGO_1_1_D_100 = "//d16rliti0tklvn.cloudfront.net/5/bogo_1_1_D_100.gif";

    static String VALUE_ADDED_ICON_BOGO_1_1_P_50 = "//d16rliti0tklvn.cloudfront.net/5/bogo_1_1_P_50.gif";

    static String VALUE_ADDED_ICON_BOGO_1_1 = "//d16rliti0tklvn.cloudfront.net/5/bogo_1_1.gif";

    static String VALUE_ADDED_ICON_BOGO_2_1 = "//d16rliti0tklvn.cloudfront.net/5/bogo_2_1.gif";

    static String VALUE_ADDED_ICON_REBATE = "//d16rliti0tklvn.cloudfront.net/5/rebate.gif";

    static String VALUE_ADDED_ICON_WARNING = "//d16rliti0tklvn.cloudfront.net/5/war.gif";

    static String VALUE_ADDED_ICON_BUY_1_GET_0_50_PERCENTAGE =
            "//d16rliti0tklvn.cloudfront.net/5/1376918161165.1885808990.gif";
    public static Object getModelFromJsonString(String response, Class modelClass) {
        Gson gson = new GsonBuilder().create();
        JsonReader reader = new JsonReader(new StringReader(response));
        reader.setLenient(true);
        return gson.fromJson(reader, modelClass);
    }

    public static String getUpdatedURL(String imageURL, int width, int height) {

        if (imageURL.indexOf("?") == -1) {

            return imageURL;

        } else {

            StringBuilder imageBuilder = new StringBuilder(imageURL.substring(0,

                    imageURL.indexOf("?")));

            imageBuilder.append("?wid=" + width);

            imageBuilder.append("&hei=" + height);

            imageBuilder.append("&op_sharpen=1");

            return imageBuilder.toString();

        }


    }

    public static boolean isPointInsideView(float x, float y, View view) {
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        //point is inside view bounds
        if ((x > viewX && x < (viewX + view.getWidth())) &&
                (y > viewY && y < (viewY + view.getHeight()))) {
            return true;
        } else {
            return false;
        }
    }

    public static String getValueAddedIconUrl(String valueAddedIcons) {

        String valueAddedIcon = valueAddedIcons.toLowerCase();
        if (!TextUtils.isEmpty(valueAddedIcon)) {
            if (valueAddedIcon.indexOf("online") != -1) {
                return VALUE_ADDED_ICON_ONLINE;
            } else if (valueAddedIcon.indexOf("morecolors") != -1) {
                return VALUE_ADDED_ICON_MORE_COLORS;
            } else if (valueAddedIcon.indexOf("bogo_1_1_d_100") != -1) {
                return VALUE_ADDED_ICON_BOGO_1_1_D_100;
            } else if (valueAddedIcon.indexOf("bogo_1_1_p_50") != -1) {
                return VALUE_ADDED_ICON_BOGO_1_1_P_50;
            } else if (valueAddedIcon.indexOf("bogo_1_1") != -1) {
                return VALUE_ADDED_ICON_BOGO_1_1;
            } else if (valueAddedIcon.indexOf("bogo_2_1") != -1) {
                return VALUE_ADDED_ICON_BOGO_2_1;
            } else if (valueAddedIcon.indexOf("buy_1_get_0_50_percentage") != -1
                    || valueAddedIcons.indexOf("buy_1_get_1_50_percentage") != -1) {
                return VALUE_ADDED_ICON_BUY_1_GET_0_50_PERCENTAGE;
            } else if (valueAddedIcon.indexOf("rebate") != -1) {
                return VALUE_ADDED_ICON_REBATE;
            } else if (valueAddedIcon.indexOf("warning") != -1) {
                return VALUE_ADDED_ICON_WARNING;
            }
        }
        return null;
    }

    public static void replaceFragment(Activity acty, Fragment fragment, int fragmentContainerID) {
        FragmentTransaction transaction = acty.getFragmentManager().beginTransaction();
        transaction.replace(fragmentContainerID, fragment);
        transaction.commit();
    }
    public static float getKohlsFormatedRating(float rating) {
        float newRating;
        if (((rating) - ((int) rating)) >= 0.5) {
            newRating = (float) (((int) rating) + 0.5);
        } else {
            newRating = ((int) rating);
        }
        return newRating;

    }
}
