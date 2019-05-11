package com.nsit.thehealthcompany.Retrofit.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nsit.thehealthcompany.LoginScreen;
import com.nsit.thehealthcompany.MainActivity;
import com.nsit.thehealthcompany.R;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.RetrofitInstance;
import com.nsit.thehealthcompany.Utils.SessionManagement;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String email;
    private String password;
    private ProgressDialog progressDialog;
    private JsonArray resultOutput;
    private Response<JsonArray> response;
    private GetUserCalorieInfo getUserCalorieInfo;
    private Toast customToast;

    public Login(Context context, String email, String password, ProgressDialog progressDialog){
        this.context = context;
        this.email = email;
        this.password = password;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getApiInterface();

        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);

        Call<JsonArray> loginUserCall = apiInterface.loginUser(data);

        try {

            System.out.println("URL : "+loginUserCall.request().url());
            response = loginUserCall.execute();
            System.out.println("Response test : "+response);
            resultOutput = response.body();

        } catch (InterruptedIOException exception){
            System.out.println("I m InterruptedIOException");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error + "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        customToast = CommonUtils.getCustomToast(context);
//        TextView textView = customToast.getView().findViewById(R.id.tryAgainLoginTextView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("I m clicked bro button");
//                ((LoginScreen)context).startLoginTask(email, password);
//            }
//        });
        customToast.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("Result Output : "+resultOutput);
        if (response != null){
            if (response.code() == 201 || response.code() == 200){
                SessionManagement sessionManagement = new SessionManagement(context);
                sessionManagement.addUser(email, password, true);

                getUserCalorieInfo = new GetUserCalorieInfo(context, email, progressDialog);
                getUserCalorieInfo.execute();
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        getUserCalorieInfo.cancel(true);
                    }
                });
            }
            else{
                CommonUtils.getCustomToast(context).show();
            }
        }
    }

}
