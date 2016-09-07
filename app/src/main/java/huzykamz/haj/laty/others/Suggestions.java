package huzykamz.haj.laty.others;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;

import huzykamz.haj.laty.jeebaleapp.R;


public class Suggestions extends Activity {

    Button buttonSend;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions);

        buttonSend = (Button) findViewById(R.id.buttonSend);


}
    public void SendEmail(View view){

        SendEmail();
    }
    protected void SendEmail() {
        Log.i("Send email", "");

        String[] TO = { "kamanziabubakar75@gmail.com" };
        String[] CC = { "huxaiphaeridris@gmail.com"  };

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I do appareciate your work");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello huzykamz your app is real awesome, " +
                "but i would suggest if you can add in other modules to increase the functionalities.");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Suggestions.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT)
                    .show();
        }

    }



}