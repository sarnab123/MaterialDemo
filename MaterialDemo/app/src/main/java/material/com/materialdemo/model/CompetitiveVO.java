package material.com.materialdemo.model;

import java.io.Serializable;
import java.util.List;

import material.com.materialdemo.IValueObject;

/**
 * Created by global on 1/6/16.
 */
public class CompetitiveVO implements IValueObject {

    String upc;

    List<CompetitorPrice> competitorPrices;

    public String getUpc()
    {
        return this.upc;
    }

    public List<CompetitorPrice> getCompetitorPrices()
    {
        return this.competitorPrices;
    }

    public void setCompetitorPrices(List<CompetitorPrice> prices)
    {
        this.competitorPrices = prices;
    }

    public class CompetitorPrice implements Serializable{
        String vendorCd;
        String vendorPrice;

        public String getVendorCd()
        {
            return this.vendorCd;
        }

        public String getVendorPrice()
        {
            return this.vendorPrice;
        }

        public void setVendorPrice(String vendorPrice)
        {
            this.vendorPrice = vendorPrice;
        }

        public void setVendorCd(String vendorCd)
        {
            this.vendorCd = vendorCd;
        }
    }
}
