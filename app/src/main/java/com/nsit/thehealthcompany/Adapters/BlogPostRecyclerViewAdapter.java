package com.nsit.thehealthcompany.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nsit.thehealthcompany.DTO.BlogPostDTO;
import com.nsit.thehealthcompany.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BlogPostRecyclerViewAdapter extends RecyclerView.Adapter<BlogPostRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BlogPostDTO> blogPostDTOArrayList;
    private BlogPostDTO blogPostDTO;

    public BlogPostRecyclerViewAdapter(Context context, ArrayList<BlogPostDTO> blogPostDTOArrayList){
        this.context = context;
        this.blogPostDTOArrayList = blogPostDTOArrayList;
    }

    @NonNull
    @Override
    public BlogPostRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(context).inflate(R.layout.blog_post_single_layout, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogPostRecyclerViewAdapter.ViewHolder holder, int position) {
        blogPostDTO = blogPostDTOArrayList.get(position);
        holder.authorNameTextView.setText("Sanjay Kumar");
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(blogPostDTO.getPhoto()).into(holder.postImage);
        holder.title.setText(blogPostDTO.getTitle());
        holder.description.setText(blogPostDTO.getDescription());
        holder.datePublishedTextView.setText(blogPostDTO.getDatePublished().substring(0,10));
        holder.readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlogPostUrl(blogPostDTO.getRedirectURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogPostDTOArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView authorNameTextView;
        private ImageView postImage;
        private TextView title;
        private TextView description;
        private TextView datePublishedTextView;
        private Button readMoreButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.authorNameTextView);
            postImage = itemView.findViewById(R.id.postImage);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            datePublishedTextView = itemView.findViewById(R.id.datePublishedTextView);
            readMoreButton = itemView.findViewById(R.id.readMoreButton);
        }
    }

    private void openBlogPostUrl(String url){
        WebView webView;
        webView=new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        final Activity activity=(Activity) context;

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                onReceivedError(view, error.getErrorCode(), error.getDescription().toString(), request.getUrl().toString());
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(url);
        ((Activity) context).setContentView(webView);

    }
}
