package com.example.user.a2_singlechoicedialogexam;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{

    Button button,button2;
    String[] items ={"선택항목1","선택항목2","선택항목3"};
    int checkedItem = 0;
    boolean[] checkedItems = {false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                showSingleChoiceDialog();
                break;
            case R.id.button2:
                showMultiChoiceDialog();
                break;
        }
    }
    public void showMultiChoiceDialog(){
        final boolean[] temp = new boolean[checkedItems.length];
        System.arraycopy(checkedItems,0,temp,0,checkedItems.length);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("확인");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);

        builder.setMultiChoiceItems(items,checkedItems,new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for(int i=0;i<checkedItems.length;i++){
                    if(checkedItems[i]){
                        result += items[i] + ". ";
                    }
                }
                Toast.makeText(getApplication(),result,Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
    }

    public void showSingleChoiceDialog(){
        final int temp = checkedItem;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("확인");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);


        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),items[which],Toast.LENGTH_SHORT).show();
                checkedItem = which;
            }
        });
       builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(),items[checkedItem],Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = temp;
            }
        });
        builder.create();
        builder.show();
    }
}
