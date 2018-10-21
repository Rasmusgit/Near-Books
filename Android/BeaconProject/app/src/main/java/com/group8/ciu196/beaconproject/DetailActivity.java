package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group8.ciu196.beaconproject.profile.ProfileActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private OthersRecyclerViewAdapter othersAdapter;
    Button queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final int index = intent.getIntExtra("index",0);
        final String category = intent.getStringExtra("cat");
        final BookManagerSingelton bookmanager = BookManagerSingelton.getInstance();

        setContentView(R.layout.activity_detail);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorBlue));

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        RecyclerView othersRead = findViewById(R.id.othersRead);

        Book book = BookManagerSingelton.getInstance().getBooksByCategory(category).get(index);

        TextView textAuthor = findViewById(R.id.text_author);
        textAuthor.setText(book.getAuthor());
        TextView textAvailable = findViewById(R.id.text_available);
        textAvailable.setText(book.getAvailability() + " available");
        TextView textShelf = findViewById(R.id.text_shelf);
        textShelf.setText(book.getShelf());
        TextView textIsbn = findViewById(R.id.text_isbn);
        textIsbn.setText(book.getIsbn());
        TextView textPublication = findViewById(R.id.text_publication);
        textPublication.setText(book.getPublication());
        TextView textOrigin = findViewById(R.id.text_origin);
        textOrigin.setText(book.getOrigin());
        TextView title = toolbar.findViewById(R.id.location_title);
        title.setText(book.getTitle());
        ImageView cover = findViewById(R.id.image_cover);
        cover.setImageResource(getImageId(this,book.getImageStr()));

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        othersRead.setLayoutManager(horizontalLayoutManager);
        othersAdapter = new OthersRecyclerViewAdapter(this, BookManagerSingelton.getInstance().getBooksByCategory(book.getCategory()), category);
        //othersAdapter.setClickListener(this);
        othersRead.setAdapter(othersAdapter);

        queue = findViewById(R.id.queue);
        ArrayList<Book>queuedbooks = bookmanager.getQueue();
        for(int i=0;i<queuedbooks.size();i++)
        {
            if(queuedbooks.get(i).getIsbn()== bookmanager.getBooksByCategory(category).get(index).getIsbn())
            {
                queue.setBackgroundColor(0xffcccccc);
            }
        }


        queue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(bookmanager.getQueue().size()<4)
                {
                    boolean isNotAdded = bookmanager.addToQueue(bookmanager.getBooksByCategory(category).get(index));
                    //queue.setEnabled(false);

                    if(!isNotAdded){

                        bookmanager.removeFromQueue(bookmanager.getBooksByCategory(category).get(index));

                        queue.setBackgroundResource(R.color.colorBlue);
                        Toast.makeText(DetailActivity.this, "Removed queue!",
                                Toast.LENGTH_LONG).show();
                    }else{
                        queue.setBackgroundColor(0xffcccccc);
                        Toast.makeText(DetailActivity.this, "Added to queue!",
                                Toast.LENGTH_LONG).show();
                    }


                }
                else
                {
                    Toast.makeText(DetailActivity.this, "Too many books already in queue!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile_button:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
