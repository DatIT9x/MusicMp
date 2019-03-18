package com.example.musicmp3;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    listViewAdapter adapter;
//    ArrayList<Song> arrayListSong;
    String[] title;
    String[] ten;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    ViewFlipper viewFlipper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewfipper);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
//        arrayListSong = new ArrayList<>();

            title = new String[]{"Buồn của anh", "Buồn không em", "Con trai cưng", "Chạm đáy nỗi đau", "Cuộc sống em ổn không", "Đúng người đúng thời điểm", "Đừng như thói quen", "Tâm sự với người lạ", "Tâm sự tuổi 30", "Thằng điên"};
            ten  = new String[]{"K-ICM,Đ,ạtG,Masew", "Đạt G", "B-Ray", "Erik", "Anh tú", "Thanh Hưng", "JayKii,Sara Luu", "Tiên cookie", "Trịnh Thăng Bình", "JustanTee,Phương Ly"};
            icon = new int[]{R.drawable.g, R.drawable.g8, R.drawable.g1, R.drawable.g2, R.drawable.ok, R.drawable.g4, R.drawable.g3, R.drawable.g5, R.drawable.g6, R.drawable.g7};

        listView = (ListView) findViewById(R.id.listviewbaihat);
        for (int i = 0; i < title.length; i++) {
            Model model = new Model(title[i], ten[i], icon[i]);
            arrayList.add(model);
        }


        adapter = new listViewAdapter(this, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        //enable back home
         actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.anhnen));
        actionBar.setDisplayShowHomeEnabled(true);
}
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_activity, menu);

       MenuItem searchitem = menu.findItem(R.id.menuSearch);
       SearchView searchView = (SearchView) searchitem.getActionView();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

           @Override
           public boolean onQueryTextSubmit(String s) {

               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               if (TextUtils.isEmpty(s)) {
                   adapter.filter("");
                   listView.clearTextFilter();
               } else {
                   adapter.filter(s);
               }
               return true;
           }
       });
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.Settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}















