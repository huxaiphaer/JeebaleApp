package hollaiuiu.news.chatapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import huzykamz.haj.laty.jeebaleapp.R;


public class Main extends AppCompatActivity {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    ProgressBar p;
    private List<WorldPopulation> worldpopulationlist = null;
    private Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



// Get the view from listview_main.xml
        setContentView(R.layout.listview_main);

        //toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
// Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }
    /*
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
        }
    }
    */
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = (ProgressBar) findViewById(R.id.progressBar);
           p.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
// Create the array
            worldpopulationlist = new ArrayList<WorldPopulation>();
            try {
// Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "NewsAlerts");
// Locate the column named "ranknum" in Parse.com and order list
// by ascending
                query.orderByAscending("Rank");
                ob = query.find();
                for (ParseObject country : ob) {
// Locate images in flag column
                    ParseFile image = (ParseFile) country.get("Images");

                    WorldPopulation map = new WorldPopulation();
                    map.setHeadlines((String) country.get("HeadLines"));
                    map.setDetails((String) country.get("Details"));

                    map.setDate((String) country.get("date"));

                    map.setFlag(image.getUrl());
                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
// Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
// Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(Main.this,
                    worldpopulationlist);
// Binds the Adapter to the ListView
            listview.setAdapter(adapter);
// Close the progressdialog
          p.setVisibility(View.INVISIBLE);
        }
    }
}