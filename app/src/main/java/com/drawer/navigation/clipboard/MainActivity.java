package com.drawer.navigation.clipboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.drawer.navigation.clipboard.db.AssetRepository;
import com.drawer.navigation.clipboard.db.AssetTableRecord;
import com.drawer.navigation.clipboard.util.ClipboardAdapter;
import com.drawer.navigation.clipboard.util.Logger;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG="MainActivity";

    AssetRepository assetRepository;
    List<AssetTableRecord> listAssets;

    ListView lvData;

    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvData=(ListView)findViewById(R.id.lvData);
        txtMsg=(TextView)findViewById(R.id.txt_message);

        //getSupportActionBar().hide();//Ocultar ActivityBar anterior


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /***** For start Service  ****/
        Intent myIntent = new Intent(this, ClipboardService.class);
        this.startService(myIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();


        loadSavedData();
    }

    private  void loadSavedData(){
        assetRepository = new AssetRepository(this);
        listAssets = assetRepository.getRecordsForSync();
        if(null!=listAssets && listAssets.size()>0){
            txtMsg.setVisibility(View.GONE);
            lvData.setVisibility(View.VISIBLE);
            for (AssetTableRecord data:listAssets
                 ) {

                Logger.showFilteredLog(TAG,"data::" + data.getTextData());

            }

            lvData.setAdapter(new ClipboardAdapter(this, listAssets));
        }else{
            txtMsg.setVisibility(View.VISIBLE);
            lvData.setVisibility(View.GONE);
            txtMsg.setText("No records available.");
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

            if(null!=assetRepository){
                if(assetRepository.deleteAll()){
//                    loadSavedData();
                    listAssets.clear();
                    lvData.invalidateViews();
                    loadSavedData();
                }
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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





}
