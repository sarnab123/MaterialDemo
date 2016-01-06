
package material.com.materialdemo;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class LoadImageTask {

    // Volley request queue.
    private RequestQueue mRequestQueue;

    // Image loader of volley.
    // private ImageLoader mImageLoader;

    // Singleton instance of load image task.
    private static LoadImageTask mInstance;

    private LoadImageTask(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        // mImageLoader = new ImageLoader(mRequestQueue, BitmapLruCache.open());
    }

    /**
     * Get single instance LoadImageTask.
     * 
     * @return single instance of LoadImageTask.
     */
    public static synchronized LoadImageTask getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoadImageTask(context);
        }
        return mInstance;
    }

    /**
     * Load Image from given url using volley image loader.
     * 
     * @param imageUrl url of the image.
     * @param view image view that will display image.
     * @param defaultImageResId default image resource id.
     * @param errorImageResId error image resource id.
     */
    public void loadImage(String imageUrl,
            ImageView view,
            int defaultImageResId,
            int errorImageResId) {

        ImageLoader imageLoader = new ImageLoader(mRequestQueue, BitmapLruCache.open());

        if (view != null) {
            if (view instanceof NetworkImageView) {
                if (defaultImageResId > 0) {
                    ((NetworkImageView) view).setDefaultImageResId(defaultImageResId);
                }
                if (errorImageResId > 0) {
                    ((NetworkImageView) view).setErrorImageResId(errorImageResId);
                }
                if (!TextUtils.isEmpty(imageUrl)) {
                    ((NetworkImageView) view).setImageUrl(imageUrl, imageLoader);
                } else {
                    ((NetworkImageView) view).setImageResource(errorImageResId);
                }
            } else {
                if (defaultImageResId > 0) {
                    view.setImageResource(defaultImageResId);
                }
                if (!TextUtils.isEmpty(imageUrl)) {
                    imageLoader.get(imageUrl,
                            ImageLoader.getImageListener(view, defaultImageResId, errorImageResId));
                }
            }
        }
    }

}
