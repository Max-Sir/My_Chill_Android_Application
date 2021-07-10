package com.maxsir.repetition.application.renewer;

import androidx.appcompat.app.AppCompatActivity;

//import com.maxsir.repetition.application.renewer.Copier;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class resultPageActivity extends AppCompatActivity {
    public static final String SWITCH_NAME_OF_INTENT="nextFromText";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        onGetActivityIntent();

    }

    public void onGetActivityIntent(){
        Intent intent=getIntent();
        String string=intent.getStringExtra(SWITCH_NAME_OF_INTENT);
        TextView tv=(TextView) findViewById(R.id.textViewResultPageSender);
        tv.setText(string);
        copy(tv,string);
    }

    public void copy(TextView tv, String result){
        ClipboardManager clipboard=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText("Copied Text Succeeded",result);
        tv.setText(result);
        clipboard.setPrimaryClip(clip);
    }

    public void SendByMessangerOnClick(View view) {
        TextView tv=(TextView) findViewById(R.id.textViewResultPageSender);
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBoxResultSender);
        if(checkBox.isChecked()){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,tv.getText());
            Intent chooser = Intent.createChooser(intent, getString(R.string.shareText));
            startActivity(chooser);
            //startActivity(intent);
        }

    }
}