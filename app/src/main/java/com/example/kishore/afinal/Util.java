package com.example.kishore.afinal;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by poova-pt1297 on 4/2/2017.
 */

class Util {
    static int dpToPx(int px, Resources resources){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px , resources.getDisplayMetrics());
    }
}
