package com.turab.whatsappstatussaver.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.turab.whatsappstatussaver.R;
import com.turab.whatsappstatussaver.adapters.ImageAdapter;
import com.turab.whatsappstatussaver.adapters.VideoAdapter;
import com.turab.whatsappstatussaver.models.StatusModel;
import com.turab.whatsappstatussaver.utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class VideoFragment extends Fragment {


    RecyclerView recyclerView;
    ProgressBar progressBar;

    Handler handler = new Handler();

    ArrayList<StatusModel> videoModerlArrayList;

    VideoAdapter videoAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewVideo);
        progressBar = view.findViewById(R.id.progressBarVideo);
        videoModerlArrayList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        getStatusVideos();


    }

    private void getStatusVideos() {

        if (MyConstants.STATUS_DIRECTORY.exists())
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORY.listFiles();

                    if (statusFiles != null && statusFiles.length>0)
                    {
                        Arrays.sort(statusFiles);

                        for (final File statusFile: statusFiles)
                        {
                            StatusModel statusModel = new StatusModel(statusFile,
                                    statusFile.getName(),statusFile.getAbsolutePath());

                            statusModel.setThumbnail(getThumnail(statusModel));
                            if (statusModel.isVideo())
                            {
                                videoModerlArrayList.add(statusModel);
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                progressBar.setVisibility(View.GONE);
                                videoAdapter = new VideoAdapter(getContext(), videoModerlArrayList,
                                        VideoFragment.this);
                                recyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                    else
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "File not found", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }

    }

    private Bitmap getThumnail(StatusModel statusModel) {
        if (statusModel.isVideo())
        {
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(),
                    MediaStore.Video.Thumbnails.MICRO_KIND);
            //BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
            //MyConstants.THUMBSIZE,MyConstants.THUMBSIZE
        }
        else
        {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()
                    ),
                    MyConstants.THUMBSIZE, MyConstants.THUMBSIZE);
        }
    }
}
