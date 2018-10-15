package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private List<String> mViewImage;
    private LayoutInflater mInflater;
    private BookRecyclerViewAdapter.ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    BookRecyclerViewAdapter(Context context, List<String> images) {
        this.mInflater = LayoutInflater.from(context);
        this.mViewImage = images;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclingview_item2, parent, false);


        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String image = mViewImage.get(position);

        holder.myView.setImageResource(getImageId(this.context,image));
        holder.myView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //holder.myTextView.setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return BookManagerSingelton.getInstance().getAllBooks().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imageView);
            //myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(view.getContext(),DetailActivity.class);
            intent.putExtra("index",getAdapterPosition());
            view.getContext().startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    public Book getItem(int id) {
        return BookManagerSingelton.getInstance().getBook(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}