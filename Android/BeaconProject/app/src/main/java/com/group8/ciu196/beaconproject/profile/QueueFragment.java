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

public class QueueFragment extends Fragment {
    ArrayList<ImageView> imgList;

    int index [];
    BookManagerSingelton booksmanager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_queue_fragment, container, false);

        booksmanager = BookManagerSingelton.getInstance();
        index = new int[20];

        ArrayList<Book>queueList = booksmanager.getQueue();
        ArrayList<ImageView>imageViewList = new ArrayList<ImageView>();
        ImageView q1 = rootView.findViewById(R.id.book1);
        ImageView q2 = rootView.findViewById(R.id.book2);
        ImageView q3 = rootView.findViewById(R.id.book3);
        ImageView q4 = rootView.findViewById(R.id.book4);
        imageViewList.add(q1);
        imageViewList.add(q2);
        imageViewList.add(q3);
        imageViewList.add(q4);



        for(int i=0;i< queueList.size();i++)
        {
            for(int j =0;j<=booksmanager.getAllBooks().size();j++)
            {
                if(queueList.get(i).getIsbn()== booksmanager.getBook(j).getIsbn())
                {
                    index[i] = j;

                    break;
                }
            }
            String imgname = queueList.get(i).getImageStr();
            imageViewList.get(i).setImageResource(getImageId(getActivity(),imgname));
        }

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", index[0]); //Optional parameters
                startActivity(myIntent);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", index[1]); //Optional parameters
                startActivity(myIntent);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", index[2]); //Optional parameters
                startActivity(myIntent);
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", index[3]); //Optional parameters
                startActivity(myIntent);
            }
        });
        return rootView;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
