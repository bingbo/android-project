package com.example.zhangbingbing.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 *
 */
public class Fragment2 extends Fragment {

    public Fragment2() {
        // Required empty public constructor
    }


    /**
     * 为Fragment加载布局时调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    /**
     * 当Activity中的onCreate方法执行完后调用
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //这里当同时出现两个fragment时,点击第二个fragment里的按钮可获取并修改第一个fragment里的textview文案
//        getActivity().findViewById(R.id.btn_fgm2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView view = (TextView) getActivity().findViewById(R.id.txt_view_fgm1);
//                Toast.makeText(getActivity(),view.getText().toString(),Toast.LENGTH_SHORT).show();
//                view.setText("change fragment1's text to this text");
//            }
//        });
    }
}
