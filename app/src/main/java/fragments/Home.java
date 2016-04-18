package fragments;

/**
 * Created by shahria on 04-01-2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shahria.MaterialTimetable.MainActivity;
import com.shahria.MaterialTimetable.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import adapter.UpcomingEventsAdapter;
import sql.DB;
import sql.get_set;

public class Home extends Fragment {


    View view;
    UpcomingEventsAdapter adapter;
    private RecyclerView mRecyclerView;

    DB db;

    ArrayList<get_set> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.tabfragment, container, false);


        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();


        String today=new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());




        db=new DB(getActivity());
        SharedPreferences shared=getActivity().getSharedPreferences("timetable",0);
       data=db.getLessons(today,shared.getInt("id",0));


        MainActivity.fab.setVisibility(View.VISIBLE);





        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new UpcomingEventsAdapter(getActivity(),data);
        mRecyclerView.setAdapter(adapter);




        return view;
    }
}