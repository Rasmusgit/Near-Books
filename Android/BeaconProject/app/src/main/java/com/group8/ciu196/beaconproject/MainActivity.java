package com.group8.ciu196.beaconproject;

import android.content.Intent;
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
    private final boolean ESTIMOTEMODE = false;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorBlue));


        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_placeholder, EntranceFragment.newInstance("","")).commit();

        EventManager eventManager = EventManager.getInstance();
        eventManager.addEvent("MUSIKPRODUKTION FÖR DIG 13-25 ÅR", "event0", "20181018", "1600", "1800", "Tio workshops för dig som vill skapa egen musik! Kanske blir det din nya hobby eller din framtida karriär? TV-spelsmusikkompositören Jacob Lincke lär dig allt om musikproduktion, olika genrer och hur man skapar passande musik och stämning till något visuellt som till exempel ett spel.");
        eventManager.addEvent("DEN NYA STADEN", "event1", "20181022", "1230", "1245", "Är du nyfiken på hur centrala Göteborg kommer att utvecklas? I en serie programpunkter på Stadsbiblioteket 300m2 i Brunnsparken berättar projektledare och arkitekter från Stadsbyggnadskontoret om några spännande och aktuella stadsutvecklingsprojekt.");
        eventManager.addEvent("START FÖR FÖRÄLDRAUTBILDNING: DYSLEXI/LÄS- OCH SKRIVSVÅRIGHETER", "event2", "20181016", "1800", "2000", "En kurs för dig som har barn med dyslexi eller läs- och skrivsvårigheter. Om hur du kan hjälpa och stödja ditt barn samt utveckla samarbetet med skolan. Ledare är specialpedagog och tal- och språkpedagog Helena Jacobsson som själv är förälder till barn med dyslexi.");

        BookManagerSingelton bookManager = BookManagerSingelton.getInstance();
        //bookManager.createBook("J.K. Rowling","Fantastic Beasts: The Crimes of Grindelwald",3, "9781408711705", "HC Engelska","book0");
        //bookManager.createBook("Brené Brown","Dare to Lead: Brave Work. Tough Conversations. Whole Hearts",1, "9781785042140", "HC Engelska","book1");
        ArrayList<Book> bookList = BookStorage.getInstance().jsonToBook();
       for(int i = 0; i < bookList.size(); i++) {
           bookManager.add(bookList.get(i));
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
                    .forTag("Entrance").inNearRange()
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

            final ProximityZone music = new ProximityZoneBuilder()
                    .forTag("Music").inNearRange()
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


            final ProximityZone sci_fi = new ProximityZoneBuilder()
                    .forTag("scifi").inNearRange()
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

            final ProximityZone architecture = new ProximityZoneBuilder()
                    .forTag("Architecture").inNearRange()
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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        switch (location){
            case "Architecture":

                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPink));
                TextView title = findViewById(R.id.location_title);
                title.setText(R.string.architecture);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPink));

                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Architecture"), R.id.main_placeholder);
                break;
            case "Sci-fi":
                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPurple));
                TextView title2 = findViewById(R.id.location_title);
                title2.setText(R.string.sci_fi);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple));

                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Sci-fi"), R.id.main_placeholder);
                break;
            case "Music":
                // finally change the color
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorGreen));
                TextView title3 = findViewById(R.id.location_title);
                title3.setText(R.string.music);
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));


                addFragment(getSupportFragmentManager(), ShelfFragment.newInstance("Music"), R.id.main_placeholder);

                break;
        }
    }




    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int id){
        fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit();

    }



    @Override
    public void onBackPressed()
    {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        }else {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
            TextView title = findViewById(R.id.location_title);
            title.setText(R.string.entrence);
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
            toolbar.setNavigationIcon(null);
            super.onBackPressed();
        }
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


