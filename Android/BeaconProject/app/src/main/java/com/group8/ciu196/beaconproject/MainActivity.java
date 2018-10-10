package com.group8.ciu196.beaconproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory;
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.api.ProximityObserver;
import com.estimote.proximity_sdk.api.ProximityObserverBuilder;
import com.estimote.proximity_sdk.api.ProximityZone;
import com.estimote.proximity_sdk.api.ProximityZoneBuilder;
import com.estimote.proximity_sdk.api.ProximityZoneContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ProximityObserver proximityObserver;
    private boolean mint = false;
    private boolean blue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout root=(LinearLayout)findViewById(R.id.root);
        root.setBackgroundColor(Color.WHITE);

        final TextView textView = (TextView) findViewById(R.id.textView);

        EstimoteCloudCredentials cloudCredentials =
                new EstimoteCloudCredentials("library-experience-android-39o", "3ef92167fc706b44e652a4fc6af53498");



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
                        String event= context.getAttachments().get("Event");
                        String event2= context.getAttachments().get("Book");

                        Log.d("app", "Welcome to the library  " + event + "");
                        Log.d("app","device id " + context.getDeviceId() + " attatchment " + context.getTag() + " Event: " + event  + "Event2: " + event2);

                        textView.setText("Enter beacon " + event);

                        root.setBackgroundColor(Color.parseColor("#B8D4B5"));
                        mint = true;

                        return null;
                    }
                })
                .onExit(new Function1<ProximityZoneContext, Unit>() {
                    @Override
                    public Unit invoke(ProximityZoneContext context) {
                        Log.d("app", "Bye bye, come again!");
                        //root.setBackgroundColor(Color.WHITE);
                        textView.setText("Exit beacon mint");
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
                        String event= context.getAttachments().get("Book");

                        Log.d("app","device id " + context.getDeviceId() + " attatchment " + context.getTag() + " Event: " + event);
                        //context.getAttachments().values();
                        /*while(context.getAttachments().keySet().iterator().hasNext()){
                            Log.d("app", context.getAttachments().keySet().iterator().next());
                        }*/

                        Log.d("app", "Welcome to the books!  " + event + "");

                                root.setBackgroundColor(Color.parseColor("#85c2e5"));
                                textView.setText("Enter beacon " + event);
                                blue = true;



                        return null;
                    }
                })
                .onExit(new Function1<ProximityZoneContext, Unit>() {
                    @Override
                    public Unit invoke(ProximityZoneContext context) {
                        Log.d("app", "Bye bye, come again!");
                        //root.setBackgroundColor(Color.WHITE);
                        textView.setText("Exit beacon blue" );
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
                            @Override public Unit invoke() {
                                Log.d("app", "requirements fulfilled");
                                proximityObserver.startObserving(enterance,books);
                                return null;
                            }
                        },
                        // onRequirementsMissing
                        new Function1<List<? extends Requirement>, Unit>() {
                            @Override public Unit invoke(List<? extends Requirement> requirements) {
                                Log.e("app", "requirements missing: " + requirements);
                                return null;
                            }
                        },
                        // onError
                        new Function1<Throwable, Unit>() {
                            @Override public Unit invoke(Throwable throwable) {
                                Log.e("app", "requirements error: " + throwable);
                                return null;
                            }
                        });

    }
}
