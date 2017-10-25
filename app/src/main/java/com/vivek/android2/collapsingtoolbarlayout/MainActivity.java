package com.vivek.android2.collapsingtoolbarlayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsing_tool_bar;
    CoordinatorLayout coordinatorLayout;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Display dWidth = getWindowManager().getDefaultDisplay();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinate_layout);

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = dWidth.getWidth() * 1 / 3;
                setAppBarOffset(heightPx);
            }
        });

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar);
        ImageView toolbarImage = (ImageView) findViewById(R.id.image_view);

        toolbarImage.getLayoutParams().height = dWidth.getWidth();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shree_krishna);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(getResources().getColor(R.color.colorAccent));
                collapsingToolbarLayout.setContentScrimColor(mutedColor);
                collapsingToolbarLayout.setStatusBarScrimColor(mutedColor);
            }
        });
    }

    private void setAppBarOffset(int offsetPx) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll(coordinatorLayout, appBarLayout, null, 0, offsetPx, new int[]{0, 0});
    }
}
