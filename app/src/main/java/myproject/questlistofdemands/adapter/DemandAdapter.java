package myproject.questlistofdemands.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import myproject.questlistofdemands.R;
import myproject.questlistofdemands.model.Demand;


public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.DemandViewHolder> {

    private OnDemandClickListener mOnDemandClickListener;
    private ArrayList<Demand> mDemands;
    private Context mContext;

    public DemandAdapter(ArrayList<Demand> demands, Context context, OnDemandClickListener onDemandClickListener) {
        this.mDemands = demands;
        this.mContext = context;
        this.mOnDemandClickListener = onDemandClickListener;
    }

    @Override
    public DemandViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_demand, viewGroup, false);
        DemandViewHolder pvh = new DemandViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DemandViewHolder demandViewHolder, final int i) {
        demandViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDemandClickListener.onFilmClicked(mDemands.get(i));
            }
        });
        if (mDemands.get(i).getCreator().getPicture() != null) {
            if (mDemands.get(i).getCreator().getPicture().isIsUploaded()) {
                Picasso.with(demandViewHolder.cv.getContext())
                        .load("https://server.qest.cz:44302/api/v1/files/" + mDemands.get(i).getCreator().getPicture().getId() +
                                "/" + mDemands.get(i).getCreator().getPicture().getToken())
                        .into(demandViewHolder.photoProfile);
            }
        }
        demandViewHolder.dateOfDemand.setText(dateFormat(mDemands.get(i).getCreationUtcTime()));
        demandViewHolder.titleOfDemand.setText(mDemands.get(i).getTitle());
        if (mDemands.get(i).getAddress().getCity() != null) {
            demandViewHolder.cityOfDemand.setText(mDemands.get(i).getAddress().getCity()
                    + mContext.getString(R.string.comma));
        }
        if (mDemands.get(i).getAddress().getZipCode() != null) {
            demandViewHolder.postcodeOdDemand.setText(mDemands.get(i).getAddress().getZipCode()
                    + mContext.getString(R.string.comma));
        }
        if (mDemands.get(i).getAddress().getCountryCode() != null) {
            demandViewHolder.codeOfTheCountry.setText(mDemands.get(i).getAddress().getCountryCode());
        }
        if (dateFormat(mDemands.get(i).getInterval().getFromUtcTime()) != null &&
                dateFormat(mDemands.get(i).getInterval().getToUtcTime()) != null) {
            demandViewHolder.dateFrom.setText(dateFormat(mDemands.get(i).getInterval().getFromUtcTime()));
            demandViewHolder.dateTo.setText(dateFormat(mDemands.get(i).getInterval().getToUtcTime()));
        } else {
            demandViewHolder.dash.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getBestOffer() == null) {
            demandViewHolder.containerForPrice.setVisibility(View.GONE);
        } else {
            demandViewHolder.priceOfDemand.setText(String
                    .format("%s", (int) Math.round((Double) mDemands.get(i).getBestOffer())));
        }
        if (daysOfEnd(dateFormat(mDemands.get(i).getValidTillUtcTime())) != null) {
            demandViewHolder.daysOfEnd.setText(daysOfEnd(dateFormat(mDemands.get(i).getValidTillUtcTime())).get(0));
            demandViewHolder.hoursOfEnd.setText(daysOfEnd(dateFormat(mDemands.get(i).getValidTillUtcTime())).get(1));
            demandViewHolder.timeView.setVisibility(View.VISIBLE);
            demandViewHolder.timeViewEmpty.setVisibility(View.GONE);
        } else {
            demandViewHolder.timeView.setVisibility(View.GONE);
            demandViewHolder.timeViewEmpty.setVisibility(View.VISIBLE);
        }
        if (mDemands.get(i).getOfferCount() == 0) {
            demandViewHolder.offers.setVisibility(View.GONE);
            demandViewHolder.offer.setVisibility(View.GONE);
        } else if (mDemands.get(i).getOfferCount() == 1) {
            demandViewHolder.offers.setVisibility(View.GONE);
            demandViewHolder.offer.setVisibility(View.VISIBLE);
            demandViewHolder.quantityOfDemands.setText(String.format("%s", mDemands.get(i).getOfferCount()));
        } else {
            demandViewHolder.offers.setVisibility(View.VISIBLE);
            demandViewHolder.offer.setVisibility(View.GONE);
            demandViewHolder.quantityOfDemands.setText(String.format("%s", mDemands.get(i).getOfferCount()));
        }
    }

    @Override
    public int getItemCount() {
        return mDemands.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private ArrayList<String> daysOfEnd(String date) {
        ArrayList<String> result = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat(mContext.getString(R.string.date_format));
        Date nowDay = new Date();
        Date dateOfEnd;
        try {
            dateOfEnd = df.parse(date);
        } catch (ParseException e1) {
            return null;
        }
        if (nowDay.before(dateOfEnd)) {
            long interval = dateOfEnd.getTime() - nowDay.getTime();
            result.add(String.format("%s", (int) Math.floor(interval / 1000 / 60 / 60 / 24)));
            result.add(String.format("%s",
                    (int) Math.floor((((double) interval / 1000 / 60 / 60 / 24) - (int) Math.floor(interval / 1000 / 60 / 60 / 24)) * 24)));
            return result;
        } else {
            return null;
        }
    }

    private String dateFormat(String date) {
        String date1 = date.substring(0, 10) + " " + date.substring(12, date.length());
        String date2 = date1.substring(0, date1.length() - 1);
        SimpleDateFormat output = new SimpleDateFormat(mContext.getString(R.string.date_format));
        SimpleDateFormat df = new SimpleDateFormat(mContext.getString(R.string.date_time_format));
        try {
            Date res = df.parse(date2);
            return output.format(res);
        } catch (ParseException e1) {
            return null;
        }
    }

    public interface OnDemandClickListener {
        void onFilmClicked(Demand demand);
    }

    static class DemandViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        LinearLayout containerForPrice;
        LinearLayout timeView;
        LinearLayout timeViewEmpty;
        TextView dateOfDemand;
        TextView dash;
        CircleImageView photoProfile;
        TextView titleOfDemand;
        TextView cityOfDemand;
        TextView postcodeOdDemand;
        TextView codeOfTheCountry;
        TextView dateFrom;
        TextView dateTo;
        TextView priceOfDemand;
        TextView quantityOfDemands;
        TextView daysOfEnd;
        TextView hoursOfEnd;
        TextView offer;
        TextView offers;


        DemandViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            ButterKnife.bind(this, cv);
            containerForPrice = itemView.findViewById(R.id.container_for_price);
            timeView = itemView.findViewById(R.id.view_time);
            dash = itemView.findViewById(R.id.dash);
            timeViewEmpty = itemView.findViewById(R.id.view_is_empty);
            dateOfDemand = itemView.findViewById(R.id.date_of_demand);
            photoProfile = itemView.findViewById(R.id.img_photo);
            titleOfDemand = itemView.findViewById(R.id.title_of_demand);
            cityOfDemand = itemView.findViewById(R.id.city_of_demand);
            postcodeOdDemand = itemView.findViewById(R.id.postcode_of_demand);
            codeOfTheCountry = itemView.findViewById(R.id.code_of_the_country);
            dateFrom = itemView.findViewById(R.id.date_from);
            dateTo = itemView.findViewById(R.id.date_to);
            priceOfDemand = itemView.findViewById(R.id.price_of_demand);
            quantityOfDemands = itemView.findViewById(R.id.quantity_of_demands);
            daysOfEnd = itemView.findViewById(R.id.day_of_end);
            hoursOfEnd = itemView.findViewById(R.id.hours_of_end);
            offer = itemView.findViewById(R.id.offer);
            offers = itemView.findViewById(R.id.offers);
        }
    }
}
