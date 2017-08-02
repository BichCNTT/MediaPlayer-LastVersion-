package com.example.ominext.tablayout;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tvCurrentTime)
    TextView tvCurrentTime;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tvTotalTime)
    TextView tvTotalTime;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.button_random)
    Button buttonRandom;
    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.button_play)
    Button buttonPlay;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.button_replay)
    Button buttonReplay;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Context context;
    LyricFragment fragment;
    //Ở MainActivity thì phải xử lý sự kiện
    //trong các

    SQLiteHelper mySQLiteHelper = new SQLiteHelper(this);
    Handler handler;
    MediaPlayer mediaPlayer = new MediaPlayer();

    private int totalTime = 0;
    boolean audioAvailable = false;
    private int counter = 0;
    int check = 0;
    boolean play = true;//biến ktra xem có cho phép bật hay không
    int i = 0;
    List<Data> listSong = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertData();

        ButterKnife.bind(this);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        handler = new Handler();

        List<Data> list = mySQLiteHelper.getAllSong();
        listSong.addAll(list);
    }

    public void insertData() {
        //chèn dữ liệu vào 1 danh sách list, chèn dữ liệu vào trong database.
        insert("Safe and sound", "http://zmp3-mp3-s1-te-vnso-qt-4.zadn.vn/5dc70f19af5d46031f4c/1011618229764532511?key=ACYQE8o_zzjJC25OSjGjrA&expires=1501744750", "4:01",
                "Safe and sound\n" +
                        "I remember tears streaming down your face \n" +
                        "When i said, i'll never let you go \n" +
                        "When all those shadows almost killed ypur light \n" +
                        "I remember you said, don't leave me here alone \n" +
                        "But all that's dead and gone and passed tonight \n" +
                        "Just close your eyes \n" +
                        "The sun is going down \n" +
                        "You'll be alright \n" +
                        "No one can hurt you now \n" +
                        "Come morning light \n" +
                        "You and I'll be safe and sound \n" +
                        "Don't you dare look out your window darling \n" +
                        "Every thing's on fire \n" +
                        "The war outside our door keeps raging on \n" +
                        "Hold onto lullaby \n" +
                        "Even when the music's gone \n" +
                        "Just close your eyes \n" +
                        "The sun is going down \n" +
                        "You'll be alright \n" +
                        "No one can hurt you now \n" +
                        "Come morning light \n" +
                        "You and I'll be safe and sound\n");

        insert("I Knew You Were Trouble", "http://zmp3-mp3-s1-te-vnso-qt-4.zadn.vn/a3150892a8d6418818c7/6976385502297965237?key=OcRlIuVwaCfpCmyBgh9TuQ&expires=1501745181", "3:39", "I Knew You Were Trouble\n" +
                "Once upon a time, a few mistakes ago \n" +
                "I was in your sights, you got me alone \n" +
                "You found me, you found me, you found me \n" +
                "\n" +
                "I guess you didn’t care, and I guess I liked that \n" +
                "And when I fell hard, you took a step back \n" +
                "Without me, without me, without me \n" +
                "\n" +
                "And he’s long gone when he’s next to me \n" +
                "And I realise the blame is on me \n" +
                "\n" +
                "Cause I knew you were trouble when you walked in \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Til you put me down \n" +
                "\n" +
                "Oh, I knew you were trouble when you walked \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Now I’m lying on the cold hard ground \n" +
                "\n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "\n" +
                "No apologies, he’ll never see you cry \n" +
                "Pretend he doesn’t know that you’re the reason why \n" +
                "You’re drowning, you’re drowning, you’re drowning \n" +
                "\n" +
                "I heard you moved on from whispers in the street \n" +
                "A new notch in your belt is all I’ll ever be \n" +
                "And now I see, now I see, now I see \n" +
                "\n" +
                "He was long gone when we met me \n" +
                "And I realise the joke is on me \n" +
                "\n" +
                "Cause I knew you were trouble when you walked in \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Til you put me down \n" +
                "\n" +
                "Oh, I knew you were trouble when you walked \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Now I’m lying on the cold hard ground \n" +
                "\n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "\n" +
                "And the saddest fear comes creeping in \n" +
                "That you never loved me, or her, or anyone, or anything \n" +
                "YEAH! \n" +
                "\n" +
                "I knew you were trouble when you walked in \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Til you put me down \n" +
                "\n" +
                "Oh, I knew you were trouble when you walked \n" +
                "Shame on me now, flew me to places I’d never been \n" +
                "Now I’m lying on the cold hard ground \n" +
                "\n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "Oh, oh. Trouble, trouble, trouble. \n" +
                "I knew you were trouble when you walked in.");

        insert("Roar", "http://zmp3-mp3-s1-te-zmp3-fpthn-2.zadn.vn/4f4276c8798c90d2c99d/1975457849898826896?key=lan3pBr1Eg0AKytnJWGfdg&expires=1501743473", "3:42", "Roar\n" +
                "I used to bite my tongue and hold my breath \n" +
                "Scared to rock the boat and make a mess \n" +
                "So I sat quietly, agreed politely \n" +
                "I guess that I forgot I had a choice \n" +
                "I let you push me past the breaking point \n" +
                "I stood for nothing, so I fell for everything \n" +
                "\n" +
                "You held me down, but I got up \n" +
                "Already brushing off the dust \n" +
                "You hear my voice, your hear that sound \n" +
                "Like thunder, gonna shake your ground \n" +
                "You held me down, but I got up \n" +
                "Get ready cause I’ve had enough \n" +
                "I see it all, I see it now \n" +
                "\n" +
                "[Chorus] \n" +
                "I got the eye of the tiger, the fire, dancing through the fire \n" +
                "Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Louder, louder than a lion \n" +
                "Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Oh oh oh oh oh oh \n" +
                "You’re gonna hear me roar \n" +
                "\n" +
                "Now I’m floating like a butterfly \n" +
                "Stinging like a bee I earned my stripes \n" +
                "I went from zero, to my own hero \n" +
                "\n" +
                "You held me down, but I got up \n" +
                "Already brushing off the dust \n" +
                "You hear my voice, your hear that sound \n" +
                "Like thunder, gonna shake your ground \n" +
                "You held me down, but I got up \n" +
                "Get ready ’cause I’ve had enough \n" +
                "I see it all, I see it now \n" +
                "\n" +
                "[Chorus] \n" +
                "I got the eye of the tiger, the fire, dancing through the fire \n" +
                "‘Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Louder, louder than a lion \n" +
                "‘Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Oh oh oh oh oh oh \n" +
                "You’re gonna hear me roar \n" +
                "\n" +
                "Roar-or, roar-or, roar-or \n" +
                "\n" +
                "I got the eye of the tiger, the fire, dancing through the fire \n" +
                "‘Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Louder, louder than a lion \n" +
                "‘Cause I am a champion and you’re gonna hear me ROAR \n" +
                "Oh oh oh oh oh oh \n" +
                "You’re gonna hear me roar");
        insert("One more night", "http://zmp3-mp3-s1-te-zmp3-fpthn-1.zadn.vn/b4ffa892a6d64f8816c7/1528857146238391076?key=L1VLtu6OIDSsRVWL1gVttA&expires=1501743829", "3:39", "One more night\n" +
                "Hiện chưa có lời bài hát");
        insert("Ghen", "http://zmp3-mp3-s1-te-zmp3-fpthn-2.zadn.vn/3c8c0971d135386b6124/9159096744558092578?key=z0Je5A6ivXWmstE2ltmd_A&expires=1501743946", "4:07", "Ghen\n" +
                "Verse 1: \n" +
                "2 giờ sáng, anh gọi em không nhấc máy \n" +
                "Không một tin nhắn từ tối qua \n" +
                "Không hề biết em ngủ chưa hay vẫn thức \n" +
                "Hay đang ở một nơi rất xa \n" +
                "\n" +
                "Pre-chorus 1: \n" +
                "(Ooh ooh ooh) Có lẽ em đang vui bên người khác, trong một vòng tay ấm áp \n" +
                "(Ooh ooh ooh) Có lẽ anh đang say trong ảo giác, từng hờn ghen như bùng cháy lên \n" +
                "\n" +
                "Chorus: \n" +
                "Bởi vì anh ghen ghen ghen ghen mà \n" +
                "Vì anh đang yêu em thôi thôi thôi mà \n" +
                "Là anh đang ghen ghen ghen ghen mà \n" +
                "Bởi vì anh đã quá yêu em, quá yêu em (x2) \n" +
                "\n" +
                "Verse 2: \n" +
                "3 giờ sáng, căn phòng khuya đang ấm áp \n" +
                "Bỗng dòng tin nhắn chơt lướt qua \n" +
                "“Em ở đâu? Đã ngủ chưa hay vẫn thức?” \n" +
                "“Hay bây giờ đây anh đến nhà?” \n" +
                "\n" +
                "Pre-chorus 2: \n" +
                "(Ooh ooh ooh) Có lẽ anh lo âu không cần thiết, chắc điều đấy anh cũng biết \n" +
                "(Ooh ooh ooh) Có lẽ anh đang say trong ảo giác, từng hờn ghen như bùng cháy lên \n" +
                "\n" +
                "Bridge: \n" +
                "Vì anh quá yêu em, chỉ muốn đến bên em và giữ em cho riêng mình anh \n" +
                "Làm sao để cho em hiểu được trái tim anh, cứ phát điên vì em mà thôi \n" +
                "Và em cũng yêu anh, chỉ muốn đến bên anh để xóa tan bao nhiêu buồn lò \n" +
                "‘Cause I just wanna make you my babe!");
        insert("Sau tất cả", "http://zmp3-mp3-s1-te-zmp3-fpthn-2.zadn.vn/8d481535b4715d2f0460/105509056746938253?key=pU7K07DtIMdEd1DEMe7yLQ&expires=1501745826", "3:54", "Sau tất cả\n" +
                "Hiện chưa có lời bài hát");
        insert("Thất tình", "http://zmp3-mp3-s1-te-zmp3-fpthn-2.zadn.vn/8832bfbbb6ff5fa106ee/9192542311448291847?key=yVbvXLKpukFQfLgVLvBIuw&expires=1501745845", "6:34", "Thất tình\n" +
                "Hiện chưa có lời bài hát");
    }

    public void insert(String name, String url, String time, String lyric) {
        Data data = new Data();
        data.setName(name);
        data.setTime(time);
        data.setUrl(url);
        data.setLyric(lyric);
        mySQLiteHelper.insert(data);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "ONE");
        fragment = new LyricFragment();
        adapter.addFragment(fragment, "TWO");
        viewPager.setAdapter(adapter);
    }

    //Muốn bật được nhạc thì phải lấy được list nhạc
    @OnClick({R.id.button_random, R.id.button_back, R.id.button_play, R.id.button_next, R.id.button_replay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_random:
                i = rand(0, listSong.size() - 1);
                tvTotalTime.setText("00:00");
                tvCurrentTime.setText("00:00");
//                    buttonPlay.setVisibility(View.INVISIBLE);
                title.setText(listSong.get(i).getName());
                buttonPlay.setEnabled(false);
                initMedia(i);
                break;
            case R.id.button_back:
                if (i >= 1) {
                    i--;
                    tvTotalTime.setText("00:00");
                    tvCurrentTime.setText("00:00");
                    title.setText(listSong.get(i).getName());
                    buttonPlay.setEnabled(false);
                    initMedia(i);
                }
                break;
            case R.id.button_play:
                if (play == true) {
                    if (audioAvailable) {
                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.start();
                            //chạy current time
                            countTimer();
                            seekBar.setOnSeekBarChangeListener(seekBarChange);
                            check = 1;//set flag Đã từng ấn nút play
                            buttonPlay.setBackground(getResources().getDrawable(R.drawable.pause));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Vui lòng chờ trong ít phút", Toast.LENGTH_LONG).show();
                    }
                    play = false;//không cho phép bật
                } else {//ngược lại nếu không cho phép bật thì dừng+kích hoạt chế độ cho phép bật
                    mediaPlayer.pause();
                    buttonPlay.setBackground(getResources().getDrawable(R.drawable.play));
                    play = true;
                }
                break;
            case R.id.button_next:
                if (i < listSong.size() - 1) {
                    i++;
                    tvTotalTime.setText("00:00");
                    tvCurrentTime.setText("00:00");
                    title.setText(listSong.get(i).getName());
                    buttonPlay.setEnabled(false);
                    initMedia(i);
                }
                break;
            case R.id.button_replay:
                if (check == 1) {
                    mediaPlayer.setLooping(true);
                    Toast.makeText(getApplicationContext(), "Đã kích hoạt chế độ lặp lại", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void initMedia(int position) {//sẽ gọi lại khi lấy được đường link
        //tạo mới 1 media với đường dẫn
        i = position;
        if (check == 1) {//nếu đã từng bật
            mediaPlayer.pause();
            buttonPlay.setBackground(getResources().getDrawable(R.drawable.play));
            play = true;
        }
        mediaPlayer = new MediaPlayer();
        if (i == 0) {
            try {
                mediaPlayer.setDataSource("http://waptrick.one/_common/loadfile.jsp?type=T&id=60977&address=/truetones/mp3_max/Good_Morning_01.mp3");
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnBufferingUpdateListener(onBufferingLoading);//audio load vào bộ đệm và đang load đến phần nào
                mediaPlayer.setOnPreparedListener(onPrepareAudio);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Không load được dữ liệu 1", Toast.LENGTH_LONG).show();
            }
            //Get song link

        } else {
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(listSong.get(i).getUrl());
                //Get song link
                mediaPlayer.setOnBufferingUpdateListener(onBufferingLoading);//audio load vào bộ đệm và đang load đến phần nào
                mediaPlayer.setOnPreparedListener(onPrepareAudio);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Không load được dữ liệu 2", Toast.LENGTH_LONG).show();
                Log.e("ex", e.getMessage());
            }
        }

    }

    private SeekBar.OnSeekBarChangeListener seekBarChange = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    };
    private MediaPlayer.OnBufferingUpdateListener onBufferingLoading = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            //i: là phần trăm audio đã load được khác với phần đang play
            seekBar.setSecondaryProgress(i);
        }
    };
    private MediaPlayer.OnPreparedListener onPrepareAudio = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            //audio sẵn sàng play
            //lấy đc total+time
            totalTime = mediaPlayer.getDuration();//time tính theo milliseconds
            int minute = totalTime / 1000 / 60;
            int second = totalTime / 1000 % 60;
            tvTotalTime.setText(minute + ":" + second);
            audioAvailable = true;//flag để đánh dấu mediaPlayer sẵn sàng
            fragment.setText(i);//gọi lại hàm setText để nó truyền lại lời bài hát
            buttonPlay.setEnabled(true);
        }
    };

    private void countTimer() {
        handler.postDelayed(timerCounter, 1000);//sau 1000 millisecond sẽ thay đổi giá trị 1 lần; timerCounter thuộc kiểu runnable
    }

    private Runnable timerCounter = new Runnable() {
        @Override
        public void run() {
            //lấy thời gian hiện tại
            counter = mediaPlayer.getCurrentPosition();
            int minute = (int) counter / 1000 / 60;
            int second = (int) counter / 1000 % 60;
            //định dạng
//               java.text.SimpleDateFormat simpleDateFormat=new java.text.SimpleDateFormat("mm:ss");
            tvCurrentTime.setText(minute + ":" + second);
            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            //Dùng đệ quy để lặp lại
            countTimer();
        }
    };

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }
}
