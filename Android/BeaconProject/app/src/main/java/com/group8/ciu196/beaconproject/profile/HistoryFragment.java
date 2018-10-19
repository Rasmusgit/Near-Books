package com.group8.ciu196.beaconproject.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.group8.ciu196.beaconproject.Book;
import com.group8.ciu196.beaconproject.BookManagerSingelton;
import com.group8.ciu196.beaconproject.DetailActivity;
import com.group8.ciu196.beaconproject.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    String imgname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history_fragment, container, false);
        BookManagerSingelton bookmanager = BookManagerSingelton.getInstance();
        ArrayList<Book> books = bookmanager.getAllBooks();

        ImageView q1 = rootView.findViewById(R.id.book1);
        ImageView q2 = rootView.findViewById(R.id.book2);
        ImageView q3 = rootView.findViewById(R.id.book3);
        ImageView q4 = rootView.findViewById(R.id.book4);

        imgname = bookmanager.getBook(0).getImageStr();
        q1.setImageResource(getImageId(getActivity(),imgname));
        imgname = bookmanager.getBook(1).getImageStr();
        q2.setImageResource(getImageId(getActivity(),imgname));
        imgname = bookmanager.getBook(2).getImageStr();
        q3.setImageResource(getImageId(getActivity(),imgname));
        imgname = bookmanager.getBook(3).getImageStr();
        q4.setImageResource(getImageId(getActivity(),imgname));

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", 0); //Optional parameters
                startActivity(myIntent);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", 1); //Optional parameters
                startActivity(myIntent);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", 2); //Optional parameters
                startActivity(myIntent);
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),DetailActivity.class);
                myIntent.putExtra("index", 3); //Optional parameters
                startActivity(myIntent);
            }
        });



        return rootView;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
