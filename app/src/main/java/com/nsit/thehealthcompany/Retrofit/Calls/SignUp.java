package com.nsit.thehealthcompany.Retrofit.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.gson.JsonObject;
import com.nsit.thehealthcompany.BasicInformation;
import com.nsit.thehealthcompany.DatabaseHelper.UserDetailDatabaseHelper;
import com.nsit.thehealthcompany.HealthyScreen;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import com.nsit.thehealthcompany.TempScreen;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.RetrofitInstance;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class SignUp extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private JsonObject dataObject;
    private JsonObject resultOutput;
    private Response<JsonObject> response;
    UserDetailDatabaseHelper userDetailDatabaseHelper;

    public SignUp(Context context, JsonObject dataObject){
        this.context = context;
        this.dataObject = dataObject;
        userDetailDatabaseHelper=new UserDetailDatabaseHelper(context);
        progressDialog = CommonUtils.getProgressDialog(context, "Signing Up","Please wait.....");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getApiInterface();

        Call<JsonObject> getOffersList = apiInterface.signUpUser(dataObject);

        try {
            response = getOffersList.execute();
            resultOutput = response.body();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error + "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("Code : "+response.code()+" \n IsSuccess : "+response.isSuccessful());


        System.out.println(resultOutput);
        if(response.isSuccessful()){
            if(response.code() == 200 || response.code() ==201){
                int id = resultOutput.get("id").getAsInt();
                String email = resultOutput.get("email").getAsString();
                insertSignUpToLocalDB(resultOutput);
                System.out.println("Data from DB "+userDetailDatabaseHelper.getUserDetails().toString());
                Intent intent=new Intent(context, BasicInformation.class);
                intent.putExtra("id", id);
                intent.putExtra("email", email);
                context.startActivity(intent);
            }
        }
        progressDialog.dismiss();
    }

    private void insertSignUpToLocalDB(JsonObject resultOutput){

        int id = resultOutput.get("id").getAsInt();
        String username = resultOutput.get("username").getAsString();
        String firstName = resultOutput.get("first_name").getAsString();
        String email = resultOutput.get("email").getAsString();
        int phoneNumber = resultOutput.get("phone").getAsInt();

        userDetailDatabaseHelper.insertUserDetails(id, username, firstName, email, phoneNumber);
    }

}
