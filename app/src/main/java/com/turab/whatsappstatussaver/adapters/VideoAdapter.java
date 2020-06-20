package com.turab.whatsappstatussaver.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turab.whatsappstatussaver.R;
import com.turab.whatsappstatussaver.fragments.ImageFragment;
import com.turab.whatsappstatussaver.fragments.VideoFragment;
import com.turab.whatsappstatussaver.models.StatusModel;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    private final List<StatusModel> videoList;
    Context context;
    VideoFragment videoFragment;

    public VideoAdapter( Context context, List<StatusModel> videoList, VideoFragment videoFragment) {
        this.videoList = videoList;
        this.context = context;
        this.videoFragment = videoFragment;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_status, parent,
                false);


        return new VideoAdapter.VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        StatusModel statusModel = videoList.get(position);
        holder.ivThumnailImageView.setImageBitmap(statusModel.getThumbnail());

    }

    @Override
    public int getItemCount() {
       return videoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {


        ImageView ivThumnailImageView;
        ImageButton imageButtonDownLoad;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumnailImageView = itemView.findViewById(R.id.ivThumnail);
            imageButtonDownLoad = itemView.findViewById(R.id.ibSaveToGallery);
        }
    }
}
