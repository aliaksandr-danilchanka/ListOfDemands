package myproject.questlistofdemands.api;

import java.util.ArrayList;

import myproject.questlistofdemands.model.Demand;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DemandsInterface {
    @GET("demands")
    Call<ArrayList<Demand>> getDemandList(@Query("offset") int offset, @Query("count") int count);

    @GET("demands/{demandId}")
    Call<Demand> getDemandInformation(@Path("demandId") int demandId);
}
