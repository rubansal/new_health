package com.nsit.thehealthcompany;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nsit.thehealthcompany.Retrofit.Calls.BlogPosts;
import com.nsit.thehealthcompany.Utils.CommonUtils;

import java.util.Objects;

public class BuzzFragment extends Fragment {

    View view;
    private RecyclerView blogPostRecyclerView;
    private ProgressDialog progressDialog;
    private BlogPosts blogPosts;
    private FrameLayout fragmentContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buzz, container, false);
        blogPostRecyclerView = view.findViewById(R.id.blogPostRecyclerView);
        fragmentContainer = (FrameLayout) ((MainActivity) Objects.requireNonNull(getActivity())).getFrameContainer();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = CommonUtils.getProgressDialog(getActivity(), "Getting Posts","Just wait a while..");
        blogPosts =new BlogPosts(getActivity(), blogPostRecyclerView, progressDialog, fragmentContainer);
        blogPosts.execute();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                blogPosts.cancel(true);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("I m destroyed bro");
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.error_layout_file, null);
//        fragmentContainer.removeView(view);
    }
}