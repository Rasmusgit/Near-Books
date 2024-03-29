package com.group8.ciu196.beaconproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
import android.view.MenuItem;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.group8.ciu196.beaconproject.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity implements EntranceFragment.OnFragmentInteractionListener, ShelfFragment.OnFragmentInteractionListener{

    private ProximityObserver proximityObserver;
    private boolean mint = false;
    private boolean blue = false;
    public final static boolean ESTIMOTEMODE = false;
    private Toolbar toolbar;
    private String location = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if(!ESTIMOTEMODE){
            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorBlue));
            changeLocationView("Entrance");
            //getSupportFragmentManager().beginTransaction().replace(R.id.main_placeholder, EntranceFragment.newInstance("","")).commit();

        }else{

            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorNone));
            location = "";
            changeLocationView(location);
        }




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
                    .forTag("entrance").inNearRange()
                    .onEnter(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            String event = context.getAttachments().get("Event");
                            String event2 = context.getAttachments().get("Book");

                            Log.d("app", "Welcome to the library  " + event + "");
                            Log.d("app", "device id " + context.getDeviceId() + " attatchment " + context.getTag() + " Event: " + event + "Event2: " + event2);

                            location = "Entrance";
                            changeLocationView(location);
                            //mint = true;

                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon mint");
                            //mint = false;
                            return null;
                        }
                    })
                    .build();

            final ProximityZone music = new ProximityZoneBuilder()
                    .forTag("music").inNearRange()
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
                            //blue = true;
                            location = "Music";
                            changeLocationView(location);

                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon blue");
                            //blue = false;
                            return null;
                        }
                    })
                    .build();


            final ProximityZone sci_fi = new ProximityZoneBuilder()
                    .forTag("sci-fi").inNearRange()
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
                            //blue = true;

                            location = "Sci-fi";
                            changeLocationView(location);



                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon blue");
                            //blue = false;
                            return null;
                        }
                    })
                    .build();

            final ProximityZone architecture = new ProximityZoneBuilder()
                    .forTag("architecture").inNearRange()
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
                            //blue = true;


                            location = "Architecture";
                            changeLocationView(location);


                            return null;
                        }
                    })
                    .onExit(new Function1<ProximityZoneContext, Unit>() {
                        @Override
                        public Unit invoke(ProximityZoneContext context) {
                            Log.d("app", "Bye bye, come again!");
                            //root.setBackgroundColor(Color.WHITE);
                            //textView.setText("Exit beacon blue");
                            //blue = false;
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
                                    proximityObserver.startObserving(enterance, music, sci_fi, architecture);
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
    public void onFragmentInteraction(Uri uri) {

    }

    public void changeFragment(View view) {
        String location = view.getTag().toString();
        // Begin the transaction
        changeLocationView(location);

    }

    public void changeLocationView(String location){

        TextView title = findViewById(R.id.location_title);

        if(!ESTIMOTEMODE) {
            toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }


        switch (location){
            case "Entrance":

                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorBlue));

                title.setText(R.string.entrence);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));

                addFragment(getSupportFragmentManager(), EntranceFragment.newInstance("",""), R.id.main_placeholder);
                break;
            case "Architecture":

                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPink));
                title.setText(R.string.architecture);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPink));

                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Architecture"), R.id.main_placeholder);
                break;
            case "Sci-fi":
                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPurple));
                title.setText(R.string.sci_fi);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple));

                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Sci-fi"), R.id.main_placeholder);
                break;
            case "Music":
                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorGreen));
                title.setText(R.string.music);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));


                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Music"), R.id.main_placeholder);

                break;
            default:
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorNone));
                title.setText("Go to location");
                addFragment(getSupportFragmentManager(), NoLocationFragment.newInstance("",""), R.id.main_placeholder);
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            changeLocationView(location);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            changeLocationView(location);
        }
    }




    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int id){
        fragmentManager.beginTransaction().replace(id, fragment).commit();

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

}


