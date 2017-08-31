package Model;

public class DateCollection {

    public String detail;
    public String time;
    public String title;
    public String place;
    public String note;

    public String[] eventDetail;

    public DateCollection(String de, String timee, String ti, String pl, String no){
        this.detail = de;
        this.time = timee;
        this.title = ti;
        this.place = pl;
        this.note = no;
        collectDetail();
    }

    public void collectDetail(){
        eventDetail = new String[5];
        eventDetail[0] = detail;
        eventDetail[1] = time;
        eventDetail[2] = title;
        eventDetail[3] = place;
        eventDetail[4] = note;
    }

    public String[] getCollectionDetail(){
        return eventDetail;
    }

}
