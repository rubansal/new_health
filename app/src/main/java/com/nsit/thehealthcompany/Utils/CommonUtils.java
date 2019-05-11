package com.nsit.thehealthcompany.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.nsit.thehealthcompany.R;

public class CommonUtils {

    public static ProgressDialog getProgressDialog(Context context, String title, String message){
        ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        return progressDialog;
    }

    public static Toast getCustomToast(Context context){
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.START|Gravity.BOTTOM|Gravity.END, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_view, null);
        toast.setView(view);
        return toast;
    }

}
