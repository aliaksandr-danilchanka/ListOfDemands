package myproject.questlistofdemands.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Demand implements Parcelable {

    public static final Creator<Demand> CREATOR = new Creator<Demand>() {
        @Override
        public Demand createFromParcel(Parcel in) {
            return new Demand(in);
        }

        @Override
        public Demand[] newArray(int size) {
            return new Demand[size];
        }
    };
    private int id;
    private String creationUtcTime;
    private CreatorBean creator;
    private CustomerCompanyBean customerCompany;
    private StateBean state;
    private AddressBean address;
    private int categoryId;
    private String validTillUtcTime;
    private IntervalBean interval;
    private String title;
    private int currencyId;
    private Object bestOffer;
    private int offerCount;

    protected Demand(Parcel in) {
        id = in.readInt();
        creationUtcTime = in.readString();
        categoryId = in.readInt();
        validTillUtcTime = in.readString();
        title = in.readString();
        currencyId = in.readInt();
        offerCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(creationUtcTime);
        parcel.writeInt(categoryId);
        parcel.writeString(validTillUtcTime);
        parcel.writeString(title);
        parcel.writeInt(currencyId);
        parcel.writeInt(offerCount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationUtcTime() {
        return creationUtcTime;
    }

    public void setCreationUtcTime(String creationUtcTime) {
        this.creationUtcTime = creationUtcTime;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public CustomerCompanyBean getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(CustomerCompanyBean customerCompany) {
        this.customerCompany = customerCompany;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getValidTillUtcTime() {
        return validTillUtcTime;
    }

    public void setValidTillUtcTime(String validTillUtcTime) {
        this.validTillUtcTime = validTillUtcTime;
    }

    public IntervalBean getInterval() {
        return interval;
    }

    public void setInterval(IntervalBean interval) {
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public Object getBestOffer() {
        return bestOffer;
    }

    public void setBestOffer(Object bestOffer) {
        this.bestOffer = bestOffer;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public static class CreatorBean {

        private int id;
        private String firstName;
        private String lastName;
        private PictureBean picture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public PictureBean getPicture() {
            return picture;
        }

        public void setPicture(PictureBean picture) {
            this.picture = picture;
        }

        public static class PictureBean {

            private int id;
            private String token;
            private boolean isUploaded;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public boolean isIsUploaded() {
                return isUploaded;
            }

            public void setIsUploaded(boolean isUploaded) {
                this.isUploaded = isUploaded;
            }
        }
    }

    public static class CustomerCompanyBean {

        private int id;
        private String name;
        private boolean isVerified;
        private boolean isHidden;
        private double rating;
        private Object logo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsVerified() {
            return isVerified;
        }

        public void setIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
        }

        public boolean isIsHidden() {
            return isHidden;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }
    }

    public static class StateBean {

        private String utcTime;
        private int value;

        public String getUtcTime() {
            return utcTime;
        }

        public void setUtcTime(String utcTime) {
            this.utcTime = utcTime;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class AddressBean {

        private int id;
        private String placeId;
        private String label;
        private String street;
        private String buildingNumber;
        private String city;
        private String zipCode;
        private String countryCode;
        private GpsBean gps;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getBuildingNumber() {
            return buildingNumber;
        }

        public void setBuildingNumber(String buildingNumber) {
            this.buildingNumber = buildingNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public GpsBean getGps() {
            return gps;
        }

        public void setGps(GpsBean gps) {
            this.gps = gps;
        }

        public static class GpsBean {

            private double latitude;
            private double longitude;

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }

    public static class IntervalBean {

        private String fromUtcTime;
        private String toUtcTime;

        public String getFromUtcTime() {
            return fromUtcTime;
        }

        public void setFromUtcTime(String fromUtcTime) {
            this.fromUtcTime = fromUtcTime;
        }

        public String getToUtcTime() {
            return toUtcTime;
        }

        public void setToUtcTime(String toUtcTime) {
            this.toUtcTime = toUtcTime;
        }
    }
}
