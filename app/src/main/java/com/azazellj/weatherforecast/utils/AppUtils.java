package com.azazellj.weatherforecast.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.TypedValue;

import com.azazellj.weatherforecast.App;

/**
 * Created by azazellj on 28.05.16.
 */
public class AppUtils {

    /**
     * Getter of ConnectivityManager for network status checking
     *
     * @return ConnectivityManager
     */
    private static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Check if network is available
     *
     * @return boolean network status
     */
    public static boolean isNetworkOn() {
        ConnectivityManager connMgr = getConnectivityManager();

        NetworkInfo networkInfo;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                return true;
            }
        } else {
            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                return true;
            }

            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                return true;
            }
        }

        return false;
    }

    public static int getPxFromDpValue(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    public static Resources getResources() {
        return Resources.getSystem();
    }
}
