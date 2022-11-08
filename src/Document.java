import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "doc")
@XmlAccessorType(XmlAccessType.FIELD)


public class Document {
    private String rate;
    @JsonProperty("student")
    @XmlElement(name = "student")
    private List<Student> students;

    public Document() {
    }

    public Document(String rate, List<Student> students) {
        this.rate = rate;
        this.students = students;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Document{" +
                "rate='" + rate + '\'' +
                ", students=" + students +
                '}';
    }
}
