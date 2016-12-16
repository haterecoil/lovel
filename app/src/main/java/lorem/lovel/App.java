package lorem.lovel;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import lorem.lovel.network.LruBitmapCache;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by mrgn on 16/12/2016.
 *
 */

public class App extends Application {

    //  Instance singleton
    private static App sharedInstance;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gt-walsheim-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        requestQueue = Volley.newRequestQueue(this);

        LruBitmapCache lruBitmapCache = new LruBitmapCache(8 * 1024 * 1024);
        imageLoader = new ImageLoader(requestQueue, lruBitmapCache);

        App.sharedInstance = this;
    }

    public static App getSharedInstance() {
        return sharedInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}

