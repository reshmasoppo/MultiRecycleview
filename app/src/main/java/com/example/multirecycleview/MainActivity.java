package com.example.multirecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import static androidx.recyclerview.widget.OrientationHelper.VERTICAL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Modal> list = new ArrayList<>();
        list.add(new Modal(Modal.TEXT_TYPE, "Hello. This is the Text-only View Type. Nice to meet you", 0));

        list.add(new Modal(Modal.IMAGE_TYPE, "Hi. I display a cool image too besides the omnipresent TextView.", R.drawable.wtc));
        list.add(new Modal(Modal.AUDIO_TYPE, "Hey. Pressing the FAB button will playback an audio file on loop.", R.raw.sound));
        list.add(new Modal(Modal.IMAGE_TYPE, "Hi again. Another cool image here. Which one is better?", R.drawable.snow));

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        RecyclerView mRecyclerView = findViewById(R.id.recycleviewMain);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);


    }
}
