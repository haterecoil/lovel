package lorem.lovel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gt-walsheim-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Suleiman Ali Shakir");

        ImageView header = (ImageView) findViewById(R.id.header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_action_name);

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
}
