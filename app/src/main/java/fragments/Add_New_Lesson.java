package fragments;

/**
 * Created by shahria on 04-01-2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shahria.MaterialTimetable.MainActivity;
import com.shahria.MaterialTimetable.R;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adapter.Adapter;
import sql.DB;
import sql.get_set;

public class Add_New_Lesson extends Fragment {


    EditText teacher,room;

    public static EditText subject;

    ExpandableListView monday;

    LinearLayout color;

    ArrayList<get_set> monday_data;

    Adapter monday_Adapter;

    String colors="";


    View view;
    private ArrayList<String> listDataHeader;

    HashMap<String, List<get_set>> listDataChild;
    private DB db;
    private ArrayList<get_set> tuesday;
    private ArrayList<get_set> wed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.add_lesson, container, false);

        subject =(EditText)view.findViewById(R.id.subject);

        teacher =(EditText)view.findViewById(R.id.teacher);
        room=(EditText)view.findViewById(R.id.classroom);

        MainActivity.fab.setVisibility(View.GONE);


        monday_data=new ArrayList<get_set>();
        setHasOptionsMenu(true);

         db=new DB(getActivity());

color=(LinearLayout)view.findViewById(R.id.color_layout);
        final ColorPicker cp = new ColorPicker(getActivity(),0,0,0);

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.show();

                Button okColor = (Button)cp.findViewById(R.id.okColorButton);
                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        color.setBackgroundColor(cp.getColor());

                       colors= cp.getColor()+"";


                        cp.dismiss();
                    }
                });
            }
        });



        prepareListData();





        monday_Adapter=new Adapter(getActivity(), listDataHeader, listDataChild);





        monday= (ExpandableListView) view.findViewById(R.id.lvExp);


        monday.setAdapter(monday_Adapter);










        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:




                if(subject.getText().toString().isEmpty()||room.getText().toString().isEmpty() |teacher.getText().toString().isEmpty())
                {

                    Toast.makeText(getActivity(),"Please Fill All Fields",Toast.LENGTH_LONG).show();

                }
                else
                if(colors.equals(""))
                {

                    Toast.makeText(getActivity(),"Please Select The Colors",Toast.LENGTH_LONG).show();

                }



                else {


                    boolean overlap = false;
                    SharedPreferences shared = getActivity().getSharedPreferences("timetable", 0);
                    Iterator it = listDataChild.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        ArrayList<get_set> data = (ArrayList<get_set>) pair.getValue();


                        if (data.size() > 1) {


                            for (int i = 0; i < data.size() - 1; i++) {


                                if (getArguments() != null) {

                                    if (!db.getLessons(String.valueOf(pair.getKey()), shared.getInt("id", 0), data.get(i).getStarttime(), data.get(i).getEndtime(),data.get(i).getId()))


                                        db.updateLesson(data.get(i).getId(), shared.getInt("id", 0), subject.getText().toString(), data.get(i).getLesson(), data.get(i).getStarttime(), data.get(i).getEndtime(), colors, room.getText().toString(), teacher.getText().toString(), (String) pair.getKey());

                                    else
                                        overlap = true;
                                } else {
                                    if (!db.getLessons(String.valueOf(pair.getKey()), shared.getInt("id", 0), data.get(i).getStarttime(), data.get(i).getEndtime()))

                                        db.insertLesson(shared.getInt("id", 0), subject.getText().toString(), data.get(i).getLesson(), data.get(i).getStarttime(), data.get(i).getEndtime(), colors, room.getText().toString(), teacher.getText().toString(), (String) pair.getKey());
                                    else
                                        overlap = true;

                                }


                            }


                        }
                        it.remove(); // avoids a ConcurrentModificationException


                    }


                    if (!overlap) {

                        Home home = new Home();

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame, home).commit();
                    }

                    else
                        Toast.makeText(getActivity(),"Already Existing Lesson: Check Timeframe",Toast.LENGTH_LONG).show();
                }

                // Do Activity menu item stuff here
                return true;
            case R.id.action_close:

                Home home=new Home();

                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,home).commit();


                return true;
            default:
                break;
        }

        return false;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add_new_lesson, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }





    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<get_set>>();

        // Adding child data
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");
        listDataHeader.add("Thrusday");
        listDataHeader.add("Friday");
        // Adding child data
        List<get_set> monday= new ArrayList<get_set>();




        tuesday = new ArrayList<get_set>();




         wed = new ArrayList<get_set>();

        List<get_set> thr = new ArrayList<get_set>();

        List<get_set> fri = new ArrayList<get_set>();


        if(getArguments()!=null)
        {

            String subjects=getArguments().getString("lesson");

            Log.e("subject",subjects);
            SharedPreferences shared=getActivity().getSharedPreferences("timetable", 0);

            DB db=new DB(getActivity());

            ArrayList<get_set> data=db.getSubject(subjects,shared.getInt("id",0));

           Log.e("data",data.size()+"");
            for(int i=0;i<data.size();i++)
            {


               subject.setText(data.get(i).getSubject());
                room.setText(data.get(i).getRoom());
                teacher.setText(data.get(i).getTeacher());

                color.setBackgroundColor(Integer.parseInt(data.get(i).getColor()));

                colors= data.get(i).getColor();






                switch (data.get(i).getDay()) {
                    case "Monday":
                        monday.add(data.get(i));
                        break;
                    case "Tuesday":

                        tuesday.add(data.get(i));
                        break;

                    case "Wednesday":
                        wed.add(data.get(i));
                        break;

                    case "Thrusday":

                        thr.add(wed.get(i));

                        break;

                    case "Friday":

                        fri.add(data.get(i));

                        break;


                }



            }




        }


        else

        {



        }

        monday.add(new get_set(1,"","Lesson","08:55 AM","09:55 AM","","","",""));
        tuesday.add(new get_set(1,"","Lesson","08:55 AM","09:55 AM","","","",""));
        wed.add(new get_set(1,"","Lesson","08:55 AM","09:55 AM","","","",""));
        thr.add(new get_set(1,"","Lesson","08:55 AM","09:55 AM","","","",""));
        fri.add(new get_set(1,"","Lesson","08:55 AM","09:55 AM","","","",""));

        listDataChild.put(listDataHeader.get(0), monday);
        listDataChild.put(listDataHeader.get(1),tuesday);
        listDataChild.put(listDataHeader.get(2), wed);
        listDataChild.put(listDataHeader.get(3), thr);
        listDataChild.put(listDataHeader.get(4), fri);
    }
}