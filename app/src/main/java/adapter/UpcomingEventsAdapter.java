package adapter;

/**
 * Created by shahria on 05-01-2016.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahria.MaterialTimetable.R;

import java.util.ArrayList;
import java.util.List;

import sql.get_set;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.CustomViewHolder> {
    private List<String> feedItemList;
    private Context mContext;
    ArrayList<get_set> data;

    public UpcomingEventsAdapter(Context context, ArrayList<get_set> data) {


        this.data=data;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcoming_events_adapter, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {


        customViewHolder.textView.setText(data.get(i).getDay());

        customViewHolder.textView2.setText("Starts at "+data.get(i).getStarttime());
        customViewHolder.textView3.setText("Ends at"+data.get(i).getEndtime());
        customViewHolder.textview4.setText(data.get(i).getSubject());






    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView,textView2,textView3,textview4;

        public CustomViewHolder(View view) {
            super(view);

            this.textView = (TextView) view.findViewById(R.id.textView);
            this.textView2 = (TextView) view.findViewById(R.id.textView2);
            this.textView3= (TextView) view.findViewById(R.id.textView5);
            this.textview4 = (TextView) view.findViewById(R.id.textView6);
        }
    }



    @Override
    public int getItemCount() {
        return (data.size());
    }
}