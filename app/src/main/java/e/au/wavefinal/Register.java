package e.au.wavefinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import e.au.wavefinal.DatabaseHelper;
import e.au.wavefinal.MainActivity;
import e.au.wavefinal.R;

public class Register extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button startWaveBtn, signInBtn;
    EditText firstNameEt, lastNameEt, emailEt, usernameEt, passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(this);
        firstNameEt = (EditText) findViewById(R.id.firstNameEt);
        lastNameEt = (EditText) findViewById(R.id.lastNameEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        usernameEt = (EditText) findViewById(R.id.usernameEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        signInBtn = (Button) findViewById(R.id.signBtn);
        startWaveBtn = (Button) findViewById(R.id.startWaveBtn);

        startWaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String firstName=firstNameEt.getText().toString();
                String lastName=lastNameEt.getText().toString();
                String email=emailEt.getText().toString();
                String username=usernameEt.getText().toString();
                String password=passwordEt.getText().toString();
                insertdata(firstName,lastName,email,username, password);
                Toast.makeText(getApplicationContext(), "Successfully Registered!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void insertdata(String firstName, String lastName, String email, String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, firstName);
        contentValues.put(DatabaseHelper.COL_3, lastName);
        contentValues.put(DatabaseHelper.COL_4, email);
        contentValues.put(DatabaseHelper.COL_5, username);
        contentValues.put(DatabaseHelper.COL_6, password);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}

