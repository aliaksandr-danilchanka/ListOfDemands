package myproject.questlistofdemands.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.adapter.DemandAdapter;
import myproject.questlistofdemands.api.RestHelper;
import myproject.questlistofdemands.model.Demand;
import myproject.questlistofdemands.model.DemandListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public class ListOfDemandsFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<Demand> mDemands;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_of_demands, null);
        ButterKnife.bind(this, v);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        loadData();

        return v;
    }

//    private void loadData() {
//        RestHelper.getInterface().getDemandInformation(1).enqueue(new Callback<Demand>() {
//            @Override
//            public void onResponse(Call<Demand> call, Response<Demand> response) {
//                if (mDemands == null) mDemands = new ArrayList<>();
//                mDemands.clear();
//                mDemands.add(response.body());
//                initializeAdapter();
//            }
//
//            @Override
//            public void onFailure(Call<Demand> call, Throwable t) {
//            }
//        });
//    }

    private void loadData() {
        RestHelper.getInterface().getDemandList(4, 4).enqueue(new Callback<DemandListResponse>() {
            @Override
            public void onResponse(Call<DemandListResponse> call, Response<DemandListResponse> response) {
                if (mDemands == null) mDemands = new ArrayList<>();
                mDemands.clear();
                mDemands.addAll(response.body().getDemands());
                initializeAdapter();
            }

            @Override
            public void onFailure(Call<DemandListResponse> call, Throwable t) {
            }
        });
    }

    private void initializeAdapter() {
        DemandAdapter rvAdapter = new DemandAdapter(mDemands);
        mRecyclerView.setAdapter(rvAdapter);
    }
}
