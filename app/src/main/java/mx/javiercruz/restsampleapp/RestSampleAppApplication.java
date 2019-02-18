package mx.javiercruz.restsampleapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import mx.javiercruz.restsampleapp.api.Api;

/**
 * Created by caltecsoluciones on 18/02/19.
 */

public class RestSampleAppApplication extends MultiDexApplication {
    private static final String TAG = RestSampleAppApplication.class.getName();

    private static RestSampleAppApplication mHolaAmigoApp;

    public static RestSampleAppApplication getApplication() {
        return mHolaAmigoApp;
    }

    public static Context getApplicationCtx(){
        return mHolaAmigoApp.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHolaAmigoApp = this;
        Api.getInstance().setmContext(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
