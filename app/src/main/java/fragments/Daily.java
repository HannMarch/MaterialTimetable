package fragments;

/**
 * Created by shahria on 04-01-2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shahria.MaterialTimetable.MainActivity;
import com.shahria.MaterialTimetable.R;


import java.util.Calendar;

import adapter.PageAdapter;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by shahria on 04-01-2016.
 */
public class Daily extends Fragment implements MaterialTabListener {



    View view;

    private ViewPager viewPager;
    private MaterialTabHost tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.dailyview,container,false);


        MainActivity.fab.setVisibility(View.VISIBLE);



         tabLayout = (MaterialTabHost) view.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Monday").setTabListener(this));
        tabLayout.addTab(tabLayout.newTab().setText("Tuesday").setTabListener(this));

        tabLayout.addTab(tabLayout.newTab().setText("Wednesday").setTabListener(this));
        tabLayout.addTab(tabLayout.newTab().setText("Thursday").setTabListener(this));
        tabLayout.addTab(tabLayout.newTab().setText("Friday").setTabListener(this));





         viewPager = (ViewPager)view.findViewById(R.id.pager);
         PageAdapter adapter = new PageAdapter
                 (getChildFragmentManager(),7);
        viewPager.setAdapter(adapter);





        
        
        



        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user swipes the selected tab will change
                tabLayout.setSelectedNavigationItem(position);
            }
        });





   





        return view;
    }


    @Override
    public void onTabSelected(MaterialTab tab) {

            Log.e("ee", "ee");
        viewPager.setCurrentItem(tab.getPosition());
        tabLayout.setSelectedNavigationItem(tab.getPosition());
        

    }



    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

}
