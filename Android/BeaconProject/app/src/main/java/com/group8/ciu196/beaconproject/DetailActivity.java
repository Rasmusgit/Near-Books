package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.content.Intent;
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



        TextView textAuthor = findViewById(R.id.text_author);
        textAuthor.setText(BookManagerSingelton.getInstance().getBook(index).getAuthor());
        TextView textAvailable = findViewById(R.id.text_available);
        textAvailable.setText(BookManagerSingelton.getInstance().getBook(index).getAvailability() + " available");
        TextView textShelf = findViewById(R.id.text_shelf);
        textShelf.setText(BookManagerSingelton.getInstance().getBook(index).getShelf());
        TextView textIsbn = findViewById(R.id.text_isbn);
        textIsbn.setText(BookManagerSingelton.getInstance().getBook(index).getIsbn());
        TextView title = toolbar.findViewById(R.id.location_title);
        title.setText(BookManagerSingelton.getInstance().getBook(index).getTitle());
        ImageView cover = findViewById(R.id.image_cover);
        cover.setImageResource(getImageId(this,BookManagerSingelton.getInstance().getBook(index).getImageStr()));

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        othersRead.setLayoutManager(horizontalLayoutManager);
        othersAdapter = new OthersRecyclerViewAdapter(this, BookManagerSingelton.getInstance().getAllBooks());
        //othersAdapter.setClickListener(this);
        othersRead.setAdapter(othersAdapter);

        queue = findViewById(R.id.queue);
        ArrayList<Book>queuedbooks = bookmanager.getQueue();
        for(int i=0;i<queuedbooks.size();i++)
        {
            if(queuedbooks.get(i).getIsbn()== bookmanager.getBook(index).getIsbn())
            {
                queue.setEnabled(false);
            }
        }


        queue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(bookmanager.getQueue().size()<=4)
                {
                    bookmanager.addToQueue(bookmanager.getBook(index));
                    queue.setEnabled(false);
                    Toast.makeText(DetailActivity.this, "Added to queue!",
                            Toast.LENGTH_LONG).show();
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
