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

import java.util.ArrayList;

import adapter.DayViewAdapter;
import sql.DB;
import sql.get_set;

public class TabFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    DayViewAdapter adapter;

    String today;

    ArrayList<get_set> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        view=inflater.inflate(R.layout.tabfragment1, container, false);

        DB db=new DB(getActivity());

        MainActivity.fab.setVisibility(View.VISIBLE);

        int day=getArguments().getInt("day");


        switch (day)
        {


            case 0:
                today="Monday";
                break;

            case 1:

                today="Tuesday";
                break;

            case 2:

                today="Wednesday";
                break;

            case 4:

                today="Thrusday";
                break;

            case 5:

                today="Friday";
                break;
        }

        SharedPreferences shared=getActivity().getSharedPreferences("timetable",0);
        data=db.getLessons(today,shared.getInt("id",0));


        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new DayViewAdapter(getActivity(),data);
        mRecyclerView.setAdapter(adapter);
        
        return view;
    }
}