package myproject.questlistofdemands.api;

import myproject.questlistofdemands.model.Demand;
import myproject.questlistofdemands.model.DemandListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public interface DemandsInterface {
    @GET("demands")
    Call<DemandListResponse> getDemandList(@Query("offset") int offset, @Query("count") int count);

    @GET("demands/{demandId}")
    Call<Demand> getDemandInformation(@Path("demandId") int demandId);
}
