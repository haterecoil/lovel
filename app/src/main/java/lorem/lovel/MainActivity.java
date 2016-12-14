package lorem.lovel;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lorem.lovel.adapters.BigCountryCardAdapter;
import lorem.lovel.fragments.CardCollectionFragment;
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

    private RecyclerView mCountryRV;
    private RecyclerView.Adapter mCountryA;
    private LinearLayoutManager mCountryLM;

    private RecyclerView mEventRV;
    private RecyclerView.Adapter mEventA;
    private LinearLayoutManager mEventLM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NeueHaasGrotDisp-Roman.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_main);

        mBigCountryRV = (RecyclerView) findViewById(R.id.Home_RecyclerView_HighlightedCountries);

        mBigCountryLM = new LinearLayoutManager(this);
        mBigCountryLM.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBigCountryRV.setLayoutManager(mBigCountryLM);
        mBigCountryRV.setHorizontalScrollBarEnabled(false);

        mBigCountryA = new BigCountryCardAdapter(createList());
        mBigCountryRV.setAdapter(mBigCountryA);

        setCollectionTitle(R.id.Home_Fragment_country_cardview, R.string.home_country_discover);
        setCollectionTitle(R.id.Home_Fragment_events_cardview, R.string.home_events_around);



        // set fragment's title
/*        CardCollectionFragment countriesFragment = (CardCollectionFragment)
                getSupportFragmentManager().
                findFragmentById(R.id.Home_Fragment_country_cardview);
        countriesFragment.setCollectionTitle(R.string.home_country_discover);

        CardCollectionFragment eventsFragment = (CardCollectionFragment)
                getSupportFragmentManager().
                        findFragmentById(R.id.Home_Fragment_events_cardview);
        eventsFragment.setCollectionTitle(R.string.home_events_around);*/

    }

    private void setCollectionTitle(Integer fragment_id, Integer string_id) {
        String collectionTitle = getString(string_id);
        CardCollectionFragment fragment = new CardCollectionFragment();

        Bundle bundle = new Bundle();
        bundle.putString("collectionTitle", collectionTitle );

        fragment.setArguments(bundle);

        android.support.v4.app.FragmentManager transaction = getSupportFragmentManager();
        transaction.beginTransaction().
            replace(fragment_id, fragment).
            commit();
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

    private List<CountryModel> createList() {
        List<CountryModel> countryList = new ArrayList<>();
        countryList.add(new CountryModel("France"));
        countryList.add(new CountryModel("Allemagne"));
        countryList.add(new CountryModel("Belgique"));
        countryList.add(new CountryModel("Portugal"));
        countryList.add(new CountryModel("Roumanie"));

        return countryList;
    }

}
