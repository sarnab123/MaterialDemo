package material.com.materialdemo.PMP;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import material.com.materialdemo.IValueObject;
import material.com.materialdemo.JSONParsingHelper;
import material.com.materialdemo.PDPActivity;
import material.com.materialdemo.PMP.Filter.PMPFilterActivity;
import material.com.materialdemo.ProductMatrixVO;
import material.com.materialdemo.R;

/**
 * Created on 7/24/2015.
 */
public class PMPFragment extends Fragment implements View.OnClickListener, PMPItemListener {

    private View mFragmentView;
    private PMPListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView pmpList;
    private View pmpViewToggle;
    private View pmpViewFilter;


    private PMPVIEWTYPE pmpViewType = PMPVIEWTYPE.LIST;

    private ProductMatrixVO mProductMatrixVO;

    public static Bitmap clickedImage;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pmp_floating_header_view_toggle:
                showPMP(pmpViewType.next());
                updateProductList();
                pmpList.invalidate();
                break;
            case R.id.pmp_floating_header_view_filter:
                startActivity(new Intent(getActivity(), PMPFilterActivity.class));
                break;

        }
    }

    @Override
    public void addItemToFavourite(String webID) {

    }

    @Override
    public void onItemClick(ProductMatrixVO.Payload.Product product,ImageView image) {

        Intent intent = new Intent(getActivity(), PDPActivity.class);
        intent.putExtra("url", product.getImage().getUrl());
        intent.putExtra("title", product.getProductTitle());

        clickedImage = ((BitmapDrawable) image.getDrawable()).getBitmap();

        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="image"
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(getActivity(), image, "image");
        // start the new activity
        startActivity(intent, options.toBundle());

    }

    public enum PMPVIEWTYPE {
        LIST, GRID, CARD;

        PMPVIEWTYPE next() {
            return values()[(ordinal() + 1) % values().length];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.new_fragment_pmp, null);
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadUI();
    }

    private void loadUI() {
        pmpList = (RecyclerView) mFragmentView.findViewById(R.id.pmp_list);

        pmpViewToggle = mFragmentView.findViewById(R.id.pmp_floating_header_view_toggle);
        pmpViewToggle.setOnClickListener(this);

        pmpViewFilter = mFragmentView.findViewById(R.id.pmp_floating_header_view_filter);
        pmpViewFilter.setOnClickListener(this);


        showPMP(pmpViewType);

        final String pmpResponse = getPMPResponse();
        JSONParsingHelper helper = new JSONParsingHelper(ProductMatrixVO.class, new JSONParsingHelper.IJSONParsingListener() {
            @Override
            public void onParseSuccess(IValueObject valueObject) {
                mProductMatrixVO = (ProductMatrixVO) valueObject;
                updateProductList();
            }

            @Override
            public void onParseFailure(Error ex) {
            }
        });
        helper.execute(pmpResponse);

    }

    private void showPMP(PMPVIEWTYPE viewType) {
        switch (viewType) {
            case LIST:
                mLayoutManager = new LinearLayoutManager(getActivity());
                pmpList.setPadding(10, 10, 10, 10);

                break;
            case GRID:
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                pmpList.setPadding(10, 10, 10, 10);
                break;
            case CARD:
                mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                pmpList.setPadding(80, 10, 80, 10);
                break;
        }
        mLayoutManager.requestSimpleAnimationsInNextLayout();

        pmpList.setLayoutManager(mLayoutManager);
        pmpList.setHasFixedSize(true);
        pmpViewType = viewType;
    }


    private String getPMPResponse() {
        AssetManager assetManager = getActivity().getAssets();
        InputStream input;
        String text = "";
        try {
            input = assetManager.open("pmpjson");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            text = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("TAG", "Text File: " + text);
        return text;
    }

    private void updateProductList() {
        mAdapter = new PMPListAdapter(getActivity(), mProductMatrixVO.getPayload().getProducts(), pmpViewType);
        mAdapter.setPmpItemListener(this);
        pmpList.setAdapter(mAdapter);
    }

}
