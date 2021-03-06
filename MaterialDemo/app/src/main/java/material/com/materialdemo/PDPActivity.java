package material.com.materialdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import material.com.materialdemo.PMP.PMPFragment;

/**
 */
public class PDPActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private LinearLayoutManager mCategoryLayoutManager;
    String TITLES[] = {"Home","Shop by Category","Wallet","YES2YOU Rewards","Account", "Lists", "Registries", "Deals & Coupos", "Store Locator"};
    int ICONS[] = {R.drawable.ic_menu, R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu,R.drawable.ic_menu};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView shoppingBag = (ImageView)findViewById(R.id.shopping_bag);
        shoppingBag.setImageResource(R.drawable.shopping_bag);

        final RecyclerView categoryList = (RecyclerView) findViewById(R.id.category_list);
        categoryList.setHasFixedSize(true);
        categoryList.setAdapter(new CategoryAdapter(TITLES,ICONS));
        mCategoryLayoutManager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(mCategoryLayoutManager);

        final DrawerLayout Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        String url = getIntent().getStringExtra("url");
        String updatedUrl = UtilityMethods.getUpdatedURL(url, 250, 250);

        NetworkImageView image = (NetworkImageView) findViewById(R.id.image);

        LoadImageTask.getInstance(this).loadImage(updatedUrl,
                image, 0, 0);

        colorize(PMPFragment.clickedImage);

        //Set title
        String title = getIntent().getStringExtra("title");
        ((TextView) findViewById(R.id.title)).setText(title);
    }

    private void colorize(Bitmap photo) {
        Palette palette = Palette.generate(photo);
        applyPalette(palette);
    }

    private void applyPalette(Palette palette) {
        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkMutedColor(16)));

        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setTextColor(palette.getVibrantColor(16));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(palette.getDarkVibrantColor(6));

    }

    @Override
    protected void onStop() {
        super.onStop();
        finishAfterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
