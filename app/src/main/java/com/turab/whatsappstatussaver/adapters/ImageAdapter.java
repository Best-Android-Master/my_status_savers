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
import com.turab.whatsappstatussaver.models.StatusModel;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private final List<StatusModel> imageList;
    Context context;
    ImageFragment imageFragment;

    public ImageAdapter( Context context, List<StatusModel> imageList, ImageFragment imageFragment) {
        this.context = context;
        this.imageList = imageList;

        this.imageFragment = imageFragment;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_status, parent,
                false);


        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        StatusModel statusModel = imageList.get(position);
        holder.ivThumnailImageView.setImageBitmap(statusModel.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivThumnailImageView;
        ImageButton imageButtonDownLoad;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumnailImageView = itemView.findViewById(R.id.ivThumnail);
            imageButtonDownLoad = itemView.findViewById(R.id.ibSaveToGallery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatusModel statusModel = imageList.get(getAdapterPosition());
                    if (statusModel != null)
                    {
                        imageFragment.downloadImage(statusModel);
                    }
                }
            });

        }
    }
}
