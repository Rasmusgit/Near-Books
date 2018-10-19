package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    // Store instance variables
    private String category;
    private int page;
    private static final String TAG = "BookFragment";

    // newInstance constructor for creating fragment with arguments
    public static BookFragment newInstance(int page, String category) {
        BookFragment fragmentFirst = new BookFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("category", category);
        Log.i(TAG,"categoty: " + category);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
        category = getArguments().getString("category");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BookManager bookManager = BookManagerSingelton.getInstance();
        ArrayList<Book> bookList = ((BookManagerSingelton) bookManager).getBooksByCategory(category);

        View view = inflater.inflate(R.layout.pager_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);

        if(bookList != null && bookList.size() > 0){
            imageView.setImageResource(getImageId(getContext(),bookList.get(page).getImageStr()));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else {
            
        }




        return view;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}