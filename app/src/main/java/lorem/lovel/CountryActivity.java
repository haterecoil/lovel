package lorem.lovel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lorem.lovel.fragments.CountryEventsFragment;
import lorem.lovel.fragments.CountryFoodFragment;
import lorem.lovel.fragments.CountryPlacesFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by mrgn on 14/12/2016.
 *
 */
public class CountryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_event_note_black_24dp,
            R.drawable.ic_place_black_24dp,
            R.drawable.ic_restaurant_menu_black_24dp
    };
    private String[] tabLabels = {
            "Events",
            "Places",
            "Food"
    };
    private final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gt-walsheim-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.county_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        int i = 0;
        while (i < TAB_COUNT) {
            View tab = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            TextView tabIcon = (TextView) tab.findViewById(R.id.tab_icon);
            tabIcon.setCompoundDrawablesWithIntrinsicBounds(tabIcons[i], 0, 0, 0);

            TextView tabLabel = (TextView) tab.findViewById(R.id.tab_label);
            tabLabel .setText(tabLabels[i]);

            tabLayout.getTabAt(i).setCustomView(tab);
            i = i+1;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CountryEventsFragment(), "Ivents");
        adapter.addFragment(new CountryPlacesFragment(), "Places");
        adapter.addFragment(new CountryFoodFragment(), "Food");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
