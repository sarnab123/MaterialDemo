package material.com.materialdemo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Value model object of product matrix page.
 *
 */
public class ProductMatrixVO implements IValueObject {


    private Payload payload;



    private String offset;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    private String count;

    private String limit;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Expose
    private ResponseHeader responseHeaders;

    public ResponseHeader getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(ResponseHeader responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public class ResponseHeader implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 2L;

        @Expose
        private String Expires;

        public String getExpires() {
            return this.Expires;
        }

        public void setExpires(String expires) {
            this.Expires = expires;
        }

    }

    public static class Payload {

        /**
         *
         */
        private static final long serialVersionUID = 20L;

        private String searchTerm;

        private String autoCorrectedTerm;

        private List<AvailableTerm> availableTerms;

        private String legalDisclaimer;

        private List<Link> links;

        private List<ActiveDimension> activeDimensions;

        private List<Sort> sorts;

        private List<Dimension> dimensions;

        private List<Product> products;

        public class Link {

            private String link;

            private String rel;

            private String uri;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRel() {
                return rel;
            }

            public void setRel(String rel) {
                this.rel = rel;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

        }

        @Expose
        private ResponseHeader responseHeaders;

        public ResponseHeader getResponseHeaders() {
            return responseHeaders;
        }

        public void setResponseHeaders(ResponseHeader responseHeaders) {
            this.responseHeaders = responseHeaders;
        }

        public class ResponseHeader implements Serializable {

            /**
             *
             */
            private static final long serialVersionUID = 2L;

            @Expose
            private String Expires;

            public String getExpires() {
                return this.Expires;
            }

            public void setExpires(String expires) {
                this.Expires = expires;
            }

        }

        public class ActiveDimension implements Serializable {

            /**
             *
             */
            private static final long serialVersionUID = 41L;

            private String name;

            private String label;

            private int index;

            private List<ActiveDimensionValue> activeDimensionValues;

            public class ActiveDimensionValue implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 42L;

                private String name;

                private int index;

                private int productCount;

                private String ID;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public int getProductCount() {
                    return productCount;
                }

                public void setProductCount(int productCount) {
                    this.productCount = productCount;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String iD) {
                    ID = iD;
                }
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public List<ActiveDimensionValue> getActiveDimensionValues() {
                return activeDimensionValues;
            }

            public void setActiveDimensionValues(
                    List<ActiveDimensionValue> activeDimensionValues) {
                this.activeDimensionValues = activeDimensionValues;
            }

        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public List<ActiveDimension> getActiveDimensions() {
            return activeDimensions;
        }

        public void setActiveDimensions(List<ActiveDimension> activeDimensions) {
            this.activeDimensions = activeDimensions;
        }

        public List<Sort> getSorts() {
            return sorts;
        }

        public void setSorts(List<Sort> sorts) {
            this.sorts = sorts;
        }

        public List<Dimension> getDimensions() {
            return dimensions;
        }

        public void setDimensions(List<Dimension> dimensions) {
            this.dimensions = dimensions;
        }

        public String getSearchTerm() {
            return searchTerm;
        }

        public void setSearchTerm(String searchTerm) {
            this.searchTerm = searchTerm;
        }

        public String getAutoCorrectedTerm() {
            return autoCorrectedTerm;
        }

        public void setAutoCorrectedTerm(String autoCorrectedTerm) {
            this.autoCorrectedTerm = autoCorrectedTerm;
        }

        public List<AvailableTerm> getAvailableTerms() {
            return availableTerms;
        }

        public void setAvailableTerms(List<AvailableTerm> availableTerms) {
            this.availableTerms = availableTerms;
        }

        public String getLegalDisclaimer() {
            return legalDisclaimer;
        }

        public void setLegalDisclaimer(String legalDisclaimer) {
            this.legalDisclaimer = legalDisclaimer;
        }

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }

        public class Sort implements Serializable {

            private static final long serialVersionUID = 40L;

            private String name;

            private boolean active;

            private String ID;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isActive() {
                return active;
            }

            public void setActive(boolean active) {
                this.active = active;
            }

            public String getID() {
                return ID;
            }

            public void setID(String iD) {
                ID = iD;
            }
        }

        public class AvailableTerm implements Serializable {

        }

        public class Dimension implements Serializable {

            /**
             *
             */
            private static final long serialVersionUID = 43L;

            private String name;

            private String label;

            private int index;

            private List<DimensionValue> dimensionValues;

            public class DimensionValue implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 44L;

                private String name;

                private int productCount;

                private int index;

                private boolean active;

                private String ID;

                private boolean showChecked;

                public boolean isShowChecked() {
                    return showChecked;
                }

                public void setShowChecked(boolean showChecked) {
                    this.showChecked = showChecked;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getProductCount() {
                    return productCount;
                }

                public void setProductCount(int productCount) {
                    this.productCount = productCount;
                }

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public boolean isActive() {
                    return active;
                }

                public void setActive(boolean active) {
                    this.active = active;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String iD) {
                    ID = iD;
                }
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public List<DimensionValue> getDimensionValues() {
                return dimensionValues;
            }

            public void setDimensionValues(List<DimensionValue> dimensionValues) {
                this.dimensionValues = dimensionValues;
            }

        }

        public class Product {

            private String webID;

            private String productTitle;

            private Image image;

            private List<Prices> prices;

            private ProductRating rating;

            private List<SwatchImage> swatchImages;

            private int ratingCount;

            private List<String> valueAddedIcons;

            private List<ProductOffer> productOffers;

            public class Image implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 51L;

                private String url;

                private String height;

                private String width;

                private String altText;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getAltText() {
                    return altText;
                }

                public void setAltText(String altText) {
                    this.altText = altText;
                }
            }

            public class Prices implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 50L;

                private RegularPrice regularPrice;

                private SalePrice salePrice;

                private String clearancePrice;

                private String displayBegDateTime;

                private String displayEndDateTime;

                private String purchaseBegDateTime;

                private String purchaseEndDateTime;

                private String regularPriceType;

                private String promotion;

                private String statusCode;

                private String priceCode;

                private boolean isCurrentPrice;

                private String salePriceStatus;

                public class RegularPrice implements Serializable {
                    /**
                     *
                     */
                    private static final long serialVersionUID = 49L;

                    private float minPrice;

                    private float maxPrice;

                    public float getMinPrice() {
                        return minPrice;
                    }

                    public void setMinPrice(float minPrice) {
                        this.minPrice = minPrice;
                    }

                    public float getMaxPrice() {
                        return maxPrice;
                    }

                    public void setMaxPrice(float maxPrice) {
                        this.maxPrice = maxPrice;
                    }

                }

                public class SalePrice implements Serializable {
                    /**
                     *
                     */
                    private static final long serialVersionUID = 48L;

                    private float minPrice;

                    private float maxPrice;

                    public float getMinPrice() {
                        return minPrice;
                    }

                    public void setMinPrice(float minPrice) {
                        this.minPrice = minPrice;
                    }

                    public float getMaxPrice() {
                        return maxPrice;
                    }

                    public void setMaxPrice(float maxPrice) {
                        this.maxPrice = maxPrice;
                    }

                }

                public String getDisplayBegDateTime() {
                    return displayBegDateTime;
                }

                public void setDisplayBegDateTime(String displayBegDateTime) {
                    this.displayBegDateTime = displayBegDateTime;
                }

                public String getDisplayEndDateTime() {
                    return displayEndDateTime;
                }

                public void setDisplayEndDateTime(String displayEndDateTime) {
                    this.displayEndDateTime = displayEndDateTime;
                }

                public String getPurchaseBegDateTime() {
                    return purchaseBegDateTime;
                }

                public void setPurchaseBegDateTime(String purchaseBegDateTime) {
                    this.purchaseBegDateTime = purchaseBegDateTime;
                }

                public String getPurchaseEndDateTime() {
                    return purchaseEndDateTime;
                }

                public void setPurchaseEndDateTime(String purchaseEndDateTime) {
                    this.purchaseEndDateTime = purchaseEndDateTime;
                }

                public String getRegularPriceType() {
                    return regularPriceType;
                }

                public void setRegularPriceType(String regularPriceType) {
                    this.regularPriceType = regularPriceType;
                }

                public String getPromotion() {
                    return promotion;
                }

                public void setPromotion(String promotion) {
                    this.promotion = promotion;
                }

                public String getStatusCode() {
                    return statusCode;
                }

                public void setStatusCode(String statusCode) {
                    this.statusCode = statusCode;
                }

                public String getPriceCode() {
                    return priceCode;
                }

                public void setPriceCode(String priceCode) {
                    this.priceCode = priceCode;
                }

                public boolean isCurrentPrice() {
                    return isCurrentPrice;
                }

                public void setCurrentPrice(boolean isCurrentPrice) {
                    this.isCurrentPrice = isCurrentPrice;
                }

                public RegularPrice getRegularPrice() {
                    return regularPrice;
                }

                public void setRegularPrice(RegularPrice regularPrice) {
                    this.regularPrice = regularPrice;
                }

                public SalePrice getSalePrice() {
                    return salePrice;
                }

                public void setSalePrice(SalePrice salePrice) {
                    this.salePrice = salePrice;
                }

                public String getClearancePrice() {
                    return clearancePrice;
                }

                public void setClearancePrice(String clearancePrice) {
                    this.clearancePrice = clearancePrice;
                }

                public String getSalePriceStatus() {
                    return salePriceStatus;
                }

                public void setSalePriceStatus(String salePriceStatus) {
                    this.salePriceStatus = salePriceStatus;
                }

            }

            public class ProductRating {

                private float avgRating;

                private int count;

                public float getAvgRating() {
                    return avgRating;
                }

                public void setAvgRating(float avgRating) {
                    this.avgRating = avgRating;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }

            public class SwatchImage implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 46L;

                private String color;

                private String URL;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getURL() {
                    return URL;
                }

                public void setURL(String uRL) {
                    URL = uRL;
                }

            }

            public class ValueAddedIcon {

                private String valueAddedIcon;

                public String getValueAddedIcon() {
                    return valueAddedIcon;
                }

                public void setValueAddedIcon(String valueAddedIcon) {
                    this.valueAddedIcon = valueAddedIcon;
                }
            }

            public class ProductOffer implements Serializable {

                /**
                 *
                 */
                private static final long serialVersionUID = 45L;

                private String id;

                private String itemType;

                private String groupType;

                public String getID() {
                    return id;
                }

                public void setID(String id) {
                    this.id = id;
                }

                public String getItemType() {
                    return itemType;
                }

                public void setItemType(String itemType) {
                    this.itemType = itemType;
                }

                public String getGroupType() {
                    return groupType;
                }

                public void setGroupType(String groupType) {
                    this.groupType = groupType;
                }

            }

            public String getWebID() {
                return webID;
            }

            public void setWebID(String webID) {
                this.webID = webID;
            }

            public String getProductTitle() {
                return productTitle;
            }

            public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
            }

            public List<SwatchImage> getSwatchImages() {
                return swatchImages;
            }

            public void setSwatchImages(List<SwatchImage> swatchImages) {
                this.swatchImages = swatchImages;
            }

            public Image getImage() {
                return image;
            }

            public void setImage(Image image) {
                this.image = image;
            }

            public List<Prices> getPrices() {
                return prices;
            }

            public void setPrices(List<Prices> prices) {
                this.prices = prices;
            }

            public ProductRating getRating() {
                return rating;
            }

            public void setRating(ProductRating rating) {
                this.rating = rating;
            }

            public List<String> getValueAddedIcons() {
                return valueAddedIcons;
            }

            public void setValueAddedIcons(List<String> valueAddedIcons) {
                this.valueAddedIcons = valueAddedIcons;
            }

            public int getRatingCount() {
                return ratingCount;
            }

            public void setRatingCount(int ratingCount) {
                this.ratingCount = ratingCount;
            }

            public List<ProductOffer> getProductOffers() {
                return productOffers;
            }

            public void setProductOffers(List<ProductOffer> productOffers) {
                this.productOffers = productOffers;
            }
        }

    }
}