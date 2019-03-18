package com.example.musicmp3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper sqLiteOpenHelper;
    Button btnlogin, btnsigup;
    EditText ed1, ed2;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sqLiteOpenHelper = new DatabaseHelper(this);
        db = sqLiteOpenHelper.getReadableDatabase();
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnsigup = (Button) findViewById(R.id.btnsign);
        ed1 = (EditText) findViewById(R.id.edte1);
        ed2 = (EditText) findViewById(R.id.edte2);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed1.getText().toString();
                String pass = ed2.getText().toString();
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ô trống", Toast.LENGTH_SHORT).show();
                }
                    cursor = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COLUMN_1 + " =? AND " + DatabaseHelper.COLUMN_2 + " =? ", new String[]{email,pass});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(Main3Activity.this, MainActivity.class);
//                            startActivity(intent);
                        }
                    }
                }
        });
        btnsigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, SignUp.class);
                startActivity(intent);
            }
        });
//                btnlogin.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                });
    }
}


