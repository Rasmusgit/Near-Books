package com.group8.ciu196.beaconproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.group8.ciu196.beaconproject.profile.ProfileActivity;

import java.util.ArrayList;

public class DetailEventActivity extends AppCompatActivity {

    private OthersRecyclerViewAdapter othersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();

        int pos = intent.getIntExtra("POS", -1);


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

        TextView title = toolbar.findViewById(R.id.location_title);



        RecyclerView othersRead = findViewById(R.id.othersRead);

        EventManager eventManager = EventManager.getInstance();
        ImageView imageView = findViewById(R.id.imgView);
        TextView dateText = findViewById(R.id.textDate);
        TextView timeText = findViewById(R.id.textTime);
        TextView detailText = findViewById(R.id.textDetail);

        Event event = eventManager.getAll().get(pos);

        title.setText(event.getTitle());


        imageView.setImageResource(getImageId(this, event.getImage()));
        dateText.setText(event.getDate());
        timeText.setText(event.getTime());
        detailText.setText(event.getDetails());


        ArrayList<String> viewImages = new ArrayList<>();
        viewImages.add("book0");
        viewImages.add("book1");
        viewImages.add("book2");
        viewImages.add("book3");
        viewImages.add("book4");

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        othersRead.setLayoutManager(horizontalLayoutManager);
        othersAdapter = new OthersRecyclerViewAdapter(this, viewImages);
        //othersAdapter.setClickListener(this);
        othersRead.setAdapter(othersAdapter);
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
