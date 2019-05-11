package com.nsit.thehealthcompany.Retrofit.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.nsit.thehealthcompany.Adapters.BlogPostRecyclerViewAdapter;
import com.nsit.thehealthcompany.DTO.BlogPostDTO;
import com.nsit.thehealthcompany.MainActivity;
import com.nsit.thehealthcompany.R;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.RetrofitInstance;
import com.nsit.thehealthcompany.Utils.SetupErrorPage;

import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;

public class BlogPosts extends AsyncTask<Void, Void, Void> {
    private Context context;
    private ProgressDialog progressDialog;
    private Response response;
    private ArrayList<BlogPostDTO> blogPostDTOArrayList;
    private RecyclerView blogPostRecyclerView;
    private SetupErrorPage setupErrorPage;

    public BlogPosts(Context context, RecyclerView blogPostRecyclerView, ProgressDialog progressDialog, FrameLayout fragmentContainer){
        this.context = context;
        this.blogPostRecyclerView = blogPostRecyclerView;
        this.progressDialog = progressDialog;
        setupErrorPage = new SetupErrorPage(context, fragmentContainer, blogPostRecyclerView, 3);
        blogPostDTOArrayList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getApiInterface();
        Call<JsonArray> getBlogPostsCall = apiInterface.getBlogPosts();
        Gson gson = new Gson();

        try {
            response = getBlogPostsCall.execute();
            JsonArray blogPostsJsonArray = (JsonArray) response.body();
            Type type = new TypeToken<ArrayList<BlogPostDTO>>(){}.getType();
            System.out.println("Posts Output : "+blogPostsJsonArray);


            if (blogPostsJsonArray != null){
                blogPostDTOArrayList = gson.fromJson(blogPostsJsonArray.toString(), type);
                System.out.println("Hey Post 1: "+blogPostDTOArrayList.get(0).toString());
            }

        } catch (InterruptedIOException exception){
            System.out.println("I m InterruptedIOException");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error + "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        System.out.println("I m Cancelled bro");
        setupErrorPage.setPage();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("Result Output : "+response);
        if (response != null){
            if (response.code() == 201 || response.code() == 200){
                BlogPostRecyclerViewAdapter blogPostRecyclerViewAdapter = new BlogPostRecyclerViewAdapter(context, blogPostDTOArrayList);
                blogPostRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                blogPostRecyclerView.setAdapter(blogPostRecyclerViewAdapter);
            }
            else{
                Toast.makeText(context, "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        }
        progressDialog.dismiss();
    }

}
