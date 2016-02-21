package material.com.materialdemo.Utils;

import material.com.materialdemo.ProductMatrixVO;

/**
 * Created by global on 1/6/16.
 */
public class GetCompetitivePriceUtil {

    IProductListener listener;



    public GetCompetitivePriceUtil(ProductMatrixVO mProductData, IProductListener listener)
    {
        this.listener = listener;
        updateCompetitiveData(mProductData);
    }

    private void updateCompetitiveData(ProductMatrixVO mProductData) {

//        for(final ProductMatrixVO.Payload.Product mProduct:mProductData.getPayload().getProducts())
//        {
////            if(!TextUtils.isEmpty(mProduct.getUpcCode()))
////            {
////                String compURL = "http://skotekal-compprice.rhcloud.com/competitorprices/price/"+
////                        mProduct.getUpcCode()+"?category="+mProduct.getCategory();
////                RestHelper restHelper = new RestHelper(compURL, new IRestListener() {
////                    @Override
////                    public void onSuccess(CompetitiveVO mCompetitiveVO) {
////                        if(null != mCompetitiveVO)
////                        {
////                            mProduct.setCompetitiveVO(mCompetitiveVO);
////                        }
////                    }
////                });
//                restHelper.performTask();
//            }
//        }
//        listener.onProductSuccess(mProductData);

    }


}
