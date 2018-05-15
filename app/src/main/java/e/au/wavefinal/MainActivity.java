package e.au.wavefinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button startWaveBtn, joinWaveBtn;
    EditText usernameEt, passwordEt;
    TextView registerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt = (EditText) findViewById(R.id.usernameEditText);
        passwordEt = (EditText) findViewById(R.id.passwordEditText);
        startWaveBtn = (Button) findViewById(R.id.startWaveBtn);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        registerTv = (TextView) findViewById(R.id.registerTextView);
        joinWaveBtn = (Button) findViewById(R.id.joinWaveBtn);

        startWaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                cursor =db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME +" WHERE "
                        +DatabaseHelper.COL_5 +" =? AND " +DatabaseHelper.COL_6 +" =? ",new String[]{username, password});
                if(cursor !=null)
                {
                    if (cursor.getCount() > 0) {

                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Recommendations.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Username and/or Password Incorrect.", Toast.LENGTH_SHORT).show();
                    }
                }
            }});
        joinWaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
