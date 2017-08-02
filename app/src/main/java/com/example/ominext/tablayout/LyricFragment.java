package com.example.ominext.tablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class LyricFragment extends Fragment {
    SQLiteHelper sqLiteHelper;
    List<Data> list = new ArrayList<>();
    @BindView(R.id.lyric)
    TextView lyric;
    Unbinder unbinder;
    int position=0;

    public LyricFragment() {
    }

    public static LyricFragment newInstance() {
        LyricFragment fragment = new LyricFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lyric, container, false);
        unbinder = ButterKnife.bind(this, view);
        sqLiteHelper = new SQLiteHelper(view.getContext());
        list = sqLiteHelper.getAllSong();
        setText(position);
        return view;
    }
    public void setText(int i) {
        lyric.setText(list.get(i).getLyric());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
