package material.com.materialdemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 */
public class PMPListAdapter extends RecyclerView.Adapter<PMPListAdapter.ViewHolder> {

    private final List<ProductMatrixVO.Payload.Product> mProductsList;
    private Activity mContext;
    private String mSelUrl;

    private static final int TYPE_PRODUCT = 0;
    private static final int TYPE_PROMOTION = 1;
    private static final int TYPE_VALUE_ADDED = 2;

    public static Bitmap clickedImage = null;

    public PMPListAdapter(Activity context, List<ProductMatrixVO.Payload.Product> productList) {
        mContext = context;
        mProductsList = productList;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public NetworkImageView mImage;
        public TextView mTitle;
        public TextView mDescription;

        public ViewHolder(View view) {
            super(view);
            mImage = (NetworkImageView) view.findViewById(R.id.image);
            mTitle = (TextView) view.findViewById(R.id.title);
            mDescription = (TextView) view.findViewById(R.id.description);
        }
    }

    @Override
    public PMPListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_PROMOTION)
        {
            // create a new view
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pmp_cards_promotion, parent, false);
            final ImageView image = (ImageView) view.findViewById(R.id.image);
            final TextView textview = (TextView) view.findViewById(R.id.title);
            // set the view's size, margins, paddings and layout parameters
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    Intent intent = new Intent(mContext, PDPActivity.class);
                    String url = (String) ((NetworkImageView) view.findViewById(R.id.image)).getTag();
                    intent.putExtra("url", url);
                    intent.putExtra("title", (String) textview.getTag());

                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(mContext, image, "image");
                    // start the new activity
                    mContext.startActivity(intent, options.toBundle());
                }
            });
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        else if(viewType == TYPE_VALUE_ADDED)
        {
            // create a new view
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pmp_cards_valueadded, parent, false);
            final ImageView image = (ImageView) view.findViewById(R.id.image);
            final TextView textview = (TextView) view.findViewById(R.id.title);
            // set the view's size, margins, paddings and layout parameters
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    Intent intent = new Intent(mContext, PDPActivity.class);
                    String url = (String) ((NetworkImageView) view.findViewById(R.id.image)).getTag();
                    intent.putExtra("url", url);
                    intent.putExtra("title", (String) textview.getTag());

                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(mContext, image, "image");
                    // start the new activity
                    mContext.startActivity(intent, options.toBundle());
                }
            });
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        else
        {

            // create a new view
            final View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pmp_cards, parent, false);
            final ImageView image = (ImageView) view.findViewById(R.id.image);
            final TextView textview = (TextView) view.findViewById(R.id.title);
            // set the view's size, margins, paddings and layout parameters
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    Intent intent = new Intent(mContext, PDPActivity.class);
                    String url = (String) ((NetworkImageView) view.findViewById(R.id.image)).getTag();
                    intent.putExtra("url", url);
                    intent.putExtra("title", (String) textview.getTag());

                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(mContext, image, "image");
                    // start the new activity
                    mContext.startActivity(intent, options.toBundle());
                }
            });
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;

        }

    }

    @Override
    public void onBindViewHolder(PMPListAdapter.ViewHolder holder, int position) {
        ProductMatrixVO.Payload.Product product = mProductsList.get(position);

        String updatedUrl = UtilityMethods.getUpdatedURL(product.getImage().getUrl(), 250, 250);
        holder.mImage.setTag(updatedUrl);
        LoadImageTask.getInstance(mContext).loadImage(updatedUrl, holder.mImage, 0, 0);
        holder.mTitle.setText(product.getProductTitle());
        holder.mTitle.setTag(product.getProductTitle());
    }

    @Override
    public int getItemViewType(int position) {
        ProductMatrixVO.Payload.Product data = mProductsList.get(position);
        if (data != null && data.getPrices() != null && data.getPrices().size() > 0 && data.getPrices().get(0).getPromotion() != null) {
            return TYPE_PROMOTION;
        }
        else if(data != null && data.getValueAddedIcons() != null && data.getValueAddedIcons().size() > 0)
        {
            return TYPE_VALUE_ADDED;
        }
        else{
            return TYPE_PRODUCT;
        }
    }

    @Override
    public int getItemCount() {
        if (mProductsList == null || mProductsList.isEmpty()) {
            return 5;
        }
        return mProductsList.size();
    }
}
