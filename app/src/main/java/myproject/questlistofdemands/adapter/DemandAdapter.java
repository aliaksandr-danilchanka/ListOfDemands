package myproject.questlistofdemands.adapter;

import android.content.Context;
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

import butterknife.BindView;
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
        if (mDemands.get(i).getCreationUtcTime() != null) {
            demandViewHolder.dateOfDemand.setVisibility(View.VISIBLE);
            demandViewHolder.dateOfDemand.setText(dateFormat(mDemands.get(i).getCreationUtcTime()));
        } else {
            demandViewHolder.dateOfDemand.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getTitle() != null) {
            demandViewHolder.titleOfDemand.setVisibility(View.VISIBLE);
            demandViewHolder.titleOfDemand.setText(mDemands.get(i).getTitle());
        } else {
            demandViewHolder.titleOfDemand.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getAddress().getCity() != null) {
            demandViewHolder.cityOfDemand.setVisibility(View.VISIBLE);
            demandViewHolder.cityOfDemand.setText(mDemands.get(i).getAddress().getCity()
                    + mContext.getString(R.string.comma));
        } else {
            demandViewHolder.cityOfDemand.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getAddress().getZipCode() != null) {
            demandViewHolder.postcodeOdDemand.setVisibility(View.VISIBLE);
            demandViewHolder.postcodeOdDemand.setText(mDemands.get(i).getAddress().getZipCode()
                    + mContext.getString(R.string.comma));
        }else{
            demandViewHolder.postcodeOdDemand.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getAddress().getCountryCode() != null) {
            demandViewHolder.codeOfTheCountry.setVisibility(View.VISIBLE);
            demandViewHolder.codeOfTheCountry.setText(mDemands.get(i).getAddress().getCountryCode());
        }else{
            demandViewHolder.codeOfTheCountry.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getInterval().getFromUtcTime() != null &&
                mDemands.get(i).getInterval().getToUtcTime() != null) {
            demandViewHolder.dateFrom.setVisibility(View.VISIBLE);
            demandViewHolder.dateTo.setVisibility(View.VISIBLE);
            demandViewHolder.dash.setVisibility(View.VISIBLE);
            demandViewHolder.dateFrom.setText(dateFormat(mDemands.get(i).getInterval().getFromUtcTime()));
            demandViewHolder.dateTo.setText(dateFormat(mDemands.get(i).getInterval().getToUtcTime()));
        } else {
            demandViewHolder.dateFrom.setVisibility(View.GONE);
            demandViewHolder.dateTo.setVisibility(View.GONE);
            demandViewHolder.dash.setVisibility(View.GONE);
        }
        if (mDemands.get(i).getBestOffer() == null) {
            demandViewHolder.containerForPrice.setVisibility(View.GONE);
        } else {
            demandViewHolder.containerForPrice.setVisibility(View.VISIBLE);
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
            demandViewHolder.quantityOfDemands.setVisibility(View.GONE);
        } else if (mDemands.get(i).getOfferCount() == 1) {
            demandViewHolder.offers.setVisibility(View.GONE);
            demandViewHolder.offer.setVisibility(View.VISIBLE);
            demandViewHolder.quantityOfDemands.setVisibility(View.VISIBLE);
            demandViewHolder.quantityOfDemands.setText(String.format("%s", mDemands.get(i).getOfferCount()));
        } else {
            demandViewHolder.offers.setVisibility(View.VISIBLE);
            demandViewHolder.offer.setVisibility(View.GONE);
            demandViewHolder.quantityOfDemands.setVisibility(View.VISIBLE);
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

        LinearLayout cv;
        @BindView(R.id.container_for_price)
        LinearLayout containerForPrice;
        @BindView(R.id.view_time)
        LinearLayout timeView;
        @BindView(R.id.view_is_empty)
        LinearLayout timeViewEmpty;
        @BindView(R.id.date_of_demand)
        TextView dateOfDemand;
        @BindView(R.id.dash)
        TextView dash;
        @BindView(R.id.img_photo)
        CircleImageView photoProfile;
        @BindView(R.id.title_of_demand)
        TextView titleOfDemand;
        @BindView(R.id.city_of_demand)
        TextView cityOfDemand;
        @BindView(R.id.postcode_of_demand)
        TextView postcodeOdDemand;
        @BindView(R.id.code_of_the_country)
        TextView codeOfTheCountry;
        @BindView(R.id.date_from)
        TextView dateFrom;
        @BindView(R.id.date_to)
        TextView dateTo;
        @BindView(R.id.price_of_demand)
        TextView priceOfDemand;
        @BindView(R.id.quantity_of_demands)
        TextView quantityOfDemands;
        @BindView(R.id.day_of_end)
        TextView daysOfEnd;
        @BindView(R.id.hours_of_end)
        TextView hoursOfEnd;
        @BindView(R.id.offer)
        TextView offer;
        @BindView(R.id.offers)
        TextView offers;


        DemandViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            ButterKnife.bind(this, cv);
        }
    }
}
