package mx.javiercruz.restsampleapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by javiercruz on 01/11/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void mostrarProgressDialog(String mensaje) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(mensaje);
            mDialog.setCancelable(false);
            mDialog.show();
        } else {
            mDialog.setMessage(mensaje);
        }
    }

    public void ocultarProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
