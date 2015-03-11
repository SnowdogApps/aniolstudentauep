package pl.snowdog.aniolstudentauep.bus;

/**
 * Created by bartek on 10/17/14.
 */
public class DateSetEvent {
    public static final String TAG = "DateSetEvent";
    private String date;
    private int fromOrTo;

    public DateSetEvent(int fromOrTo, String date) {
        this.date = date;
        this.fromOrTo = fromOrTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFromOrTo() {
        return fromOrTo;
    }

    public void setFromOrTo(int fromOrTo) {
        this.fromOrTo = fromOrTo;
    }


}
