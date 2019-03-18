package com.example.musicmp3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView txtTen, txtTimeS, txtTimeE;
    SeekBar skTime;
    ImageButton btnBack, btnPlay, btnNext, btnrepeat, btnramdom;
    ArrayList<Song> arraySong;
    int position;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView imageMp3;
    boolean repeat = false;
    boolean checkramdom = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AnhXa();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        position = bundle.getInt("pos");
        AddSong();
        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        KhoitaoMediaPlayer();
        ActionBar actionBar = getSupportActionBar();
        //enable back home
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.anhnen));
        actionBar.setDisplayShowHomeEnabled(true);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.pau);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.play);
                }
                SetTimeE();
                updateTime();
                imageMp3.startAnimation(animation);
            }
        });
        btnrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkramdom == true) {
                        checkramdom = false;
                        btnrepeat.setImageResource(R.drawable.repeat1);
                        btnramdom.setImageResource(R.drawable.ic_repeat_black_24dp);
                    }
                    btnrepeat.setImageResource(R.drawable.repeat1);
                    repeat = true;
                } else {
                    btnrepeat.setImageResource(R.drawable.ic_replay_black_24dp);
                    repeat = false;
                }
            }
        });
        btnramdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkramdom == false) {
                    if (repeat == true) {
                        repeat = false;
                        btnramdom.setImageResource(R.drawable.ramdom1);
                        btnrepeat.setImageResource(R.drawable.ic_replay_black_24dp);
                    }
                    btnramdom.setImageResource(R.drawable.ramdom1);
                    checkramdom = true;
                } else {
                    btnrepeat.setImageResource(R.drawable.ic_repeat_black_24dp);
                    checkramdom = false;
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arraySong.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (arraySong.size())) {
                        btnPlay.setImageResource(R.drawable.play);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = arraySong.size();
                            }
                            position -= 1;
                        }
                        if (checkramdom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arraySong.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (arraySong.size() - 1)) {
                            position = 0;
                        }
                    }
                    KhoitaoMediaPlayer();
                    mediaPlayer.start();
                    SetTimeE();
                    updateTime();
                    btnBack.setClickable(false);
                    btnNext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnBack.setClickable(true);
                            btnNext.setClickable(true);
                        }
                    }, 5000);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arraySong.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (arraySong.size())) {
                        btnPlay.setImageResource(R.drawable.play);
                        position--;
                        if (position < 0) {
                            position = arraySong.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkramdom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arraySong.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                    }
                    KhoitaoMediaPlayer();
                    mediaPlayer.start();
                    SetTimeE();
                    updateTime();

                    btnBack.setClickable(false);
                    btnNext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnBack.setClickable(true);
                            btnNext.setClickable(true);
                        }
                    }, 5000);
                }
            }
        });

//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position++;
//                if (position > arraySong.size() - 1) {
//                    position = 0;
//                }
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                }
//                if(repeat == true){
//                    position += 1;
//                }
//                if(checkramdom == true){
//                    Random random = new Random();
//                    int index = random.nextInt(arraySong.size());
//                    if(index == position){
//                        position = index -1;
//                    }
//                    position = index;
//                }
//                KhoitaoMediaPlayer();
//                mediaPlayer.start();
//                SetTimeE();
//                updateTime();
//
//            }
//        });
////
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position--;
//                if (position < 0) {
//                    position = arraySong.size() - 1;
//                }
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.stop();
//                }
//                KhoitaoMediaPlayer();
//                mediaPlayer.start();
//                SetTimeE();
//                updateTime();
//
//            }
//        });
//
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()) { //nếu đang phát->pause->đổi hình play
//                    mediaPlayer.pause();
//                    btnPlay.setImageResource(R.drawable.pau);
//                    KhoitaoMediaPlayer();
//                } else {
//                    mediaPlayer.start();
//                    btnPlay.setImageResource(R.drawable.play);
//
//                }
//                SetTimeE();
//                updateTime();
//                imageMp3.startAnimation(animation);
//            }
//        });
//        btnrepeat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(repeat == false){
//                    if(checkramdom == true){
//                        checkramdom = false;
//                        btnrepeat.setImageResource(R.drawable.repeat1);
//                        btnramdom.setImageResource(R.drawable.ic_repeat_black_24dp);
//                    }
//                    btnrepeat.setImageResource(R.drawable.repeat1);
//                    repeat = true;
//                }else {
//                    btnrepeat.setImageResource(R.drawable.ic_replay_black_24dp);
//                    repeat = false;
//                }
//            }
//        });
//        btnramdom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(checkramdom == false){
//                    if(repeat == true){
//                        repeat = false;
//                        btnramdom.setImageResource(R.drawable.ramdom1);
//                        btnrepeat.setImageResource(R.drawable.ic_replay_black_24dp);
//                    }
//                    btnramdom.setImageResource(R.drawable.ramdom1);
//                    checkramdom = true;
//                }else {
//                    btnramdom.setImageResource(R.drawable.ic_repeat_black_24dp);
//                    checkramdom = false;
//            }
//            }
//        });


        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skTime.getProgress());

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                txtTimeS.setText(dinhdanggio.format(mediaPlayer.getCurrentPosition()));
                skTime.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        KhoitaoMediaPlayer();
                        mediaPlayer.start();
                        SetTimeE();
                        updateTime();

                    }
                });
                handler.postDelayed(this, 500);

            }
        }, 100);
    }


    private void SetTimeE() {
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        txtTimeE.setText(dinhdanggio.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());
    }

    private void KhoitaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(Main2Activity.this, arraySong.get(position).getFile());
        txtTen.setText(arraySong.get(position).getTenBH());
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Buồn của anh", R.raw.buoncuaanh));
        arraySong.add(new Song("Buồn không em", R.raw.buonkhongem));
        arraySong.add(new Song("Con trai cưng", R.raw.contraicung));
        arraySong.add(new Song("Chạm đáy nỗi đau", R.raw.chamdaynoidau));
        arraySong.add(new Song("Cuộc sống em ổn không", R.raw.cuocsongemonkhong));
        arraySong.add(new Song("Đúng người đúng thời điểm", R.raw.dungnguoidungthoidiem));
        arraySong.add(new Song("Đừng như thói quen", R.raw.dungnhuthoiquen));
        arraySong.add(new Song("Tâm sự với người lạ", R.raw.tamsuvoinguoila));
        arraySong.add(new Song("Tâm sự tuổi 30", R.raw.tamsutuoi30));
        arraySong.add(new Song("Thằng điên", R.raw.thangdien));
    }


    private void AnhXa() {
        txtTimeS = (TextView) findViewById(R.id.textviewTimeS);
        txtTimeE = (TextView) findViewById(R.id.textviewTimeE);
        txtTen = (TextView) findViewById(R.id.textviewTen);
        skTime = (SeekBar) findViewById(R.id.SeekBarTime);
        btnBack = (ImageButton) findViewById(R.id.imagebutton);
        btnPlay = (ImageButton) findViewById(R.id.imagebutton1);
        btnNext = (ImageButton) findViewById(R.id.imagebutton2);
        imageMp3 = (ImageView) findViewById(R.id.imageMp3);
        btnrepeat = (ImageButton) findViewById(R.id.backd);
        btnramdom = (ImageButton) findViewById(R.id.ramdom);
    }
}

