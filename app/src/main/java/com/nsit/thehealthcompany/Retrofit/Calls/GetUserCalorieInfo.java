package com.nsit.thehealthcompany.Retrofit.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nsit.thehealthcompany.DTO.FoodItemDTO;
import com.nsit.thehealthcompany.DTO.UserCalorieDTO;
import com.nsit.thehealthcompany.MainActivity;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.RetrofitInstance;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class GetUserCalorieInfo extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private String email;
    private String password;
    private Response response;
    private ArrayList<UserCalorieDTO> userCalorieDTOArrayList;
    private long totalProtein;
    private long totalCarbs;
    private long totalFat;
    private Toast customToast;


    private ArrayList<FoodItemDTO> getFoodItemDTO(JsonObject jsonObject, String value){

        JsonElement jsonElement = new JsonParser().parse(jsonObject.get(value).getAsString());
        JsonArray itemsJsonArray = jsonElement.getAsJsonArray();
        int size = itemsJsonArray.size();
        ArrayList<FoodItemDTO> foodItemDTOArrayList = new ArrayList<>();
        FoodItemDTO foodItemDTO;
        for(int i=0; i<size; i++){
            foodItemDTO = new FoodItemDTO();
            JsonArray foodItem = itemsJsonArray.get(i).getAsJsonArray();
            foodItemDTO.setId(foodItem.get(0).getAsInt());
            foodItemDTO.setQuantity(foodItem.get(1).getAsInt());
            foodItemDTO.setProtein(foodItem.get(2).getAsInt());
            foodItemDTO.setCarbs(foodItem.get(3).getAsInt());
            foodItemDTO.setFat(foodItem.get(4).getAsInt());
            foodItemDTOArrayList.add(foodItemDTO);
            totalProtein +=  foodItem.get(2).getAsInt();
            totalCarbs +=  foodItem.get(3).getAsInt();
            totalFat +=  foodItem.get(4).getAsInt();
        }
        return foodItemDTOArrayList;
    }

    public GetUserCalorieInfo(Context context, String email, ProgressDialog progressDialog){
        this.context = context;
        this.email = email;
        this.password = password;
        this.progressDialog = progressDialog;
        userCalorieDTOArrayList = new ArrayList<>();
        totalProtein = 0;
        totalCarbs = 0;
        totalFat = 0;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getApiInterface();

        Map<String, String> data = new HashMap<>();
        data.put("email", email);

        Call<JsonArray> userCalorieDTOCall = apiInterface.getUserCalorieData(data);

        try {
            response = userCalorieDTOCall.execute();
            System.out.println("URL for Calorie request : "+userCalorieDTOCall.request().url());
            JsonArray jsonArray = (JsonArray) response.body();
            System.out.println("Json Array : "+jsonArray);

            System.out.println("Inside Get User Calorie Info");

            if (jsonArray != null){
                int jsonArraySize = jsonArray.size();

                System.out.println("Json Array Size : "+jsonArraySize);
                System.out.println("Json Data Received :"+jsonArray);

                for(int i=0; i<jsonArraySize; i++){

                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    UserCalorieDTO userCalorieDTO = new UserCalorieDTO();
                    userCalorieDTO.setId(jsonObject.get("email").getAsString());
                    userCalorieDTO.setDateRecorded(jsonObject.get("date_recorded").getAsString());
                    userCalorieDTO.setCalories(jsonObject.get("calories").getAsInt());
                    userCalorieDTO.setWeight(jsonObject.get("weight").getAsInt());

                    userCalorieDTO.setBreakfast(getFoodItemDTO(jsonObject, "breakfast"));
                    userCalorieDTO.setLunch(getFoodItemDTO(jsonObject, "lunch"));
                    userCalorieDTO.setSnacks(getFoodItemDTO(jsonObject, "snacks"));
                    userCalorieDTO.setDinner(getFoodItemDTO(jsonObject, "dinner"));
                    userCalorieDTO.setTotalProtein(totalProtein);
                    userCalorieDTO.setTotalCarbs(totalCarbs);
                    userCalorieDTO.setTotalFat(totalFat);

                    userCalorieDTOArrayList.add(userCalorieDTO);
                }
                System.out.println("Final data : "+userCalorieDTOArrayList);
            }
        } catch (InterruptedIOException exception) {
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
//        customToast.getView().findViewById(R.id.tryAgainLoginTextView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((LoginScreen)context).startLoginTask(email, password);
//            }
//        });
        customToast.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (progressDialog != null){
            progressDialog.dismiss();
        }

        if (response != null){
            if(response.code() == 201 || response.code() == 200){
                Intent intent = new Intent(context, MainActivity.class);
                intent.putParcelableArrayListExtra("UserCaloriesDetailsList", userCalorieDTOArrayList);
                context.startActivity(intent);
            }
        }
    }
}