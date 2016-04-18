package adapter;

/**
 * Created by shahria on 05-01-2016.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.shahria.MaterialTimetable.MainActivity;
import com.shahria.MaterialTimetable.R;

import java.util.ArrayList;
import java.util.List;

import fragments.Add_New_Lesson;
import fragments.Home;
import sql.DB;
import sql.get_set;

public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.CustomViewHolder> {
    private List<String> feedItemList;
    private Context mContext;

    ArrayList<get_set> data;
  int i;
    public DayViewAdapter(Context context, ArrayList<get_set> data) {

        this.data=data;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_lesson_adapter, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT


        int color1 = Integer.parseInt(data.get(i).getColor());

       TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(data.get(i).getSubject().charAt(0)),color1);

        customViewHolder.imageView.setImageDrawable(drawable);
        customViewHolder.subject.setText(data.get(i).getSubject());
        customViewHolder.lesson.setText(data.get(i).getLesson());
        customViewHolder.time.setText(data.get(i).getStarttime()+" - "+data.get(i).getEndtime());







    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView subject;
        protected TextView  time;
        protected TextView lesson;

        public CustomViewHolder(View view) {
            super(view);



            view.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                              i=getAdapterPosition();


                            final Dialog details=new Dialog(mContext);

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

                                    DB db = new DB(mContext);
                                    db.deleteContact(data.get(i).getId());


                                    details.dismiss();

                                    Home frag = new Home();

                                    Bundle b = new Bundle();
                                    b.putString("lesson", data.get(i).getSubject());
                                    frag.setArguments(b);

                                    if (mContext instanceof MainActivity) {
                                        MainActivity feeds = (MainActivity) mContext;
                                        feeds.getSupportFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();
                                    }
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
                                    frag.setArguments(b);

                                    if (mContext instanceof MainActivity) {
                                        MainActivity feeds = (MainActivity) mContext;
                                        feeds.getSupportFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();
                                    }
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
                    }
            );






            this.imageView= (ImageView) view.findViewById(R.id.profile_image);

            this.subject=(TextView)view.findViewById(R.id.subject);
            this.lesson=(TextView)view.findViewById(R.id.lesson);
            this.time=(TextView)view.findViewById(R.id.time);

        }
    }



    @Override
    public int getItemCount() {
        return (data.size());
    }
}