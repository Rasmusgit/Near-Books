package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EntranceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EntranceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfFragment extends Fragment {

    private FragmentStatePagerAdapter adapterViewPager;
    private String category;

    private static final String TAG = "ShelfFragment";




    private OnFragmentInteractionListener mListener;

    public ShelfFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ShelfFragment newInstance(String param) {
        ShelfFragment fragment = new ShelfFragment();
        Bundle args = new Bundle();
        Log.i(TAG,"categoty: " + param);
        args.putString("category", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            this.category = getArguments().getString("category");
            Log.i(TAG,"categoty: " + category);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_shelf, container, false);

        adapterViewPager = new SwipePagerAdapter(getFragmentManager(),this.category);

        ViewPager vpPager = view.findViewById(R.id.vpPager);
        vpPager.setClipToPadding(false);

        vpPager.setOffscreenPageLimit(3);

        vpPager.setPageMargin(0);

        vpPager.setAdapter(adapterViewPager);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {



            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                /*Toast.makeText(getContext(),
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();*/

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });





        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
