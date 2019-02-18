package mx.javiercruz.restsampleapp.utils;

import android.content.Context;
import android.widget.Toast;

public final class UIUtil {

    public static void showToast(Context context, String text) {
        if (text != null) {
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}
