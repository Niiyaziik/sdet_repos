import java.util.List;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> estimation;

    public Student(String name, String group, 
    int course, List<Integer> estimation){
        this.name = name;
        this.group = group;
        this.course = course;
        this.estimation = estimation;
    }

    public String getName(){
        return name;
    }

    public String getGroup(){
        return group;
    }

    public int getCourse(){
        return course;
    }

    public List<Integer> getEstimation(){
        return estimation;
    }

    public double Otchislenie(){
        if (estimation.isEmpty())
        return 0.0;
        double sum = 0;
        for (int estimat : estimation){
            sum += estimat;
        }
        return sum/estimation.size();
    }

    public void Perehod(){
        this.course++;
    }
}
