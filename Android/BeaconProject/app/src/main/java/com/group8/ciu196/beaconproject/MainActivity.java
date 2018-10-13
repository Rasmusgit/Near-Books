package com.group8.ciu196.beaconproject;

import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory;
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.api.ProximityObserver;
import com.estimote.proximity_sdk.api.ProximityObserverBuilder;
import com.estimote.proximity_sdk.api.ProximityZone;
import com.estimote.proximity_sdk.api.ProximityZoneBuilder;
import com.estimote.proximity_sdk.api.ProximityZoneContext;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    private ProximityObserver proximityObserver;
    private boolean mint = false;
    private boolean blue = false;
    private final boolean ESTIMOTEMODE = false;
    private MyRecyclerViewAdapter adapter;
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorBlue));


        // data to populate the RecyclerView with
        ArrayList<Integer> viewColors = new ArrayList<>();
        viewColors.add(Color.BLUE);
        viewColors.add(Color.YELLOW);
        viewColors.add(Color.MAGENTA);
        viewColors.add(Color.RED);
        viewColors.add(Color.BLACK);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
       /* RecyclerView recyclerView = findViewById(R.id.rvAnimals);


        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MyRecyclerViewAdapter(this, viewColors, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);*/



        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setClipToPadding(false);

        vpPager.setOffscreenPageLimit(3);

        vpPager.setPageMargin(0);

        vpPager.setAdapter(adapterViewPager);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
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



        float pageWidth = adapterViewPager.getPageWidth(0);
        int totalWidth = vpPager.getWidth();
        Log.i("PAGER", "Page width: " + pageWidth + " Total width: " + totalWidth);
        //int centerPadding = Math.round((totalWidth - pageWidth)/2);

        //final LinearLayout root=(LinearLayout)findViewById(R.id.root);
        //root.setBackgroundColor(Color.WHITE);



        EstimoteCloudCredentials cloudCredentials =
                new EstimoteCloudCredentials("library-experience-android-39o", "3ef92167fc706b44e652a4fc6af53498");

        if(ESTIMOTEMODE) {

            this.proximityObserver =
                    new ProximityObserverBuilder(getApplicationContext(), cloudCredentials)
                            .onError(new Function1<Throwable, Unit>() {
                                @Override
                                public Unit invoke(Throwable throwable) {
                                    Log.e("app", "proximity observer error: " + throwable);
                                    return null;
                                }
                            }).withLowLatencyPowerMode().build();


            final ProximityZone enterance = new ProximityZoneBuilder()
                    .forTag("Cafeteria").inNearRange()
                    .onEnter(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            String event = context.getAttachments().get("Event");
                            String event2 = context.getAttachments().get("Book");

                            Log.d("app", "Welcome to the library  " + event + "");
                            Log.d("app", "device id " + context.getDeviceId() + " attatchment " + context.getTag() + " Event: " + event + "Event2: " + event2);

                            //textView.setText("Enter beacon " + event);

                            //root.setBackgroundColor(Color.parseColor("#B8D4B5"));
                            mint = true;

                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon mint");
                            mint = false;
                            return null;
                        }
                    })
                    .build();

            final ProximityZone books = new ProximityZoneBuilder()
                    .forTag("History").inNearRange()
                    .onEnter(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            String event = context.getAttachments().get("Book");

                            Log.d("app", "device id " + context.getDeviceId() + " attatchment " + context.getTag() + " Event: " + event);
                            //context.getAttachments().values();
                        /*while(context.getAttachments().keySet().iterator().hasNext()){
                            Log.d("app", context.getAttachments().keySet().iterator().next());
                        }*/

                            Log.d("app", "Welcome to the books!  " + event + "");

                            //root.setBackgroundColor(Color.parseColor("#85c2e5"));
                            //textView.setText("Enter beacon " + event);
                            blue = true;


                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon blue");
                            blue = false;
                            return null;
                        }
                    })
                    .build();


            RequirementsWizardFactory
                    .createEstimoteRequirementsWizard()
                    .fulfillRequirements(this,
                            // onRequirementsFulfilled
                            new Function0<Unit>() {
                                @Override
                                public Unit invoke() {
                                    Log.d("app", "requirements fulfilled");
                                    proximityObserver.startObserving(enterance, books);
                                    return null;
                                }
                            },
                            // onRequirementsMissing
                            new Function1<List<? extends Requirement>, Unit>() {
                                @Override
                                public Unit invoke(List<? extends Requirement> requirements) {
                                    Log.e("app", "requirements missing: " + requirements);
                                    return null;
                                }
                            },
                            // onError
                            new Function1<Throwable, Unit>() {
                                @Override
                                public Unit invoke(Throwable throwable) {
                                    Log.e("app", "requirements error: " + throwable);
                                    return null;
                                }
                            });

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.locationbar, menu);
        return true;
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }
}
