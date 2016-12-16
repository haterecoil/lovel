package lorem.lovel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lorem.lovel.R;
import lorem.lovel.models.CardModel;

/**
 * Created by mrgn on 15/12/2016.
 *
 */

public class CountryEventsFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        //initDataset();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.country_events_fragment, container, false);



        if(savedInstanceState==null){
            initCardCollectionFragment(rootView, R.id.countryevents_fragment_one, "Vendredi 15 Dec.");
            initCardCollectionFragment(rootView, R.id.countryevents_fragment_two, "Samedi 16 Dec.");
            initCardCollectionFragment(rootView, R.id.countryevents_fragment_three, "Mardi 19 Dec.");
            initCardCollectionFragment(rootView, R.id.countryevents_fragment_four, "Samedi 23 Dec.");
            initCardCollectionFragment(rootView, R.id.countryevents_fragment_five, "Dimanche 24 Dec.");
        }

        return rootView;
    }

    private void initCardCollectionFragment(View v,
                                            int fragment_container_id,
                                            String fragment_title) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (v.findViewById(fragment_container_id) != null) {

            // Create a new Fragment to be placed in the activity layout
            CardCollectionFragment firstFragment = new CardCollectionFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle args = new Bundle();
            args.putString("collectionTitle",fragment_title);
            args.putInt("cardType", CardModel.TYPE_EVENT);
            firstFragment.setArguments(args);

            // Add the fragment to the 'fragment_container' FrameLayout
            getChildFragmentManager().beginTransaction()
                    .add(fragment_container_id, firstFragment).commit();
        }
    }


}
