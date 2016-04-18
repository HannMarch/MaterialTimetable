package adapter;

/**
 * Created by shahria on 04-01-2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragments.TabFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {


                TabFragment tab1 = new TabFragment();
        Bundle b=new Bundle();
        b.putInt("day",position);
        tab1.setArguments(b);



        return  tab1;


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}