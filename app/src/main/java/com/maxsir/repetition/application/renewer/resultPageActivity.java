package com.maxsir.repetition.application.renewer;

import androidx.appcompat.app.AppCompatActivity;

//import com.maxsir.repetition.application.renewer.Copier;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private String receivedTextByIntent;

    @SuppressLint("SetTextI18n")
    public void onGetActivityIntent(){
        Intent intent=getIntent();
        String string=intent.getStringExtra(SWITCH_NAME_OF_INTENT);
        TextView tv=(TextView) findViewById(R.id.textViewResultPageSender);
        tv.setText(string);
        copy(tv,string);
        receivedTextByIntent=string;
        tv.setText(tv.getText().toString()+getString(R.string.mark_as_Copied));
    }

    public void copy(TextView tv, String result){
        ClipboardManager clipboard=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText(getString(R.string.copiedSucceededText),result);
        tv.setText(result);
        clipboard.setPrimaryClip(clip);
    }

    public void SendByMessangerOnClick(View view) {
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBoxResultSender);
        CheckBox checkBoxService=(CheckBox) findViewById(R.id.checkBox2);
        if(checkBox.isChecked()){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,receivedTextByIntent);
            if(!checkBoxService.isChecked()){
                Intent chooser = Intent.createChooser(intent, getString(R.string.shareText));
                startActivity(chooser);
            }else {
                startActivity(intent);
            }
        }

    }

    public void stateChanged(View view) {
        Button tv=(Button) findViewById(R.id.button);
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkBoxMainSelected=(CheckBox) findViewById(R.id.checkBoxResultSender);
        if(!checkBoxMainSelected.isChecked()){
            checkBox.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
        }else{
            checkBox.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
        }
    }
}