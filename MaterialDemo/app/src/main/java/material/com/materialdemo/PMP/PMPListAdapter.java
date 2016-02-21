package material.com.materialdemo.PMP;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import material.com.materialdemo.ProductMatrixVO;
import material.com.materialdemo.R;

/**
 */
public class PMPListAdapter extends RecyclerView.Adapter<PMPListAdapter.ViewHolder> {

    private final List<ProductMatrixVO.Payload.Product> mProductsList;

    private PMPFragment.PMPVIEWTYPE mPmpViewType;

    private PMPItemListener mPmpItemListener;

    private Activity mContext;

    public PMPListAdapter(Activity context, List<ProductMatrixVO.Payload.Product> productList, PMPFragment.PMPVIEWTYPE pmpViewType) {
        mProductsList = productList;
        mPmpViewType = pmpViewType;
        this.mContext = context;
    }

    public PMPItemListener getPmpItemListener() {
        return mPmpItemListener;
    }

    public void setPmpItemListener(PMPItemListener mPmpItemListener) {
        this.mPmpItemListener = mPmpItemListener;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public PMPItemView pmpItemView;

        public ViewHolder(View view) {
            super(view);
            pmpItemView = (PMPItemView) view.findViewById(R.id.item_pmp_view);
            if (mPmpViewType == PMPFragment.PMPVIEWTYPE.LIST) {
                pmpItemView.setOrientation(LinearLayout.HORIZONTAL);
            } else {
                pmpItemView.setOrientation(LinearLayout.VERTICAL);
            }
            pmpItemView.setPmpViewType(mPmpViewType);
        }
    }

    @Override
    public PMPListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pmp_card_view, parent, false);


        final ImageView image = (ImageView) view.findViewById(R.id.image);
        final TextView textview = (TextView) view.findViewById(R.id.title);
//        // set the view's size, margins, paddings and layout parameters
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickedImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
//                Intent intent = new Intent(mContext, PDPActivity.class);
//                String url = (String) ((NetworkImageView) view.findViewById(R.id.image)).getTag();
//                intent.putExtra("url", url);
//                intent.putExtra("title", (String) textview.getTag());
//
//                // create the transition animation - the images in the layouts
//                // of both activities are defined with android:transitionName="robot"
//                ActivityOptions options = ActivityOptions
//                        .makeSceneTransitionAnimation(mContext, image, "image");
//                // start the new activity
//                mContext.startActivity(intent, options.toBundle());
//            }
//        });
//        ImageView previewImage = (ImageView) view.findViewById(R.id.item_image_view);
//        previewImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PDPPreviewActivity.class);
//                mContext.startActivityForResult(intent, 0);
//            }
//        });
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PMPListAdapter.ViewHolder holder, int position) {
        ProductMatrixVO.Payload.Product product = mProductsList.get(position);
        holder.pmpItemView.populate(product);
        holder.pmpItemView.setPmpItemListener(getPmpItemListener());

    }


    @Override
    public int getItemCount() {
        if (mProductsList == null || mProductsList.isEmpty()) {
            return 5;
        }
        return mProductsList.size();
    }

}
