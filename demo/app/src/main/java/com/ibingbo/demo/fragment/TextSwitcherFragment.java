package com.ibingbo.demo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.ibingbo.demo.MainActivity;
import com.ibingbo.demo.R;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TextSwitcherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextSwitcherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    private TextSwitcher textSwitcher;
    private Button button;
    private int mCounter=0;
    public TextSwitcherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextSwitcherFragment.
     */
    public static TextSwitcherFragment newInstance(int sectionNum) {
        TextSwitcherFragment fragment = new TextSwitcherFragment();
        Bundle args=new Bundle();
        args.putInt("section_number",sectionNum);
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_switcher, container, false);
        textSwitcher=(TextSwitcher)view.findViewById(R.id.text_switcher_view);
        button=(Button)view.findViewById(R.id.btn_text_switcher_next);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getContext());
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                t.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
                return t;
            }
        });
        Animation in = AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter++;
                textSwitcher.setText(String.valueOf(mCounter));
            }
        });
        textSwitcher.setCurrentText(String.valueOf(mCounter));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
    }






}
