//package com.maxsir.repetition.application.renewer;
//
//import android.content.ClipData;
//import android.content.ClipboardManager;
//
//import android.widget.TextView;
//import  android.content.Context;
//import static androidx.core.content.ContextCompat.getSystemService;
//
//public class Copier {
//    public void copy(TextView tv, String result){
//        ClipboardManager clipboard=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//        ClipData clip=ClipData.newPlainText("Copied Text Succeeded",result);
//        tv.setText(result);
//        clipboard.setPrimaryClip(clip);
//    }
//
//}
