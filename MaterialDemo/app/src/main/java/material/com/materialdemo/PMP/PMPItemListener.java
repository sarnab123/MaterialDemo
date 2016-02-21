package material.com.materialdemo.PMP;

import android.widget.ImageView;

import material.com.materialdemo.ProductMatrixVO;

/**
 * Created on 7/22/2015.
 */
public interface PMPItemListener {
    void addItemToFavourite(String webID);

    void onItemClick(ProductMatrixVO.Payload.Product product, ImageView transistionImage);
}
