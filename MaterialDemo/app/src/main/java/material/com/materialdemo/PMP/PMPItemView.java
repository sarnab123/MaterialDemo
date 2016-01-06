package material.com.materialdemo.PMP;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import material.com.materialdemo.LoadImageTask;
import material.com.materialdemo.ProductMatrixVO;
import material.com.materialdemo.R;
import material.com.materialdemo.UtilityMethods;

/**
 * Created on 7/20/2015 by sanchit.gupta.
 */
public class PMPItemView extends LinearLayout {

    public NetworkImageView mImage;
    public TextView mTitle;
    public TextView mOriginalPrice;
    public TextView mSalePrice;
    public NetworkImageView mBogoImage;
    private ImageView mFavIcon;
    private PMPItemListener mPmpItemListener;

    private PMPFragment.PMPVIEWTYPE mPmpViewType;

    private RatingBar mRatingBar;
    private TextView mRatingCountTextView;

    private boolean mItemAddedToFav;

    LinearLayout rootView;

    private ProductMatrixVO.Payload.Product mProduct;

    public PMPItemView(Context context) {
        super(context);
        inti();
    }

    public PMPItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti();
    }

    public PMPItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

    private void inti() {
        inflate(getContext(), R.layout.view_pmp_item, this);
        // rootView = (LinearLayout) findViewById(R.id.item_pmp_view);
        mImage = (NetworkImageView) findViewById(R.id.item_image_view);
        mTitle = (TextView) findViewById(R.id.item_title);
        mOriginalPrice = (TextView) findViewById(R.id.item_original_price);
        mSalePrice = (TextView) findViewById(R.id.item_sale_price);
        mBogoImage = (NetworkImageView) findViewById(R.id.item_bogo_image);
        mFavIcon = (ImageView) findViewById(R.id.item_favorite_icon);
        mRatingBar = (RatingBar) findViewById(R.id.id_product_itemRatingBar);
        mRatingCountTextView = (TextView) findViewById(R.id.id_product_itemRatingCountTxt);
    }

    public void populate(final ProductMatrixVO.Payload.Product product) {
        this.mProduct = product;

        if (getPmpViewType() == PMPFragment.PMPVIEWTYPE.CARD) {
            mImage.setLayoutParams(new FrameLayout.LayoutParams((int) getResources().getDimension(R.dimen.pmp_item_image_width_large),
                    (int) getResources().getDimension(R.dimen.pmp_item_image_height_large)));
        } else if (getPmpViewType() == PMPFragment.PMPVIEWTYPE.GRID) {
            mImage.setLayoutParams(new FrameLayout.LayoutParams((int) getResources().getDimension(R.dimen.pmp_item_image_width_medium),
                    (int) getResources().getDimension(R.dimen.pmp_item_image_height_medium)));
        } else {
            mImage.setLayoutParams(new FrameLayout.LayoutParams((int) getResources().getDimension(R.dimen.pmp_item_image_width_small),
                    (int) getResources().getDimension(R.dimen.pmp_item_image_height_small)));
        }

        if (product.getImage() != null && product.getImage().getUrl() != null) {
            String updatedUrl = UtilityMethods.getUpdatedURL(product.getImage().getUrl(),
                    (int) getResources().getDimension(R.dimen.pmp_item_image_width_large),
                    (int) getResources().getDimension(R.dimen.pmp_item_image_height_large));
            LoadImageTask.getInstance(getContext()).loadImage(updatedUrl, mImage, 0, 0);
        }
        if (product.getProductTitle() != null) {
            mTitle.setText(product.getProductTitle());
        }
        if (product.getPrices() != null && product.getPrices().size() > 0) {
            if (product.getPrices().get(0).getRegularPrice() != null && product.getPrices().get(0).getRegularPrice().getMinPrice() != 0) {
                mOriginalPrice.setText("Orig. $" + String.valueOf(product.getPrices().get(0).getRegularPrice().getMinPrice()));
                mOriginalPrice.setVisibility(View.VISIBLE);
            } else {
                mOriginalPrice.setVisibility(View.GONE);
            }
            if (product.getPrices().get(0).getSalePrice() != null && product.getPrices().get(0).getSalePrice().getMinPrice() != 0) {
                mSalePrice.setText("SALE $" + String.valueOf(product.getPrices().get(0).getSalePrice().getMinPrice()));
                mSalePrice.setVisibility(View.VISIBLE);
                // Set Original price with strike through
                mOriginalPrice.setPaintFlags(mOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            } else {
                mSalePrice.setVisibility(View.GONE);
            }
        }

        mFavIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPmpItemListener != null) {
                    mPmpItemListener.addItemToFavourite(product.getWebID());
                }
                mFavIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_selected));
                mItemAddedToFav = true;
            }
        });
        if(!mItemAddedToFav){
            mFavIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_default));
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPmpItemListener != null) {
                    mPmpItemListener.onItemClick(product.getWebID());
                }
            }
        });

        if (product.getValueAddedIcons() != null && product.getValueAddedIcons().size() > 0) {
            String updatedUrl = UtilityMethods.getUpdatedURL("https:" + UtilityMethods.getValueAddedIconUrl(product.getValueAddedIcons().get(0)),
                    200,
                    50);
            mBogoImage.setVisibility(View.VISIBLE);
            LoadImageTask.getInstance(getContext()).loadImage(updatedUrl, mBogoImage, 0, 0);
        }

        // Product Rating
        ProductMatrixVO.Payload.Product.ProductRating productRating = product.getRating();
        if (productRating != null && productRating.getAvgRating() != 0) {
            mRatingBar.setStepSize(0.5f);
            mRatingBar.setRating(UtilityMethods.getKohlsFormatedRating(productRating.getAvgRating()));
            mRatingCountTextView.setText("(" + String.valueOf(productRating.getCount())
                    + ")");
        }
    }

    public void setPmpItemListener(PMPItemListener mPmpItemListener) {
        this.mPmpItemListener = mPmpItemListener;
    }

    public PMPFragment.PMPVIEWTYPE getPmpViewType() {
        return mPmpViewType;
    }

    public void setPmpViewType(PMPFragment.PMPVIEWTYPE mPmpViewType) {
        this.mPmpViewType = mPmpViewType;
    }
}
