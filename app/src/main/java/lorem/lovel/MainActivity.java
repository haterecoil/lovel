package lorem.lovel;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lorem.lovel.adapters.BigCountryCardAdapter;
import lorem.lovel.fragments.CardCollectionFragment;
import lorem.lovel.models.CardModel;
import lorem.lovel.models.CountryModel;
import lorem.lovel.models.EventModel;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
//    Once you have added a RecyclerView widget to your layout, obtain a handle to the object,
// connect it to a layout manager, and attach an adapter for the data to be displayed:

    private RecyclerView mBigCountryRV;
    private RecyclerView.Adapter mBigCountryA;
    private LinearLayoutManager mBigCountryLM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gt-walsheim-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        mBigCountryRV = (RecyclerView) findViewById(R.id.Home_RecyclerView_HighlightedCountries);

        mBigCountryLM = new LinearLayoutManager(this);
        mBigCountryLM.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBigCountryRV.setLayoutManager(mBigCountryLM);
        mBigCountryRV.setHorizontalScrollBarEnabled(false);

        mBigCountryA = new BigCountryCardAdapter(createList(), this);
        mBigCountryRV.setAdapter(mBigCountryA);

        if(savedInstanceState==null){
            initCardCollectionFragment(R.id.Home_Fragment_Container_country_cardview,
                    R.string.home_country_discover,
                    CardModel.TYPE_COUNTRY);
            initCardCollectionFragment(R.id.Home_Fragment_Container_events_cardview,
                    R.string.home_events_around,
                    CardModel.TYPE_EVENT);
        }

    }

    private void initCardCollectionFragment(int fragment_container_id,
                                            int fragment_title_id,
                                            int cardType) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(fragment_container_id) != null) {

            // Create a new Fragment to be placed in the activity layout
            CardCollectionFragment firstFragment = new CardCollectionFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle args = getIntent().getExtras();
            if (args == null){
                args = new Bundle();
            }
            args.putString("collectionTitle", getString(fragment_title_id));
            args.putInt("cardType", cardType);
            firstFragment.setArguments(args);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(fragment_container_id, firstFragment).commit();
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private List<EventModel> createEventList() {
        List<EventModel> eventList = new ArrayList<>();
        eventList.add(new EventModel("Le swag au panama", "Paris XV"));
        eventList.add(new EventModel("La vie en rose", "Rueil-Malmaison"));
        eventList.add(new EventModel("Teuf façon Techécoslovaquie", "Fontainebleau"));
        eventList.add(new EventModel("Bukkake", "Centre du Japon"));

        return eventList;
    }

    private List<CardModel> createList() {
        List<CardModel> countryList = new ArrayList<>();
        countryList.add(new CardModel()
                .setName("Hongrie")
                .setCardType(CardModel.TYPE_COUNTRY));
        countryList.add(new CardModel()
                .setName("Suisse")
                .setCardType(CardModel.TYPE_COUNTRY));
        countryList.add(new CardModel()
                .setName("Italie")
                .setCardType(CardModel.TYPE_COUNTRY));

        return countryList;
    }

}
