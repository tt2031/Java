import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ResultsBean implements Serializable {
    private  List<Point> results = new ArrayList<Point>();

    public ResultsBean() {
    }

    public void addResult(Point p) {
        this.results.add(p);
    }

    public Object getResults() {
        return results;
    }

}
