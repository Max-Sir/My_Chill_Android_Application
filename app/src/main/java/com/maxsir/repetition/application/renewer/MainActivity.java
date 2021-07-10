package com.maxsir.repetition.application.renewer;

import java.lang.Math;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void doSomething(View view){
        TextView txt= findViewById(R.id.Text1);
        Spinner spinner= findViewById(R.id.color);
        String text= spinner.getSelectedItem().toString();
        txt.setText(text);
        Toast hello_max = Toast.makeText(this, "Hello Max", Toast.LENGTH_SHORT);
        hello_max.show();
        //TextView tx=new TextView();
    }

    public void onClickSwitchPage(View view) {
        Intent intent=new Intent(this,resultPageActivity.class);
        intent.putExtra(resultPageActivity.SWITCH_NAME_OF_INTENT,((TextView)findViewById(R.id.Text1)).getText());
        startActivity(intent);
    }

    public void goToStopwatchOnClick(View view) {
        Intent intent=new Intent(this,TimerActivity.class);
        startActivity(intent);
    }

    public  String createRandomIntegerString(int length){
        StringBuilder stringBuilder=new StringBuilder();
        String hexsInterpolation="0123456789abcdef";

        for (int i = 0; i < length; ++i) {
            int tmpInt=new Random().nextInt();
            int x=Math.abs(tmpInt)%16;
            stringBuilder.append(hexsInterpolation.charAt(x));
        }
        return stringBuilder.toString();
    }

    public void changeBackgroundColor(View view) {
        LinearLayout layout=findViewById(R.id.LLMain);
        String color="#"+createRandomIntegerString(0x08);
        layout.setBackgroundColor(Color.parseColor(color));
        Toast tt=Toast.makeText(this,"Randomized current color is: "+color,Toast.LENGTH_SHORT);
        tt.show();
        ClipboardManager clipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText(getString(R.string.copiedSucceededText),color);
        clipboard.setPrimaryClip(clip);
    }
}