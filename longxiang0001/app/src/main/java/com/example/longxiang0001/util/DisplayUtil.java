package com.example.longxiang0001.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayUtil {

    public static int getScreenWidth(Context ctx){
        WindowManager Wm = (WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm =new DisplayMetrics();
        Wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context ctx) {
       WindowManager Wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
       DisplayMetrics dm = new DisplayMetrics();
       Wm.getDefaultDisplay().getMetrics(dm);
       return dm.heightPixels;
    }
    public static float getScreenDensity(Context ctx) {
        WindowManager Wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }
}
