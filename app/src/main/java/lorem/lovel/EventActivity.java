package lorem.lovel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by mrgn on 15/12/2016.
 *
 */

public class EventActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    int mutedColor = R.attr.colorPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


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
        collapsingToolbar.setTitle(" ");

        ImageView header = (ImageView) findViewById(R.id.header);
        InputStream bitmapStream = null;
        try {
            bitmapStream = getAssets().open("img/event_header.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap=BitmapFactory.decodeStream(bitmapStream);

        Glide.with(this)
                .load(Uri.parse("file:///android_asset/img/event_header.png"))
                .centerCrop()
                .into(header);

        ImageView c_logo = (ImageView) findViewById(R.id.citymapper_img_logo);
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/img/citymapper_logo.png"))
                .centerCrop()
                .into(c_logo);

        ImageView c_metro= (ImageView) findViewById(R.id.citymapper_img_metro);
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/img/citymapper_metros.png"))
                .into(c_metro);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.colorPrimaryDark);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
