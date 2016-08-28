import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean(name = "clockTimer")
@ViewScoped
public class ClockTimerBean {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String date = dateFormat.format(new Date());

    public String getDate() {
        return date;
    }

    public void curDate() {
        date = dateFormat.format(new Date());
    }
}