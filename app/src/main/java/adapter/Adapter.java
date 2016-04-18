package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shahria.MaterialTimetable.R;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

;import sql.get_set;

public class Adapter extends BaseExpandableListAdapter implements TimePickerDialog.OnTimeSetListener {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<get_set>> _listDataChild;

    public Adapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<get_set>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {




            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            if(isLastChild) {
                convertView = infalInflater.inflate(R.layout.add_new, null);


                TextView add=(TextView)convertView.findViewById(R.id.textView7);




                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {





                        if(_listDataChild.get(_listDataHeader.get(groupPosition)).size()<4) {
                            _listDataChild.get(_listDataHeader.get(groupPosition)).add(new get_set(1,"Lesson","Lesson","08:55 AM","9:55 AM","","","",""));
                            notifyDataSetChanged();
                        }

                        else

                        Toast.makeText(_context,"Can't Add More Then 3 lessons",Toast.LENGTH_LONG).show();



                    }
                });


            }
                else
            {
            convertView = infalInflater.inflate(R.layout.add_lessson_adapter, null);




           final      EditText lesson=(EditText)convertView.findViewById(R.id.lesson);

                ImageView close=(ImageView)convertView.findViewById(R.id.imageView4);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        _listDataChild.get(_listDataHeader.get(groupPosition)).remove(childPosition);
                        notifyDataSetChanged();

                    }
                });




                final EditText start=(EditText)convertView.findViewById(R.id.editText2);

                lesson.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getLesson());

                lesson.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {


                        _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).setLesson(s.toString());


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });



                start.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).setStarttime(s.toString());

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });






                start.setText((_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getStarttime()));





                final EditText end=(EditText)convertView.findViewById(R.id.editText3);

                end.setText((_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getEndtime()));

                end.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).setEndtime(s.toString());

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                start.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {



                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            Calendar now = Calendar.getInstance();


                            TimePickerDialog time = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                     @Override
                                                                                     public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

                                                                                         String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                                                                         String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                                                                         String secondString = second < 10 ? "0" + second : "" + second;

                                                                                         String AM_PM ;
                                                                                         if(hourOfDay < 12) {
                                                                                             AM_PM = "AM";

                                                                                         } else {
                                                                                             AM_PM = "PM";

                                                                                         }
                                                                                         String time = hourString + ":" + minuteString+" "+AM_PM;
                                                                                         start.setText(time);
                                                                                     }
                                                                                 }, now.get(Calendar.HOUR_OF_DAY),
                                    now.get(Calendar.MINUTE), true);



                            time.setTitle("StartTime");
                            time.show(((Activity) _context).getFragmentManager(), "Start Time");
                        }
                        return true;
                    }
                });





                end.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {


                        if (event.getAction() == MotionEvent.ACTION_DOWN)
                        {

                            Calendar now = Calendar.getInstance();


                            TimePickerDialog time = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                     @Override
                                                                                     public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {


                                                                                         String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                                                                         String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                                                                         String secondString = second < 10 ? "0" + second : "" + second;

                                                                                         String AM_PM ;
                                                                                         if(hourOfDay < 12) {
                                                                                             AM_PM = "AM";

                                                                                         } else {
                                                                                             AM_PM = "PM";

                                                                                         }
                                                                                         String time = hourString + ":" + minuteString+" "+AM_PM;
                                                                                         end.setText(time);
                                                                                     }
                                                                                 }, now.get(Calendar.HOUR_OF_DAY),
                                    now.get(Calendar.MINUTE), true);

                            time.setTitle("End Time");
                            time.show(((Activity) _context).getFragmentManager(), "End Time");
                        }
                        return true;
                    }
                });


        }



        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {






    }
}