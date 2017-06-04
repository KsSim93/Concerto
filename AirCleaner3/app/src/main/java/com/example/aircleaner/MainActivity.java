package com.example.aircleaner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressBar progBar;
    private TextView text;
    private ProgressBar progBar2;
    private TextView text2;
    private Button button1, button2, button3;

    private Handler mHandler = new Handler();
    private int mProgressStatus=30;
    private int mProgressStatus2=60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        progBar= (ProgressBar)findViewById(R.id.progressBar);
        text = (TextView)findViewById(R.id.tv_percent);

        progBar2= (ProgressBar)findViewById(R.id.progressBar2);
        text2 = (TextView)findViewById(R.id.tv_percent2);

        progBar.setProgress(mProgressStatus);
        text.setText(""+mProgressStatus+"%");

        progBar2.setProgress(mProgressStatus2);
        text2.setText(""+mProgressStatus2+"%");

        button1.setOnClickListener(mClickListener);
        button2.setOnClickListener(mClickListener);
        button3.setOnClickListener(mClickListener);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Switch sw =(Switch)findViewById(R.id.switch2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(MainActivity.this,"체크상태 = "+isChecked,Toast.LENGTH_SHORT).show();
            }
        });



    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1:
                    Intent intent=new Intent(MainActivity.this,ch_bgm.class);
                    startActivity(intent);
                    break;
                case R.id.button2:
                    Intent intent2=new Intent(MainActivity.this,ch_rgb.class);
                    startActivity(intent2);
                    break;
                case R.id.button3:
                    Intent intent3=new Intent(MainActivity.this,ch_voice.class);
                    startActivity(intent3);
                    break;
            }
        }
    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this,on_off.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_alarm){
            Intent intent=new Intent(MainActivity.this,alarm.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.graph) {
            Intent intent=new Intent(MainActivity.this,GraphView.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_add) {
            Intent intent = new Intent(MainActivity.this,add.class);
            startActivity(intent);
            return true;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
