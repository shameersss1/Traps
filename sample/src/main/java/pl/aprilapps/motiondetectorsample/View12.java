package pl.aprilapps.motiondetectorsample;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
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

public class View12 extends Activity {
    String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME = "id";
    private static final String TAG_ADD ="date";
    private static final String TAG_ADD1 ="time";

    String sender,phone;
    DrawerLayout mDrawerLayout;

    JSONArray peoples = null;


    ArrayList<HashMap<String, String>> personList;

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        list = (ListView) findViewById(R.id.listView);

        personList = new ArrayList<HashMap<String,String>>();
        login("0000");
    }


    protected void showList()
    {
        try
        {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++)
            {
                JSONObject c = peoples.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                String phone = c.getString(TAG_ADD);
                String j = c.getString(TAG_ADD1);

                HashMap<String,String> persons = new HashMap<String,String>();

                name = j+phone+name;

                persons.put(TAG_NAME,name);
//                persons.put(TAG_ADD,phone);
//                persons.put(TAG_ADD1,j);

                personList.add(persons);
            }
            ListAdapter adapter = new SimpleAdapter(View12.this, personList, R.layout.ll22,
                    new String[]{TAG_NAME,},
                    new int[]{R.id.msgtype1}
            );

            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                    String sel = list.getItemAtPosition(position).toString();




                    //Toast.makeText(getApplicationContext(),sel.substring(sel.length()-3,sel.length()-1),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(View12.this,NewB.class);
                    i.putExtra("msg",sel.substring(sel.length()-3,sel.length()-1));

                    i.putExtra("sender",sender);
//
                    //Toast.makeText(getApplicationContext(),sel.substring(sel.length()-3,sel.length()-1),Toast.LENGTH_LONG).show();
                    startActivity(i);

                    //Toast.makeText(getApplicationContext(), temp[1].substring(2,temp[1].length()-1), Toast.LENGTH_LONG).show();

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void login(final String phone) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(View12.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];



                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("phone", uname));


                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://islamquiz.in/Android/User/viewimage.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){

                loadingDialog.dismiss();
                String s=result.trim();


                myJSON=s;
                showList();

            }
        }
        LoginAsync g = new LoginAsync();
        g.execute(phone);
    }
}