package com.lostli.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lost.sslayout.SimpleSwitchLayout;
import com.lostli.simpleswitchlayout.R;


public class MainActivity extends AppCompatActivity {
    private SimpleSwitchLayout simpleSwitchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleSwitchLayout = (SimpleSwitchLayout) findViewById(R.id.simpleswitchlayout);

        simpleSwitchLayout.bindView(R.id.tv_page_1,R.id.tv_page_2,R.id.tv_page_3);

    }

    public void switchPage(View view){
        switch (view.getId()){
            case R.id.btn_page1:
                simpleSwitchLayout.showView(R.id.tv_page_1);
                break;
            case R.id.btn_page2:
                simpleSwitchLayout.showView(R.id.tv_page_2);
                break;
            case R.id.btn_page3:
                simpleSwitchLayout.showView(R.id.tv_page_3);
                break;
        }
    }

    public void gotoLoadingLayout(View view){
        startActivity(new Intent(this,LoadingLayoutActivity.class));
    }
}
