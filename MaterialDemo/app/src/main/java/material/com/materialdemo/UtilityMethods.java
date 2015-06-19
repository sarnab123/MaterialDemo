package material.com.materialdemo;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 */
public class UtilityMethods {
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

    public static boolean isPointInsideView(float x, float y, View view){
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        //point is inside view bounds
        if(( x > viewX && x < (viewX + view.getWidth())) &&
                ( y > viewY && y < (viewY + view.getHeight()))){
            return true;
        } else {
            return false;
        }
    }
}
