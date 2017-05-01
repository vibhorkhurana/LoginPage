package cdac.in.loginpage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cdac.in.loginpage.R;

public class MainActivity extends Activity {
    Button btLogin,btReset;
    EditText edName,edPwd;
    private static String USR_NAME = "usr_name";
    private static String PSWD = "pswd";
    private static String STNGS_SAVED = "stngs_saved";

    final String saveStatus = "true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences prefs = getSharedPreferences("UserPref", MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        btLogin = (Button) findViewById(R.id.button);
        btReset = (Button) findViewById(R.id.button2);
        edName = (EditText) findViewById(R.id.edUsrNm);
        edPwd = (EditText) findViewById(R.id.edUsrPwd);
        if (!prefs.getString(USR_NAME," ").contentEquals(" "))
        {
            edName.setText(prefs.getString(USR_NAME," "));
        }
        //Toast.makeText(MainActivity.this,prefs.getString(STNGS_SAVED," "),Toast.LENGTH_SHORT).show();
        if (prefs.getString(STNGS_SAVED, " ").equals(saveStatus)) {
            Intent it1 = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(it1);
            MainActivity.this.finish();
        }
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(USR_NAME, edName.getText().toString());
                editor.putString(PSWD, edPwd.getText().toString());
                editor.putString(STNGS_SAVED, "true");
                editor.commit();
                Toast.makeText(MainActivity.this, "Contents Saved", Toast.LENGTH_SHORT).show();
                Intent it1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(it1);
                MainActivity.this.finish();
            }

        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edName.setText("");
                edName.setHint("User Name");
                edPwd.setText("");
                edPwd.setHint("Password");
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(USR_NAME, " ");
                editor.putString(PSWD, " ");
                editor.putString(STNGS_SAVED, " ");
                editor.commit();
                Toast.makeText(MainActivity.this, "Credentials Reset", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

