package com.shahria.MaterialTimetable;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import fragments.Add_New_Lesson;
import fragments.Daily;
import fragments.Home;
import fragments.Week;
import sql.DB;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private ArrayList<String> timetable;

    SharedPreferences shared;

    public static FloatingActionButton fab;
int timetable_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shared=getSharedPreferences("timetable",0);

        timetable_id=shared.getInt("id", 0);



    fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Add_New_Lesson new_lesson = new Add_New_Lesson();


                fm.beginTransaction().replace(R.id.frame, new_lesson).commit();

            }
        });

  final DB db=new DB(getApplicationContext());

        db.exportDB();

        db.insertTimetable(1, "Default TimeTable");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);


         timetable=db.getAllContacts();

        timetable.add("Add New TimeTable");

        ArrayAdapter<String>  adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,timetable);

        final Spinner timetables=(Spinner)headerView.findViewById(R.id.spiiner);

        timetables.setAdapter(adapter);

        timetables.setSelection(timetable_id);


        timetables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(position==timetable.size()-1)
                {


                    final Dialog details=new Dialog(MainActivity.this);

                    details.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    details.setContentView(R.layout.add_timetable);

                    final EditText name=(EditText)details.findViewById(R.id.text);

                    Button submit=(Button)details.findViewById(R.id.submit);

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            db.insertTimetable(timetable.size(), name.getText().toString());

                           timetable=db.getAllContacts();

                            timetable.add("Add New TimeTable");

                            ArrayAdapter<String>  adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,timetable);



                            timetables.setAdapter(adapter);
                            details.dismiss();

                        }
                    });




                    details.show();

                }

                else

                {

                   SharedPreferences.Editor edit=shared.edit();

                    edit.putInt("id",position);

                    edit.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









 fm=getSupportFragmentManager();


        Home home=new Home();


        fm.beginTransaction().replace(R.id.frame,home).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {

            Home home=new Home();


            fm.beginTransaction().replace(R.id.frame,home).commit();


        }


        if (id == R.id.nav_camera) {

            Week week= new Week();

            fm.beginTransaction().replace(R.id.frame,week).commit();

        } else if (id == R.id.nav_gallery) {

            Daily daily= new Daily();

            fm.beginTransaction().replace(R.id.frame,daily).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
