package mx.javiercruz.restsampleapp.api;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import mx.javiercruz.restsampleapp.interfaces.GenericEventListener;
import mx.javiercruz.restsampleapp.models.Product;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by caltecsoluciones on 18/02/19.
 */

public class Api {
    private static final String TAG = Api.class.getSimpleName();

    private static final String BASE_URL = "https://super.walmart.com.mx/api/rest/";

    private static final int        CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int        CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final Executor THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(CORE_POOL_SIZE);
    private static Api singleton;
    private Retrofit retrofit;
    private WebService  webService;
    private Context mContext;

    public static Api getInstance() {
        if (singleton == null) {
            createInstance();
        }
        return singleton;
    }

    private synchronized static void createInstance() {
        if (singleton == null) {
            singleton = new Api();
        }
    }

    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(logging);
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(builder.build())
                .callbackExecutor(THREAD_POOL_EXECUTOR)
                .build();

        webService = retrofit.create(WebService.class);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    //obs
    private Observable<Product> getProductObs() {
        return webService.product()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //calls
    public void getProductAPI(GenericEventListener<Product> listener) {
        Observable<Product> productObs = Api.getInstance().getProductObs();
        productObs.subscribe(new Subscriber<Product>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "getProductoAPI.error "+e.getMessage());
                listener.onError(e.getMessage());
            }

            @Override
            public void onNext(Product item) {
                listener.onSuccess(item);
            }
        });
    }

}
