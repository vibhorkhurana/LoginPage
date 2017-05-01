package cdac.in.loginpage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vibhor on 1/5/17.
 */

public class SecondActivity extends Activity {
    SharedPreferences prefs;
    TextView tvText;
    Button btLogOut;
    private static String USR_NAME = "usr_name";
    private static String PSWD = "pswd";
    private static String STNGS_SAVED = "stngs_saved";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String USR_NAME = "usr_name";
        //String PSWD = "pswd";
        //String STNGS_SAVED = "stngs_saved";
        setContentView(R.layout.activity_welcome);
        prefs = getSharedPreferences("UserPref", MODE_PRIVATE);
        tvText = (TextView) findViewById(R.id.textView);
        btLogOut = (Button) findViewById(R.id.btLogout);
        tvText.setText("Hello "+prefs.getString(USR_NAME," "));
        tvText.setTextSize(26);
        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                resetPreference();
                startActivity(intent);
                SecondActivity.this.finish();

            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Dialog dt1;
            dt1 = new Dialog(SecondActivity.this);
            dt1.setContentView(R.layout.dialog_layout);
            dt1.show();
            Button btExit = (Button) dt1.findViewById(R.id.btExit);
            Button btLogOutExit = (Button) dt1.findViewById(R.id.btLgExit);
            btExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SecondActivity.this,"Exiting",Toast.LENGTH_SHORT).show();
                    SecondActivity.this.finish();

                }
            });

            btLogOutExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SecondActivity.this.finish();
                    Toast.makeText(SecondActivity.this,"LogOut and Exit",Toast.LENGTH_SHORT).show();
                    resetPreference();
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }

    private void resetPreference() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PSWD, " ");
        editor.putString(STNGS_SAVED, "false");
        editor.commit();
    }
}
