package com.fllo.appbars;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fllo.bars.AppBarrr;

public class ScrollingActivity extends AppCompatActivity {

    private AppBarrr barrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        barrr = (AppBarrr) findViewById(R.id.app_barrr);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!barrr.isExpanded()) {
                    barrr.showExpandedLayout();
                } else {
                    barrr.hideExpandedLayout();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (barrr.isExpanded()) {
            barrr.hideExpandedLayout();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
