package com.group8.ciu196.beaconproject.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.group8.ciu196.beaconproject.DetailActivity;
import com.group8.ciu196.beaconproject.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    ArrayList<ImageView> imgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imgList = new ArrayList<ImageView>();
        View rootView = inflater.inflate(R.layout.activity_history_fragment, container, false);
        imgList.add((ImageView) rootView.findViewById(R.id.book1));
        imgList.add((ImageView) rootView.findViewById(R.id.book2));
        imgList.add((ImageView) rootView.findViewById(R.id.book3));
        imgList.add((ImageView) rootView.findViewById(R.id.book4));
//        ImageView img1 = (ImageView) rootView.findViewById(R.id.book1);
//        ImageView img2 = (ImageView) rootView.findViewById(R.id.book2);

        for(int i =0;i<imgList.size();i++)
        {
            imgList.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity() , DetailActivity.class);
                    startActivity(intent);
                }

            });
        }


        return rootView;
    }
}
