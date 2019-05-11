package com.nsit.thehealthcompany.Retrofit.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nsit.thehealthcompany.DTO.UserBasicInformationDTO;
import com.nsit.thehealthcompany.DatabaseHelper.UserCoreDatabaseHelper;
import com.nsit.thehealthcompany.MainActivity;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.RetrofitInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpWithBasicInfo extends AsyncTask<Void, Void, Void> {

    private UserBasicInformationDTO userBasicInformationDTO;

    private ProgressDialog progressDialog;
    private Response response;
    private Context context;
    private String email;

    private JsonObject resultOutput;
    private UserCoreDatabaseHelper userCoreDatabaseHelper;

    public SignUpWithBasicInfo(Context context,String email, UserBasicInformationDTO userBasicInformationDTO){
        this.context = context;
        this.email = email;
        this.userBasicInformationDTO = userBasicInformationDTO;
        userCoreDatabaseHelper=new UserCoreDatabaseHelper(context);
        progressDialog = CommonUtils.getProgressDialog(context, "Signing Up With Basic Info","Please wait.....");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getApiInterface();

        JsonObject userInfoObject = new JsonObject();
        userInfoObject.addProperty("email", userBasicInformationDTO.getID());
        userInfoObject.addProperty("gender", userBasicInformationDTO.getGender());
        userInfoObject.addProperty("height", userBasicInformationDTO.getHeight());
        userInfoObject.addProperty("weight", userBasicInformationDTO.getWeight());
        userInfoObject.addProperty("age", userBasicInformationDTO.getAge());
        userInfoObject.addProperty("goal", userBasicInformationDTO.getGoal());

        System.out.println("Json Object sent : "+userInfoObject);

        Call<JsonObject> signUpWithBasicInfoCall = apiInterface.signUpWithBasicInfo(userInfoObject);

        try {
            response = signUpWithBasicInfoCall.execute();
            resultOutput= (JsonObject) response.body();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error + "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();

        System.out.println("Code after basic info sign up : "+ response.errorBody());
        if(response.code() == 201 || response.code() ==200){
            insertIntoLocalDb(resultOutput);
            GetUserCalorieInfo getUserCalorieInfo = new GetUserCalorieInfo(context, email, progressDialog);
            getUserCalorieInfo.execute();
            System.out.println("Data from core DB "+userCoreDatabaseHelper.getUserData().toString());
        }
    }

    private void insertIntoLocalDb(JsonObject resultOutput){
        int id = resultOutput.get("email").getAsInt();
        String gender = resultOutput.get("gender").getAsString();
        int height = resultOutput.get("height").getAsInt();
        int weight = resultOutput.get("weight").getAsInt();
        int age = resultOutput.get("age").getAsInt();
        String goal = resultOutput.get("goal").getAsString();

        userCoreDatabaseHelper.insertUserData(id, gender, height, weight, age, goal);
    }

}
