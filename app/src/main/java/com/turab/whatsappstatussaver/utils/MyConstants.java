package com.turab.whatsappstatussaver.utils;

import android.os.Environment;

import com.turab.whatsappstatussaver.R;

import java.io.File;

public class MyConstants {

    public static final File STATUS_DIRECTORY =
            new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
             "/WhatsApp/Media/.Statuses");

    public static final String APP_DIR = Environment.getExternalStorageState() +
            File.separator + R.string.app_name + ""; //WhatsAppStatusProDir

    public static final int THUMBSIZE = 128;

}
