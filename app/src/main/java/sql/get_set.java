package sql;

/**
 * Created by shahria on 14-01-2016.
 */
public class get_set {

    int id;
    String subject, lesson, starttime, endtime, color, room, teacher,day;

    public int getId() {
        return id;
    }

    public get_set(int id, String subject, String lesson, String starttime, String endtime, String color, String room, String teacher,String day) {
        this.id = id;
        this.subject = subject;
        this.lesson = lesson;
        this.starttime = starttime;
        this.endtime = endtime;
        this.color = color;
        this.room = room;

        this.teacher = teacher;
        this.day=day;

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
