package com.example.aircleaner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressBar progBar;
    private TextView text;
    private ProgressBar progBar2;
    private TextView text2;
    private Button button1, button2, button3;
    private Switch sw;

    private TextView tvStatus;
    private TextView tvPercent;
    private ProgressBar pbPercent;

    private Handler mHandler = new Handler();

    private int mProgressStatus = 30;
    private int mProgressStatus2 = 151;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        progBar = (ProgressBar) findViewById(R.id.progressBar);
        text = (TextView) findViewById(R.id.tv_percent);

        progBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        text2 = (TextView) findViewById(R.id.tv_percent2);

        tvStatus = (TextView) findViewById(R.id.on_off2);
        tvPercent = (TextView) findViewById(R.id.tv_percent4);
        pbPercent = (ProgressBar) findViewById(R.id.progressBar3);

        sw = (Switch) findViewById(R.id.switch2);

        Resources res = getResources();
        Rect bounds = progBar.getProgressDrawable().getBounds();
        Rect bounds2 = progBar2.getProgressDrawable().getBounds();

        if (0 < mProgressStatus && mProgressStatus <= 30) {
            progBar.setProgress(mProgressStatus/2);                 //data의 반이 그래프의 색상으로 찍힘
            text.setText("좋음");
            progBar.setProgressDrawable(res.getDrawable(R.drawable.prog_color_blue));
        } else if (30 < mProgressStatus && mProgressStatus <= 80) {
            progBar.setProgress(mProgressStatus/2);
            text.setText("보통");
            progBar.setProgressDrawable(res.getDrawable(R.drawable.prog_color_green));
        } else if (80 < mProgressStatus && mProgressStatus <= 150) {
            progBar.setProgress(mProgressStatus/2);
            text.setText("나쁨");
            progBar.setProgressDrawable(res.getDrawable(R.drawable.prog_color_yellow));
        } else if (151 <= mProgressStatus) {
            progBar.setProgress(mProgressStatus/2);
            text.setText("매우높음");
            progBar.setProgressDrawable(res.getDrawable(R.drawable.prog_color_red));
        }


        if (0 < mProgressStatus2 && mProgressStatus2 <= 30) {
            progBar2.setProgress(mProgressStatus2/2);
            text2.setText("좋음");
            progBar2.setProgressDrawable(res.getDrawable(R.drawable.prog_color_blue));
        } else if (30 < mProgressStatus2 && mProgressStatus2 <= 80) {
            progBar2.setProgress(mProgressStatus2/2);
            text2.setText("보통");
            progBar2.setProgressDrawable(res.getDrawable(R.drawable.prog_color_green));
        } else if (80 < mProgressStatus2 && mProgressStatus2 <= 150) {
            progBar2.setProgress(mProgressStatus2/2);
            text2.setText("나쁨");
            progBar2.setProgressDrawable(res.getDrawable(R.drawable.prog_color_yellow));
        } else if (151 <= mProgressStatus2) {
            progBar2.setProgress(mProgressStatus2/2);
            text2.setText("매우높음");
            progBar2.setProgressDrawable(res.getDrawable(R.drawable.prog_color_red));
        }


        progBar.getProgressDrawable().setBounds(bounds);
        progBar2.getProgressDrawable().setBounds(bounds2);

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

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                savePreferences(MainActivity.this, "main_switch", isChecked);
                if (isChecked) {
                    tvStatus.setText("공기청정기가 ON 되었습니다.");
                    tvPercent.setText("동작중");
                    pbPercent.setVisibility(View.VISIBLE);
                } else {
                    tvStatus.setText("공기청정기가 OFF 되었습니다.");
                    tvPercent.setText("정지중");
                    pbPercent.setVisibility(View.INVISIBLE);
                }
                //Toast.makeText(MainActivity.this, "동작중 = " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sw.setChecked(getPreferences(this, "main_switch"));

        if (sw.isChecked()) {
            tvStatus.setText("공기청정기가 ON 되었습니다.");
            tvPercent.setText("동작중");
            pbPercent.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setText("공기청정기가 OFF 되었습니다.");
            tvPercent.setText("정지중");
            pbPercent.setVisibility(View.INVISIBLE);
        }
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1:
                    Intent intent = new Intent(MainActivity.this, PlayListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button2:
                    Intent intent2 = new Intent(MainActivity.this, SampleActivity2.class);
                    startActivity(intent2);
                    break;
                case R.id.button3:
                    Intent intent3 = new Intent(MainActivity.this, SpeechToTextActivity.class);
                    startActivity(intent3);
                    break;
            }
        }
    };


    // 값 불러오기
    private Boolean getPreferences(Context ctx, String key) {
        SharedPreferences pref = ctx.getSharedPreferences("main", MODE_PRIVATE);
        Boolean status = pref.getBoolean(key, false);

        return status;
    }

    // 값 저장하기
    private void savePreferences(Context ctx, String key, Boolean status) {
        SharedPreferences pref = ctx.getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, status);
        editor.commit();
    }

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
            Intent intent = new Intent(MainActivity.this, on_off.class);
            startActivity(intent);
            return true;
        }
//        else if (id == R.id.action_alarm) {
//            Intent intent = new Intent(MainActivity.this, alarm.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.graph) {
            Intent intent = new Intent(MainActivity.this, BarChartActivityMultiDataset.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_add) {
            Intent intent = new Intent(MainActivity.this, add.class);
            startActivity(intent);
            return true;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
