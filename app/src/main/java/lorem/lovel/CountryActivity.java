package lorem.lovel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import lorem.lovel.fragments.CountryEventsFragment;
import lorem.lovel.fragments.CountryFoodFragment;
import lorem.lovel.fragments.CountryPlacesFragment;
import lorem.lovel.models.CardModel;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by mrgn on 14/12/2016.
 *
 */
public class CountryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CollapsingToolbarLayout collapsingToolbar;
    private CardModel mCountry;
    int mutedColor = R.attr.colorPrimary;
    private int[] tabIcons = {
            R.drawable.ic_event_note_black_24dp,
            R.drawable.ic_place_black_24dp,
            R.drawable.ic_restaurant_menu_black_24dp
    };
    private String[] tabLabels = {
            "EVENTS",
            "LIEUX",
            "RESTOS"
    };
    private final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/gt-walsheim-bold.ttf");
        collapsingToolbar.setExpandedTitleTypeface(tf);
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        String countryName = getIntent().getStringExtra("countryName");
        if (countryName == null) { throw new Error("no country params :/"); }

        mCountry = new CardModel().setName(countryName).setCardType(CardModel.TYPE_COUNTRY);

        collapsingToolbar.setTitle(mCountry.getName());

        Glide.with(this)
                .load(mCountry.getIllustrationPath(true))
                .asBitmap()
                .placeholder(R.drawable.progress_animation)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @SuppressWarnings("ResourceType")
                            @Override
                            public void onGenerated(Palette palette) {

                                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                                collapsingToolbar.setContentScrimColor(mutedColor);
                            }
                        });

                        ImageView headerView = (ImageView) findViewById(R.id.header);;
                        headerView.setImageBitmap(resource);
                    }
                });

        viewPager = (ViewPager) findViewById(R.id.county_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
