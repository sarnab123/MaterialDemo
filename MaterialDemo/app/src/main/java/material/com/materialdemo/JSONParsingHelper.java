package material.com.materialdemo;

import android.os.AsyncTask;
import android.os.Message;



/**

 * AsynTask to deserialize json string to class objects

 */

public class JSONParsingHelper extends AsyncTask<String, Void, Message> {



    private Class<? extends IValueObject> mResponseClass;



    private IJSONParsingListener mParsingListener;



    public static interface IJSONParsingListener {

        public void onParseSuccess(IValueObject valueObject);



        public void onParseFailure(Error ex);

    }



    /**

     * Constructor

     *

     * @param responseClass - Class name which has to be populated for JSON

     *            response

     * @param parsingListener - Listener to get parsed response

     */

    public JSONParsingHelper(Class<? extends IValueObject> responseClass,

                             IJSONParsingListener parsingListener) {

        mResponseClass = responseClass;

        mParsingListener = parsingListener;

    }



    @Override

    protected Message doInBackground(String... response) {

        IValueObject responsedata;

        Message message = Message.obtain();

        try {

            responsedata = (IValueObject) UtilityMethods.getModelFromJsonString(response[0],

                    mResponseClass);

        } catch (Exception ex) {

            ex.printStackTrace();

            Error networkException = new Error(ex);

            message.obj = networkException;

            return message;

        }

        message.obj = responsedata;

        return message;

    }



    @Override

    protected void onPostExecute(Message parsedObj) {

        if (parsedObj.obj instanceof IValueObject) {

            mParsingListener.onParseSuccess((IValueObject)parsedObj.obj);

        } else {

            Error error = null;

            if (parsedObj != null && parsedObj.obj != null) {

                error = (Error) parsedObj.obj;

            } else {

                error = new Error(new Exception("Null Value obtained"));

            }

            mParsingListener.onParseFailure(error);

        }

    }

}