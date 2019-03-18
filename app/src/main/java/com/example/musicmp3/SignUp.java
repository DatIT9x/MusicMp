package com.example.musicmp3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
//    SQLiteOpenHelper sqLiteOpenHelper;
//    SQLiteDatabase db;
    Button btnsign1, btnlogin1;
    EditText edtemail, edtpass, edtcpass;
    DatabaseHelper dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dt = new DatabaseHelper(this);
//        sqLiteOpenHelper = new DatabaseHelper(this);
        btnsign1 = (Button) findViewById(R.id.btnsign1);
        btnlogin1 = (Button) findViewById(R.id.btnlogin1);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpass = (EditText) findViewById(R.id.edtpass);
        edtcpass = (EditText) findViewById(R.id.edtcpass);


        btnsign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db = sqLiteOpenHelper.getWritableDatabase();
                String s1 = edtemail.getText().toString();
                String s2 = edtpass.getText().toString();
                String s3 = edtcpass.getText().toString();

//                insertdata(email, pass, cpass);
//                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                if (s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getApplicationContext(), "Ô trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)) {
                        Boolean checkemail = dt.checkemail(s1);
                        if (checkemail == true) {
                            boolean insert = dt.insert(s1, s2);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
}

//    public void insertdata(String email,String pass,String cpass){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.COLUMN_1, email);
//        contentValues.put(DatabaseHelper.COLUMN_2, pass);
//        contentValues.put(DatabaseHelper.COLUMN_3, cpass);
//        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
//    }
//}





