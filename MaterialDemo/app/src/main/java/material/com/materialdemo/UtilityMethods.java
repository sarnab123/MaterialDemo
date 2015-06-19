package material.com.materialdemo;

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
}
