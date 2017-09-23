package myproject.questlistofdemands.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.activity.InformationAboutDemandActivity;
import myproject.questlistofdemands.adapter.DemandAdapter;
import myproject.questlistofdemands.api.RestHelper;
import myproject.questlistofdemands.model.Demand;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static myproject.questlistofdemands.activity.InformationAboutDemandActivity.ID_DEMAND_KEY;

public class ListOfDemandsFragment extends Fragment {

    public static final String DEMAND_KEY = "DEMAND_KEY";
    private static final int COUNT_OF_DEMANDS = 10;
    private static final int THREAT_SHOT = 5;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_bar)
    FrameLayout mSearchBar;
    @BindView(R.id.view_error)
    RelativeLayout mViewError;
    @BindView(R.id.view_no_results)
    LinearLayout mViewNoResults;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<Demand> mDemands;
    private int mOffer = 0;
    private LinearLayoutManager mLinearLayoutManagerForAddScroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_of_demands, null);
        ButterKnife.bind(this, v);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mLinearLayoutManagerForAddScroll = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.setHasFixedSize(true);
        showRecyclerView();

        if (savedInstanceState != null) {
            mDemands = savedInstanceState.getParcelableArrayList(DEMAND_KEY);
            if (mDemands != null) {
                initializeAdapter();
            } else {
                showProgressBarView();
                loadData();
            }
        } else {
            showProgressBarView();
            loadData();
        }

        if (mDemands != null) {
            initializeAdapter();
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mLinearLayoutManagerForAddScroll.getItemCount() - THREAT_SHOT
                        == mLinearLayoutManagerForAddScroll.findLastVisibleItemPosition()) {
                    loadMoreData();
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mOffer = 0;
                loadData();
                onItemsLoadComplete();
            }
        });


        return v;
    }

    void onItemsLoadComplete() {
        Toast.makeText(getContext(), getString(R.string.files_refresh), Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DEMAND_KEY, mDemands);
    }

    private void loadData() {
        RestHelper.getInterface().getDemandList(mOffer, COUNT_OF_DEMANDS).enqueue(new Callback<ArrayList<Demand>>() {
            @Override
            public void onResponse(Call<ArrayList<Demand>> call, Response<ArrayList<Demand>> response) {
                if (mDemands == null) mDemands = new ArrayList<>();
                mDemands.clear();
                mDemands = response.body();
                if (mDemands != null) {
                    if (mDemands.isEmpty()) {
                        showNoResultsView();
                    } else {
                        initializeAdapter();
                        showRecyclerView();
                    }
                } else {
                    showNoResultsView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Demand>> call, Throwable t) {
                showErrorView();
            }
        });
    }

    private void loadMoreData() {
        mOffer += COUNT_OF_DEMANDS;
        RestHelper.getInterface().getDemandList(mOffer, COUNT_OF_DEMANDS).enqueue(new Callback<ArrayList<Demand>>() {
            @Override
            public void onResponse(Call<ArrayList<Demand>> call, Response<ArrayList<Demand>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i) != null) {
                            mDemands.add(response.body().get(i));
                        }
                    }
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Demand>> call, Throwable t) {
                showErrorView();
            }
        });

    }

    private void initializeAdapter() {
        DemandAdapter rvAdapter = new DemandAdapter(mDemands, getContext(), new DemandAdapter.OnDemandClickListener() {
            @Override
            public void onFilmClicked(Demand demand) {
                Intent intent = new Intent(getActivity(), InformationAboutDemandActivity.class);
                intent.putExtra(ID_DEMAND_KEY, demand.getId());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(rvAdapter);
    }

    private void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mSearchBar.setVisibility(View.GONE);
        mViewError.setVisibility(View.GONE);
        mViewNoResults.setVisibility(View.GONE);
    }

    private void showProgressBarView() {
        mRecyclerView.setVisibility(View.GONE);
        mSearchBar.setVisibility(View.VISIBLE);
        mViewError.setVisibility(View.GONE);
        mViewNoResults.setVisibility(View.GONE);
    }

    private void showErrorView() {
        mRecyclerView.setVisibility(View.GONE);
        mSearchBar.setVisibility(View.GONE);
        mViewError.setVisibility(View.VISIBLE);
        mViewNoResults.setVisibility(View.GONE);
    }

    private void showNoResultsView() {
        mRecyclerView.setVisibility(View.GONE);
        mSearchBar.setVisibility(View.GONE);
        mViewError.setVisibility(View.GONE);
        mViewNoResults.setVisibility(View.VISIBLE);
    }
}
