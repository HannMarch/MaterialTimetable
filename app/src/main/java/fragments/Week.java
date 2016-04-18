package fragments;

/**
 * Created by shahria on 04-01-2016.
 */

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shahria.MaterialTimetable.MainActivity;
import com.shahria.MaterialTimetable.R;


import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sql.DB;
import sql.get_set;
import util.CommonMethod;
import util.Entity;
import util.WeekSets;

/**
 * Created by shahria on 04-01-2016.
 */
public class Week extends Fragment {



    View view;

    private TextView textViewServiceApp;
    private TextView textViewWeekCalender;
    private TextView textViewPrevDate;
    private TextView textViewDate;
    private TextView textViewNextDate;
    private TextView textViewSun;
    private TextView textViewMon;
    private TextView textViewTue;
    private TextView textViewWed;
    private TextView textViewThu;
    private TextView textViewFri;
    private TextView textViewSat;
    private TextView textView12am;
    private TextView textView1am;
    private TextView textView2am;
    private TextView textView3am;
    private TextView textView4am;
    private TextView textView5am;
    private TextView textView6am;
    private TextView textView7am;
    private TextView textView8am;
    private TextView textView9am;
    private TextView textView10am;
    private TextView textView11am;
    private TextView textView12pm;
    private TextView textView1pm;
    private TextView textView2pm;
    private TextView textView3pm;
    private TextView textView4pm;
    private TextView textView5pm;
    private TextView textView6pm;
    private TextView textView7pm;
    private TextView textView8pm;
    private TextView textView9pm;
    private TextView textView10pm;
    private TextView textView11pm;

    // * Views

    private RelativeLayout relativeLayoutMonDay;
    private RelativeLayout relativeLayoutTueDay;
    private RelativeLayout relativeLayoutWedDay;
    private RelativeLayout relativeLayoutThuDay;
    private RelativeLayout relativeLayoutFriDay;


    // *IMges
    private ImageView buttonBack;
    private ImageView buttonHome;

    private Typeface typface;

    public String dateFormat, logInID;
    public String[] weekDays;
    public String[] NextPreWeekday;
    public String dateFormate;
    public String firstDayOfWeek;
    public String lastDayOfWeek;

    public static ArrayList<Entity> arrayListEntity = new ArrayList<Entity>();
    public static ArrayList<Entity> arrayListEButtonId = new ArrayList<Entity>();

    public int weekDaysCount = 0;
    public ArrayList<WeekSets> weekData;
    String tapMargin ;
    String buttonHight;
    private ArrayList<get_set> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.week,container,false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });



        textViewPrevDate = (TextView) view.findViewById(R.id.textViewPrevDate);
        textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        textViewNextDate = (TextView) view.findViewById(R.id.textViewNextDate);
        textViewSun = (TextView) view.findViewById(R.id.textViewSun);
        textViewMon = (TextView) view.findViewById(R.id.textViewMon);
        textViewTue = (TextView) view.findViewById(R.id.textViewTue);
        textViewWed = (TextView) view.findViewById(R.id.textViewWed);
        textViewThu = (TextView) view.findViewById(R.id.textViewThu);

        MainActivity.fab.setVisibility(View.VISIBLE);
        textView12am = (TextView) view.findViewById(R.id.textView12am);
        textView1am = (TextView) view.findViewById(R.id.textView1am);
        textView2am = (TextView) view.findViewById(R.id.textView2am);
        textView3am = (TextView) view.findViewById(R.id.textView3am);
        textView4am = (TextView) view.findViewById(R.id.textView4am);
        textView5am = (TextView) view.findViewById(R.id.textView5am);
        textView6am = (TextView) view.findViewById(R.id.textView6am);
        textView7am = (TextView) view.findViewById(R.id.textView7am);
        textView8am = (TextView) view.findViewById(R.id.textView8am);
        textView9am = (TextView) view.findViewById(R.id.textView9am);
        textView10am = (TextView) view.findViewById(R.id.textView10am);
        textView11am = (TextView) view.findViewById(R.id.textView11am);
        textView12pm = (TextView) view.findViewById(R.id.textView12pm);
        textView1pm = (TextView) view.findViewById(R.id.textView1pm);
        textView2pm = (TextView) view.findViewById(R.id.textView2pm);
        textView3pm = (TextView) view.findViewById(R.id.textView3pm);
        textView4pm = (TextView) view.findViewById(R.id.textView4pm);
        textView5pm = (TextView) view.findViewById(R.id.textView5pm);
        textView6pm = (TextView) view.findViewById(R.id.textView6pm);
        textView7pm = (TextView) view.findViewById(R.id.textView7pm);
        textView8pm = (TextView) view.findViewById(R.id.textView8pm);
        textView9pm = (TextView) view.findViewById(R.id.textView9pm);
        textView10pm = (TextView) view.findViewById(R.id.textView10pm);
        textView11pm = (TextView) view.findViewById(R.id.textView11pm);


        relativeLayoutMonDay = (RelativeLayout) view.findViewById(R.id.relativeLayoutSunday);
        relativeLayoutTueDay = (RelativeLayout) view.findViewById(R.id.relativeLayoutMonDay);
        relativeLayoutWedDay = (RelativeLayout) view.findViewById(R.id.relativeLayoutTueDay);
        relativeLayoutThuDay = (RelativeLayout) view.findViewById(R.id.relativeLayoutWedDay);
        relativeLayoutFriDay=(RelativeLayout) view.findViewById(R.id.relativeLayoutThuDay);


        NextPreWeekday = getWeekDay();
        firstDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[0]);
        lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
        textViewDate.setText(firstDayOfWeek + "-" + lastDayOfWeek + " "
                + CommonMethod.convertWeekDaysMonth(NextPreWeekday[6]));

        textViewSun.setText("Mon");
        textViewMon.setText("Tue");
        textViewTue.setText("Wed");
        textViewWed.setText("Thr");
        textViewThu.setText("Fri");

        try
        {
            new LoadViewsInToWeekView().execute("");
        } catch (Exception e)
        {
            Log.getStackTraceString(e);
        }




        return view;
    }



    public String[] getWeekDay() {

        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    @SuppressLint("SimpleDateFormat")
    public String[] getWeekDayNext() {

        weekDaysCount++;
        Calendar now1 = Calendar.getInstance();
        Calendar now = (Calendar) now1.clone();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    @SuppressLint("SimpleDateFormat")
    public String[] getWeekDayPrev() {

        weekDaysCount--;
        Calendar now1 = Calendar.getInstance();
        Calendar now = (Calendar) now1.clone();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    public Button getButtonToLayout(int higth, int marginTop,
                                    String buttonText, String jobID, int buttonID,String color) {

        @SuppressWarnings("deprecation")
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, higth);
        Button button = new Button(getActivity());
        button.setLayoutParams(params);
        button.setBackgroundColor(Integer.parseInt(color));
        button.setText(buttonText);
        button.setOnClickListener(buttonOnclckForAllWeekButton);
        button.setTextSize(9);
        button.setId(buttonID);
        params.setMargins(0, marginTop, 0, 0);
        arrayListEntity.add(getEntity(jobID, buttonText));

        return button;

    }

    public View.OnClickListener buttonOnclckForAllWeekButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;




            final int i=b.getId();


            final Dialog details=new Dialog(getActivity());

            details.requestWindowFeature(Window.FEATURE_NO_TITLE);
            details.setContentView(R.layout.lesson_detail);

            TextView time=(TextView)details.findViewById(R.id.time);
            TextView cls=(TextView)details.findViewById(R.id.clas);
            TextView teacher=(TextView)details.findViewById(R.id.teacher);
            TextView edit=(TextView)details.findViewById(R.id.edit);
            TextView subject=(TextView)details.findViewById(R.id.subject);
            TextView delete=(TextView)details.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DB db=new DB(getActivity());
                    db.deleteContact(data.get(i).getId());



                    details.dismiss();

                    Home frag = new Home();

                    Bundle b=new Bundle();
                    b.putString("lesson",data.get(i).getSubject());
                    frag.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();
                }
            });

            subject.setText(data.get(i).getSubject());
            subject.setBackgroundColor(Integer.parseInt(data.get(i).getColor()));



            time.setText(data.get(i).getDay() + " " + data.get(i).getStarttime() + "-" + data.get(i).getEndtime());

            teacher.setText("Teacher: "+data.get(i).getTeacher());
            cls.setText("Class: "+data.get(i).getLesson());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details.dismiss();
                    Add_New_Lesson frag = new Add_New_Lesson();

                    Bundle b=new Bundle();
                    b.putString("lesson",data.get(i).getSubject());
                    //// TODO: lesson or subject 
                    frag.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();
                }
            });
            TextView Close=(TextView)details.findViewById(R.id.close);
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details.dismiss();
                }
            });
            details.show();





        }
    };

    public static Entity getEntity(String jobID, String jobRefID) {
        Entity E = new Entity();
        E.JobIDForButton = jobID;
        E.JobRefID = jobRefID;
        return E;

    }

    public static Entity getButton(int layoutView, int buttonTag) {
        Entity E = new Entity();
        E.layoutView = layoutView;
        E.buttonTag = buttonTag;
        return E;

    }

    public static WeekSets getWeekValues(String weekDay, String jobId,
                                         String jobRefId, String tapMargina, String buttonHighta,String color) {
        WeekSets W = new WeekSets();
        W.day = weekDay;
        W.jobID = jobId;
        W.jobRefID = jobRefId;
        W.tapMargin = tapMargina;
        W.buttonHeight = buttonHighta;
        W.color=color;

        return W;
    }

    public String getWithAndHigthToButton(int startTime,int endtime) {

        int time;
        String size = "0";
        try {
            time = startTime;

            switch (time) {
                case 0:
                    size = "0";
                    break;
                case 1:
                    size = "60";

                    break;
                case 2:
                    size = "120";

                    break;
                case 3:
                    size = "180";

                    break;
                case 4:
                    size = "240";

                    break;
                case 5:
                    size = "300";

                    break;
                case 6:
                    size = "360";

                    break;
                case 7:
                    size = "420";

                    break;
                case 8:
                    size = "480";

                    break;
                case 9:
                    size = "540";

                    break;
                case 10:
                    size = "600";

                    break;
                case 11:
                    size = "660";

                    break;
                case 12:
                    size = "720";

                    break;
                case 13:
                    size = "780";

                    break;
                case 14:
                    size = "840";

                    break;
                case 15:
                    size = "900";

                    break;
                case 16:
                    size = "960";

                    break;
                case 17:
                    size = "1020";

                    break;
                case 18:
                    size = "1080";

                    break;
                case 19:
                    size = "1140";

                    break;
                case 20:
                    size = "1200";

                    break;
                case 21:
                    size = "1260";

                    break;
                case 22:
                    size = "1320";

                    break;
                case 23:
                    size = "1380";
                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }



      int result=  Integer.parseInt(size)+endtime;
        return String.valueOf(result);

    }

    public String getHightOfButton(int startTime, int endTime) {
        String hight = "0";
        try {
            int subHigth = endTime - startTime;

            hight = String.valueOf(subHigth * 60);

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return hight;

    }

    public class LoadViewsInToWeekView extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                weekData = new ArrayList<WeekSets>();





                DB db=new DB(getActivity());

                SharedPreferences shared=getActivity().getSharedPreferences("timetable",0);
                data=db.getAllLessons(shared.getInt("id",0 ));


                Log.e("data",data.size()+"");







                for(int i=0;i<data.size();i++) {


                    int day=0;

                    switch (data.get(i).getDay()) {
                        case "Monday":
                            day=0;
                            break;
                        case "Tuesday":
                            day=1;
                            break;

                        case "Wednesday":
                            day=2;
                            break;

                        case "Thrusday":

                            day=3;

                            break;

                        case "Friday":

                            day=4;

                            break;


                    }
                    Log.e("j", Integer.parseInt(data.get(i).getStarttime().substring(0, 2)) + "," + Integer.parseInt(data.get(i).getEndtime().substring(0, 2)));

                    tapMargin = getWithAndHigthToButton(Integer.parseInt(data.get(i).getStarttime().substring(0,2)),Integer.parseInt(data.get(i).getStarttime().substring(3,5)));
                    buttonHight = getHightOfButton(Integer.parseInt(data.get(i).getStarttime().substring(0,2)), Integer.parseInt(data.get(i).getEndtime().substring(0,2)));
                    weekData.add(getWeekValues(String.valueOf(day), "12", data.get(i).getLesson(),
                            tapMargin, buttonHight, data.get(i).getColor()));


                }







            } catch (Exception e) {

                Log.e("e",e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {

            try {

                WeekSets weekToDay;
                int length = weekData.size();
                Log.i("length===>", String.valueOf(length));

                if (length != 0) {
                    for (int k = 0; k < weekData.size(); k++) {
                        weekToDay = weekData.get(k);

                        int day = Integer.parseInt(weekToDay.day);
                        switch (day) {
                            case 0:

                                int MonDay1 = 200;
                                relativeLayoutMonDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHeight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, k, weekToDay.color));
                                arrayListEButtonId.add(getButton(0, MonDay1));

                                break;

                            case 1:
                                int MonDay = 200;
                                relativeLayoutTueDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHeight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID,k,weekToDay.color));
                                arrayListEButtonId.add(getButton(1, MonDay));
                                MonDay++;
                                break;
                            case 2:
                                int TueDay = 200;
                                relativeLayoutWedDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHeight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID,k,weekToDay.color));
                                arrayListEButtonId.add(getButton(2, TueDay));
                                TueDay++;
                                break;
                            case 3:
                                int WedDay = 200;
                                relativeLayoutThuDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHeight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID,k,weekToDay.color));
                                arrayListEButtonId.add(getButton(3, WedDay));
                                WedDay++;
                                break;
                            case 4:
                                int ThuDay = 200;
                                relativeLayoutFriDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHeight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID,k,weekToDay.color));
                                arrayListEButtonId.add(getButton(k, ThuDay));
                                ThuDay++;
                                break;

                            case 6:

                                break;

                            default:
                                break;
                        }

                    }

                }

            } catch (Exception e) {
                Log.getStackTraceString(e);
            }



        }

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {


        }

    }


}
