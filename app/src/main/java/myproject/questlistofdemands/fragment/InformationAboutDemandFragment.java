package myproject.questlistofdemands.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.api.RestHelper;
import myproject.questlistofdemands.model.Demand;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAboutDemandFragment extends Fragment {

    public static final String ID_KEY = "ID_KEY";
    private static final String DEMAND_KEY = "DEMAND_KEY";

    @BindView(R.id.search_bar_information_about_demand)
    FrameLayout mInfBar;
    @BindView(R.id.view_information_about_demand)
    LinearLayout mInformationAboutDemand;
    @BindView(R.id.view_error_information_about_demand)
    RelativeLayout mErrorView;
    @BindView(R.id.title_of_demand_for_information)
    TextView mTitleOfDemand;
    @BindView(R.id.title_information_about_demand)
    TextView mTitleInformationAboutDemand;
    @BindView(R.id.img_photo_information)
    CircleImageView mPhoto;
    @BindView(R.id.name_of_customer)
    TextView mNameOfCustomer;
    @BindView(R.id.surname_of_customer)
    TextView mSurnameOfCustomer;
    @BindView(R.id.appraisal)
    RatingBar mAppraisal;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.date_from_information)
    TextView mDateFrom;
    @BindView(R.id.date_to_information)
    TextView mDateTo;
    @BindView(R.id.container_for_time)
    RelativeLayout mContainerForTime;


    private int mIdDemand;
    private View mV;
    private Demand mDemand;


    public static InformationAboutDemandFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        InformationAboutDemandFragment fragment = new InformationAboutDemandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mV = inflater.inflate(R.layout.fragment_information_about_demand, null);
        ButterKnife.bind(this, mV);
        mIdDemand = getArguments().getInt(ID_KEY);
        if (savedInstanceState != null) {
            mDemand = savedInstanceState.getParcelable(DEMAND_KEY);
            if (mDemand != null) {
                initializeData();
            } else {
                showProgressBar();
                loadData();
            }
        } else {
            showProgressBar();
            loadData();
        }
        return mV;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(DEMAND_KEY, mDemand);
    }

    private void loadData() {
        RestHelper.getInterface().getDemandInformation(mIdDemand).enqueue(new Callback<Demand>() {
            @Override
            public void onResponse(@NonNull Call<Demand> call, Response<Demand> response) {
                mDemand = response.body();
                initializeData();
            }

            @Override
            public void onFailure(@NonNull Call<Demand> call, Throwable t) {
                showErrorView();
            }
        });
    }

    private void initializeData() {
        if (mDemand.getTitle() != null) {
            mTitleOfDemand.setVisibility(View.VISIBLE);
            mTitleOfDemand.setText(mDemand.getTitle());
        }else{
            mTitleOfDemand.setVisibility(View.GONE);
        }
        if (mDemand.getCreator().getPicture() != null) {
            if (mDemand.getCreator().getPicture().isIsUploaded()) {
                Picasso.with(mV.getContext())
                        .load("https://server.qest.cz:44302/api/v1/files/" + mDemand.getCreator().getPicture().getId() +
                                "/" + mDemand.getCreator().getPicture().getToken())
                        .into(mPhoto);
            }
        }
        if (mDemand.getCreator().getFirstName() != null) {
            mNameOfCustomer.setText(mDemand.getCreator().getFirstName());
        } else {
            mNameOfCustomer.setVisibility(View.GONE);
        }
        if (mDemand.getCreator().getLastName() != null) {
            mSurnameOfCustomer.setText(mDemand.getCreator().getLastName());
        } else {
            mSurnameOfCustomer.setVisibility(View.GONE);
        }
        mAppraisal.setRating((float) mDemand.getCustomerCompany().getRating());
        if (mDemand.getAddress().getLabel() != null) {
            mAddress.setText(mDemand.getAddress().getLabel());
        } else {
            mAddress.setVisibility(View.GONE);
        }
        if (mDemand.getInterval().getFromUtcTime() != null &&
                mDemand.getInterval().getToUtcTime() != null) {
            mDateFrom.setText(dateFormat(mDemand.getInterval().getFromUtcTime()));
            mDateTo.setText(dateFormat(mDemand.getInterval().getToUtcTime()));
        } else {
            mContainerForTime.setVisibility(View.GONE);
        }
        showInformationAboutFilm();
    }

    private String dateFormat(String date) {
        String date1 = date.substring(0, 10) + " " + date.substring(12, date.length());
        String date2 = date1.substring(0, date1.length() - 1);
        SimpleDateFormat output = new SimpleDateFormat(getString(R.string.date_format_for_information));
        SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_time_format));
        try {
            Date res = df.parse(date2);
            return output.format(res);
        } catch (ParseException e1) {
            return null;
        }
    }

    private void showErrorView() {
        mInfBar.setVisibility(View.GONE);
        mInformationAboutDemand.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    private void showInformationAboutFilm() {
        mInfBar.setVisibility(View.GONE);
        mInformationAboutDemand.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mInfBar.setVisibility(View.VISIBLE);
        mInformationAboutDemand.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }
}
