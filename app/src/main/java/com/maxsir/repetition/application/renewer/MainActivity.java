package com.maxsir.repetition.application.renewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void doSomething(View view){
        TextView txt=(TextView) findViewById(R.id.Text1);
        Spinner spinner=(Spinner) findViewById(R.id.color);
        String curitem=spinner.getSelectedItem().toString();
        String text=String.valueOf(curitem);
        txt.setText(text);
    }

    public void onClickSwitchPage(View view) {
        Intent intent=new Intent(this,resultPageActivity.class);
        intent.putExtra(resultPageActivity.SWITCH_NAME_OF_INTENT,((TextView)findViewById(R.id.Text1)).getText());
        startActivity(intent);
    }
}