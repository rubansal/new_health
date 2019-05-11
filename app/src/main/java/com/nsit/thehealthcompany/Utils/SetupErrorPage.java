package com.nsit.thehealthcompany.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nsit.thehealthcompany.R;
import com.nsit.thehealthcompany.Retrofit.Calls.BlogPosts;

import androidx.recyclerview.widget.RecyclerView;

public class SetupErrorPage {
    private Context context;
    private FrameLayout fragmentContainer;
    private int index;
    private RecyclerView blogPostRecyclerView;
    private View mainView;

    public SetupErrorPage(Context context, FrameLayout fragmentContainer,RecyclerView blogPostRecyclerView, int index) {
        this.context = context;
        this.fragmentContainer = fragmentContainer;
        this.blogPostRecyclerView = blogPostRecyclerView;
        this.index = index;
    }

    public void setPage(){
        View errorView = LayoutInflater.from(context).inflate(R.layout.error_layout_file, null);
        TextView tryAgainTextView = errorView.findViewById(R.id.tryAgainTextView);
        tryAgainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 3){
                    ProgressDialog progressDialog = CommonUtils.getProgressDialog(context, "Getting Posts","Just wait a while..");
                    fragmentContainer.removeViewAt(0);
                    fragmentContainer.addView(mainView);
                    BlogPosts blogPosts =new BlogPosts(context, blogPostRecyclerView, progressDialog, fragmentContainer);
                    blogPosts.execute();
                }
            }
        });
        mainView = fragmentContainer.getChildAt(0);
        fragmentContainer.removeViewAt(0);
        fragmentContainer.addView(errorView);
    }

}
