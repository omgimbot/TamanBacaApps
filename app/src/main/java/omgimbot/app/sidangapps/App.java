package omgimbot.app.sidangapps;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

/*
 * 	We are creating a Application Singleton Object by extending Application, so it should be declared as a application in the "AndroidMainFests" file
 * */

public class App extends Application
{
    public static final String TAG = App.class.getSimpleName();

    private static Prefs preferences;
    private static App mInstance;
    private static Application sApplication;
    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }
    public static Prefs getPref() {
        return preferences;
    }
    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        if (Build.VERSION.SDK_INT <= 19){
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        mInstance = this;
        sApplication = this;
        preferences = new Prefs(sApplication);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }


    public static synchronized App getInstance()
    {
        return mInstance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
