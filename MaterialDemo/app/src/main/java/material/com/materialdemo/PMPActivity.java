package material.com.materialdemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class PMPActivity extends ActionBarActivity {

    private PMPListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager, mCategoryLayoutManager;
    private Toolbar toolbar;
    private MenuItem mMenuItemSearch;
    private RecyclerView pmpList;
    private SearchView mSearchView;
    String TITLES[] = {"Home", "Shop by Category", "Wallet", "YES2YOU Rewards", "Account", "Lists", "Registries", "Deals & Coupos", "Store Locator"};
    int ICONS[] = {R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu, R.drawable.ic_menu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmp_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView categoryList = (RecyclerView) findViewById(R.id.category_list);
        ImageView shoppingBag = (ImageView) findViewById(R.id.shopping_bag);

        shoppingBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO this code will never be called as on Touch is implemented
                Toast.makeText(PMPActivity.this, "Coming soon..", Toast.LENGTH_SHORT).show();
            }
        });

        MultiTouchListener touchListener = new MultiTouchListener(this);
        shoppingBag.setOnTouchListener(touchListener);

        categoryList.setAdapter(new CategoryAdapter(TITLES, ICONS));
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

        pmpList = (RecyclerView) findViewById(R.id.pmp_list);
        mLayoutManager = new LinearLayoutManager(this);
        pmpList.setLayoutManager(mLayoutManager);
        pmpList.setHasFixedSize(true);
        final String pmpResponse = getPMPResponse();
        //mAdapter = new PMPListAdapter(this, null);
        //pmpList.setAdapter(mAdapter);
        JSONParsingHelper helper = new JSONParsingHelper(ProductMatrixVO.class, new JSONParsingHelper.IJSONParsingListener() {
            @Override
            public void onParseSuccess(IValueObject valueObject) {
                mAdapter = new PMPListAdapter(PMPActivity.this, ((ProductMatrixVO) valueObject).getPayload().getProducts());
                pmpList.setAdapter(mAdapter);

            }

            @Override
            public void onParseFailure(Error ex) {

            }
        });
        helper.execute(pmpResponse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenuItemSearch = menu.findItem(R.id.action_search);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            mSearchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            mSearchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
        } else {
            mSearchView = (SearchView) MenuItemCompat
                    .getActionView(mMenuItemSearch);
        }

        mSearchView.setQueryHint("Search on Kohl's");
        EditText searchEdit = ((EditText) mSearchView
                .findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setHintTextColor(getResources().getColor(
                R.color.white_translucent));
        searchEdit.setTextColor(getResources().getColor(R.color.white));

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

    private String getPMPResponse() {
        AssetManager assetManager = getAssets();
        InputStream input;
        String text = "";

        try {
            input = assetManager.open("pmpjson");

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            text = new String(buffer, "UTF-8");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.v("TAG", "Text File: " + text);
        return text;
    }

    public void onAddToBackClick(View shoppingBagView) {
        // Get the center point of the shoppingbag icon view
        int top = shoppingBagView.getTop()-(shoppingBagView.getHeight()/2);
        // Find view from Recycleview which is at given position
        // Change X coordinate as per requirement/give the center coordinate of the screen
        View v = pmpList.findChildViewUnder((shoppingBagView.getRight()+200)/2,top);
        // Get postion of the given view
        int position = pmpList.getChildPosition(v);

        Toast.makeText(PMPActivity.this, "Item Position" + position + "\nItem Title : "+((TextView) v.findViewById(R.id.title)).getText(), Toast.LENGTH_SHORT).show();


    }
}
