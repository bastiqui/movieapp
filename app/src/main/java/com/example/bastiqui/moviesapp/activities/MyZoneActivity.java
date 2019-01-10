package com.example.bastiqui.moviesapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.fragments.RecentHistoryFragment;

import java.util.concurrent.atomic.AtomicReference;

public class MyZoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zone);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Button watchlist = findViewById(R.id.watchlist);
        watchlist.setOnClickListener(v -> {
            Intent intent = new Intent(this, WatchList.class);
            startActivity(intent);
        });

        FloatingActionButton search = findViewById(R.id.search);
        search.setOnClickListener(view -> {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        });

        FloatingActionButton goHome = findViewById(R.id.return_home);
        goHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, FragmentsMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        FloatingActionButton menu = findViewById(R.id.menu);
        AtomicReference<Boolean> check = new AtomicReference<>(false);
        menu.setOnClickListener(view -> {

            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            Animation rollback = AnimationUtils.loadAnimation(this, R.anim.rollback);

            Animation go_search = AnimationUtils.loadAnimation(this, R.anim.go_search);
            Animation return_search = AnimationUtils.loadAnimation(this, R.anim.return_search);

            Animation go_home = AnimationUtils.loadAnimation(this, R.anim.go_home);
            Animation return_home = AnimationUtils.loadAnimation(this, R.anim.return_home);

            if (check.get()) {
                check.set(false);

                rollback.setAnimationListener(new Animation.AnimationListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onAnimationStart(Animation animation) {
                        search.setVisibility(View.GONE);
                        goHome.setVisibility(View.GONE);

                        search.setClickable(false);
                        goHome.setClickable(false);

                        search.startAnimation(return_search);
                        goHome.startAnimation(return_home);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                view.startAnimation(rollback);
            } else {
                check.set(true);

                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onAnimationStart(Animation animation) {
                        search.setVisibility(View.VISIBLE);
                        goHome.setVisibility(View.VISIBLE);

                        search.setClickable(true);
                        goHome.setClickable(true);

                        search.startAnimation(go_search);
                        goHome.startAnimation(go_home);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                view.startAnimation(rotate);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fagments_m, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_myZone) {
            Intent myZone = new Intent(this, MyZoneActivity.class);
            startActivity(myZone);
        } else if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        } else if (id == R.id.action_help) {
            Intent help = new Intent(this, HelpActivity.class);
            startActivity(help);
        } else if (id == R.id.action_about) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RecentHistoryFragment();
                default:
                    return new RecentHistoryFragment();
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
