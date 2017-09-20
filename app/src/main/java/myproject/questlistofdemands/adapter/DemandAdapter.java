package myproject.questlistofdemands.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.model.Demand;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.DemandViewHolder>  {

    ArrayList<Demand> mDemands;

    public DemandAdapter(ArrayList<Demand> demands) {
        this.mDemands = demands;
    }

    @Override
    public DemandViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_demand, viewGroup, false);
        DemandViewHolder pvh = new DemandViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DemandViewHolder demandViewHolder, final int i) {
        Picasso.with(demandViewHolder.cv.getContext())
                .load("https://server.qest.cz:44302/api/v1/files/" + mDemands.get(i).getCreator().getPicture().getId() +
                "/" + mDemands.get(i).getCreator().getPicture().getToken())
                .into(demandViewHolder.poster);
        demandViewHolder.title.setText(mDemands.get(i).getCreator().getFirstName());
        demandViewHolder.originalLanguage.setText(mDemands.get(i).getCreator().getLastName());
    }

    @Override
    public int getItemCount() {
        return mDemands.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DemandViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView poster;
        TextView title;
        TextView originalLanguage;
        TextView releaseDate;
        TextView overview;

        DemandViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            ButterKnife.bind(this, cv);
            poster = itemView.findViewById(R.id.img_poster);
            title = itemView.findViewById(R.id.title);
            originalLanguage = itemView.findViewById(R.id.txt_original_language);
            releaseDate = itemView.findViewById(R.id.txt_release_date);
            overview = itemView.findViewById(R.id.txt_overview);
        }
    }
}
