package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EntranceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EntranceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntranceFragment extends Fragment implements EventRecyclerViewAdapter.ItemClickListener, BookRecyclerViewAdapter.ItemClickListener{

    private EventRecyclerViewAdapter eventAdapter;
    private BookRecyclerViewAdapter bookAdapter;


    private OnFragmentInteractionListener mListener;

    public EntranceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntranceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntranceFragment newInstance(String param1, String param2) {
        EntranceFragment fragment = new EntranceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrance, container, false);


        // data to populate the RecyclerView with
        ArrayList<Integer> viewColors = new ArrayList<>();
        viewColors.add(Color.BLUE);
        viewColors.add(Color.YELLOW);
        viewColors.add(Color.MAGENTA);
        viewColors.add(Color.RED);
        viewColors.add(Color.BLACK);

        ArrayList<String> viewImages = new ArrayList<>();
        viewImages.add("event0");
        viewImages.add("event1");
        viewImages.add("event2");
        viewImages.add("event3");
        viewImages.add("event4");

        ArrayList<String> viewImages2 = new ArrayList<>();
        viewImages2.add("book0");
        viewImages2.add("book1");
        viewImages2.add("book2");
        viewImages2.add("book3");
        viewImages2.add("book4");

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        RecyclerView rvEvents = view.findViewById(R.id.rvEvents);
        RecyclerView rvBooks = view.findViewById(R.id.rvBooks);


        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvEvents.setLayoutManager(horizontalLayoutManager);
        eventAdapter = new EventRecyclerViewAdapter(getContext(), viewImages, animalNames);
        eventAdapter.setClickListener(this);
        rvEvents.setAdapter(eventAdapter);


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvEvents);


        LinearLayoutManager horizontalLayoutManagerBooks
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvBooks.setLayoutManager(horizontalLayoutManagerBooks);
        bookAdapter = new BookRecyclerViewAdapter(getContext(), viewImages2, animalNames);
        bookAdapter.setClickListener(this);
        rvBooks.setAdapter(bookAdapter);


        SnapHelper snapHelperBooks = new LinearSnapHelper();
        snapHelperBooks.attachToRecyclerView(rvBooks);


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

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + eventAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
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
