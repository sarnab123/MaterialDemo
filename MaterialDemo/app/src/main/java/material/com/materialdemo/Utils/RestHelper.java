package material.com.materialdemo.Utils;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import material.com.materialdemo.IValueObject;
import material.com.materialdemo.JSONParsingHelper;
import material.com.materialdemo.model.CompetitiveVO;

/**
 * Created by global on 1/6/16.
 */
public class RestHelper {

    private String privateURL;
    private IRestListener restListener;

    public RestHelper(String url,IRestListener restListener)
    {
        this.privateURL = url;
        this.restListener = restListener;
    }

    private static enum Method
    {
        GET,POST,PUT;
    }

    public void performTask()
    {
//        RestTask task = new RestTask(privateURL);

        String response = performRequestOperation();
        CompetitiveVO competitiveVO = new CompetitiveVO();

        try {
            JSONObject myJson = new JSONObject(response);
            JSONArray priceArray = myJson.getJSONArray("competitorPrices");
            List<CompetitiveVO.CompetitorPrice> competitorPrices = new ArrayList<CompetitiveVO.CompetitorPrice>();
            for (int i = 0 ; i < priceArray.length(); i++)
            {
                JSONObject object = priceArray.getJSONObject(i);
                CompetitiveVO.CompetitorPrice price = competitiveVO.new CompetitorPrice();
                price.setVendorPrice(object.getString("vendorPrice"));
                price.setVendorCd(object.getString("vendorCd"));
                competitorPrices.add(price);
            }
            competitiveVO.setCompetitorPrices(competitorPrices);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        restListener.onSuccess(competitiveVO);

//        JSONParsingHelper helper = new JSONParsingHelper(CompetitiveVO.class, new JSONParsingHelper.IJSONParsingListener() {
//            @Override
//            public void onParseSuccess(IValueObject valueObject) {
//                CompetitiveVO competitiveVO = (CompetitiveVO) valueObject;
//                restListener.onSuccess(competitiveVO);
//            }
//
//            @Override
//            public void onParseFailure(Error ex) {
//            }
//        });
//        helper.execute(response);

//        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    // each task increments this count as soon as it enters its doInBackground()
    // so this allows to track the order of tasks execution
    private AtomicInteger executedTasksCount;

    private class RestTask extends AsyncTask<Void, Void, String> /* Params, Progress, Result */ {

        private final String privateURL;

        RestTask(String privateURL) {
            this.privateURL = privateURL;
        }

        @Override
        protected String doInBackground(Void... params) {
            String response = performRequestOperation();


            return response;
        }

        @Override
        protected void onPostExecute(String result)
        {
            JSONParsingHelper helper = new JSONParsingHelper(CompetitiveVO.class, new JSONParsingHelper.IJSONParsingListener() {
                @Override
                public void onParseSuccess(IValueObject valueObject) {
                    CompetitiveVO competitiveVO = (CompetitiveVO) valueObject;
                    restListener.onSuccess(competitiveVO);
                }

                @Override
                public void onParseFailure(Error ex) {
                }
            });
            helper.execute(result);
        }

    }

    /*
    Perform HTTP Operation to retrieve responses.
     */

    private String performRequestOperation()
    {
        String response = null;
        try {
            URL restURL = new URL(privateURL);
            InputStream urlInputStream = null;

            HttpURLConnection urlConnection = (HttpURLConnection)restURL.openConnection();
            urlConnection.setRequestMethod(Method.GET.name());

            int responseCode = urlConnection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                urlInputStream = urlConnection.getInputStream();
                if (urlInputStream != null) {
                    response = readStream(urlInputStream);
                } else {
                    response = null;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while (reader != null && (line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return builder.toString();
    }

}
