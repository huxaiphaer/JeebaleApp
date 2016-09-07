package hollaiuiu.news.chatapp;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import huzykamz.haj.laty.jeebaleapp.R;


public class SingleItemView extends AppCompatActivity {
    // Declare Variables

    String headlines;
    String details;
    String flag;
    String date;
    private Toolbar mToolbar;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("News Details");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i = getIntent();
// Get the result of rank
// Get the result of country
        headlines = i.getStringExtra("Headlines");
// Get the result of population
        details = i.getStringExtra("Details");
// Get the result of flag
        flag = i.getStringExtra("flag");
        // insert the date
        date = i.getStringExtra("createdAt");

// Locate the TextViews in singleitemview.xml

        TextView txtcountry = (TextView) findViewById(R.id.headlines);
        TextView txtpopulation = (TextView) findViewById(R.id.details);

// Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.flag);

// Set results to the TextViews

        txtcountry.setText(headlines);
        txtpopulation.setText(details);


// Capture position and set results to the ImageView
// Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(flag, imgflag);
    }
}