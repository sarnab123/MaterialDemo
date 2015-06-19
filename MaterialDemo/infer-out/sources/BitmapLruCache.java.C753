
package material.com.materialdemo;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Bitmap LRU cache implemented holding bitmaps in main memory.
 *
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

    // Singleton instance of LRU cache.
    private static BitmapLruCache mInstance;

    private BitmapLruCache() {
        super(getDefaultLruCacheSize());
    }

    /**
     * Opens LRU cache
     * 
     * @return single instance of LRU cache.
     */
    public static synchronized BitmapLruCache open() {
        if (mInstance == null) {
            mInstance = new BitmapLruCache();
        }
        return mInstance;
    }

    /**
     * Get default cache size that is 1/8th of run time memory of application.
     * 
     * @return
     */
    private static int getDefaultLruCacheSize() {
        // Get maximum memory available to application in KB.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // Set cache size to 1/8th of maximum memory.
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }

    @Override
    protected int sizeOf(String key, Bitmap bitmap) {
        // The cache size will be measured in kilobytes rather than
        // number of items.
        return bitmap.getByteCount() / 1024;
    }

    /**
     * Get bitmap from cache for the given key value.
     * 
     * @param key key of bitmap.
     * @return bitmap from cache for the given key value.
     */
    @Override
    public Bitmap getBitmap(String key) {
        return super.get(key);
    }

    /**
     * Store bitmap in cache for the given key value.
     * 
     * @param key bitmap's key
     * @param bitmap bitmap that will be stored in the cache.
     */
    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        try {
            synchronized (this) {
                if (this.get(key) != null) {
                    super.remove(key);
                }
            }
            super.put(key, bitmap);
        } catch (OutOfMemoryError memoryError) {
            // TODO(Ankit Grover): Bad way to handle out of memory
            // issues, replace this try-catch with proper memory handling
            // algorithm.
            super.evictAll();
        }
    }
}
