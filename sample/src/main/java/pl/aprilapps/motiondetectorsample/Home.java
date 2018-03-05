package pl.aprilapps.motiondetectorsample;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends ActionBarActivity {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



Button b1 = (Button) findViewById(R.id.button2);
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(Home.this,Settings.class);
                 startActivity(i);
             }
         });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;



        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if(menuItem.getItemId() == R.id.dm)
                {
                    Intent i = new Intent(Home.this,MotionDetectionActivity.class);
                    startActivity(i);
                }

                if (menuItem.getItemId() == R.id.up) {
                    Intent i =new Intent(Home.this,Settings.class);
                    startActivity(i);

                }

                if (menuItem.getItemId() == R.id.afm) {
                    Intent i =new Intent(Home.this,ImageBrowser.class);
                    startActivity(i);

                }

                if (menuItem.getItemId() == R.id.si) {
                    Intent i =new Intent(Home.this,View12.class);
                    startActivity(i);

                }

                return false;
            }

        });


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


    }


}