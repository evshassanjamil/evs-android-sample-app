package com.evs.android.mysampleapp.week8.recyclerview;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.security.KeyStore;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by hassanjamil on 13/01/2020.
 * @author hassanjamil
 */
public class PicassoTrustAll {

    private final String TAG = PicassoTrustAll.class.getSimpleName();
    private static Picasso mInstance = null;

    private PicassoTrustAll(Context context) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager).build();
            mInstance = new Picasso.Builder(context)
                    .downloader(new OkHttp3Downloader(client))
                    .listener(new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                            Log.e(TAG, exception.getMessage());
                            exception.printStackTrace();
                        }
                    }).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Picasso getInstance(Context context) {
        if (mInstance == null) {
            new PicassoTrustAll(context);
        }
        return mInstance;
    }
}
