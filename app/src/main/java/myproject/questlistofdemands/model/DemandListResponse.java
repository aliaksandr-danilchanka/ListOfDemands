package myproject.questlistofdemands.model;

import java.util.List;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public class DemandListResponse {
    private List<Demand> mDemands;

    public List<Demand> getDemands() {
        return mDemands;
    }

    public void setDemands(List<Demand> demands) {
        this.mDemands = demands;
    }
}
