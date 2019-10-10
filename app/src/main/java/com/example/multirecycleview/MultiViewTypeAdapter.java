package com.example.multirecycleview;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private boolean fabStateVolume = false;
    private ArrayList<Modal> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;

    public MultiViewTypeAdapter(ArrayList<Modal> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Modal.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);

            case Modal.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);

            case Modal.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Modal.TEXT_TYPE;
            case 1:
                return Modal.IMAGE_TYPE;
            case 2:
                return Modal.AUDIO_TYPE;
            default:
                return -1;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int listPosition) {


        Modal object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {

                case Modal.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);

                    break;
                case Modal.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);

                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Modal.AUDIO_TYPE:
                    ((AudioTypeViewHolder) holder).txtType.setText(object.text);

                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (fabStateVolume) {
                                if (mPlayer.isPlaying()) {
                                    mPlayer.stop();

                                }
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.volume);
                                fabStateVolume = false;

                            } else {
                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
                                mPlayer.setLooping(true);
                                mPlayer.start();
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
                                fabStateVolume = true;

                            }
                        }
                    });
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    //Viewtype 1
    public class TextTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtType;
        CardView cardview;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.cardview = itemView.findViewById(R.id.cardview);
        }
    }

//Viewtype 2
    public class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ImageView image;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.txtType = itemView.findViewById(R.id.type);
            this.image = itemView.findViewById(R.id.imageView);

        }

    }
//Viewtype 3

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.fab = itemView.findViewById(R.id.fab);
        }
    }
}




