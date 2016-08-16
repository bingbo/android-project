package com.ibingbo.demo.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.ibingbo.demo.R;

/**
 * Created by zhangbingbing on 16/8/13.
 */
public class CardViewFragment extends Fragment {
    private static final String TAG=CardViewFragment.class.getSimpleName();

    CardView mCardView;

    SeekBar mRaiusSeekBar;

    SeekBar mElevationSeekBar;


    public static CardViewFragment newInstance(int sectionNum){
        CardViewFragment fragment=new CardViewFragment();
        Bundle args=new Bundle();
        args.putInt("section_number",sectionNum);
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCardView=(CardView)view.findViewById(R.id.cardView);
        mRaiusSeekBar=(SeekBar)view.findViewById(R.id.cardview_radius_seekbar);
        mRaiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.d(TAG, String.format("SeekBar Radius progress : %d", progress));
                mCardView.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mElevationSeekBar=(SeekBar)view.findViewById(R.id.cardview_elevation_seekbar);
        mElevationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.d(TAG, String.format("SeekBar Elevation progress : %d", progress));
                mCardView.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_view,container,false);
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    public CardViewFragment(){

    }
}
