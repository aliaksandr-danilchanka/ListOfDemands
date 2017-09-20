package myproject.questlistofdemands.model;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public class Demand {

    private int id;
    private String creationUtcTime;
    private creatorBean creator;
    private customerCompanyBean customerCompany;
    private stateBean state;
    private addressBean address;
    private int categoryId;
    private String validTillUtcTime;
    private intervalBean interval;
    private String title;
    private int currencyId;
    private Object bestOffer;
    private int offerCount;

    public int getId() {
        return id;
    }

    public String getCreationUtcTime() {
        return creationUtcTime;
    }

    public creatorBean getCreator() {
        return creator;
    }

    public customerCompanyBean getCustomerCompany() {
        return customerCompany;
    }

    public stateBean getState() {
        return state;
    }

    public addressBean getAddress() {
        return address;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getValidTillUtcTime() {
        return validTillUtcTime;
    }

    public intervalBean getInterval() {
        return interval;
    }

    public String getTitle() {
        return title;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public Object getBestOffer() {
        return bestOffer;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationUtcTime(String creationUtcTime) {
        this.creationUtcTime = creationUtcTime;
    }

    public void setCreator(creatorBean creator) {
        this.creator = creator;
    }

    public void setCustomerCompany(customerCompanyBean customerCompany) {
        this.customerCompany = customerCompany;
    }

    public void setState(stateBean state) {
        this.state = state;
    }

    public void setAddress(addressBean address) {
        this.address = address;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setValidTillUtcTime(String validTillUtcTime) {
        this.validTillUtcTime = validTillUtcTime;
    }

    public void setInterval(intervalBean interval) {
        this.interval = interval;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setBestOffer(Object bestOffer) {
        this.bestOffer = bestOffer;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public static class creatorBean {

        private int id;
        private String firstName;
        private String lastName;
        private pictureBean picture;

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public pictureBean getPicture() {
            return picture;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setPicture(pictureBean picture) {
            this.picture = picture;
        }

        public static class pictureBean {

            private int id;
            private String token;
            private boolean isUploaded;

            public int getId() {
                return id;
            }

            public String getToken() {
                return token;
            }

            public boolean isIsUploaded() {
                return isUploaded;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public void setIsUploaded(boolean isUploaded) {
                this.isUploaded = isUploaded;
            }
        }
    }

    public static class customerCompanyBean {

        private int id;
        private Object name;
        private boolean isVerified;
        private boolean isHidden;
        private int rating;
        private Object logo;

        public int getId() {
            return id;
        }

        public Object getName() {
            return name;
        }

        public boolean isIsVerified() {
            return isVerified;
        }

        public boolean isIsHidden() {
            return isHidden;
        }

        public int getRating() {
            return rating;
        }

        public Object getLogo() {
            return logo;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public void setIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }
    }

    public static class stateBean {

        private String utcTime;
        private int value;

        public String getUtcTime() {
            return utcTime;
        }

        public int getValue() {
            return value;
        }

        public void setUtcTime(String utcTime) {
            this.utcTime = utcTime;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class addressBean {

        private int id;
        private String placeId;
        private String label;
        private String street;
        private String buildingNumber;
        private String city;
        private String countryCode;
        private gpsBean gps;

        public int getId() {
            return id;
        }

        public String getPlaceId() {
            return placeId;
        }

        public String getLabel() {
            return label;
        }

        public String getStreet() {
            return street;
        }

        public String getBuildingNumber() {
            return buildingNumber;
        }

        public String getCity() {
            return city;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public gpsBean getGps() {
            return gps;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setBuildingNumber(String buildingNumber) {
            this.buildingNumber = buildingNumber;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public void setGps(gpsBean gps) {
            this.gps = gps;
        }

        public static class gpsBean {

            private double latitude;
            private double longitude;

            public double getLatitude() {
                return latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }

    public static class intervalBean {

        private String fromUtcTime;
        private String toUtcTime;

        public String getFromUtcTime() {
            return fromUtcTime;
        }

        public String getToUtcTime() {
            return toUtcTime;
        }

        public void setFromUtcTime(String fromUtcTime) {
            this.fromUtcTime = fromUtcTime;
        }

        public void setToUtcTime(String toUtcTime) {
            this.toUtcTime = toUtcTime;
        }
    }
}
