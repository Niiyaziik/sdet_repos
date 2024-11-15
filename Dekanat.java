import java.util.ArrayList;
import java.util.List;

public class Dekanat {
    List<Student> students;
    
    public Dekanat(){
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void Session(){
        for(int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            if(student.Otchislenie() < 3.0){
                students.remove(i);
                i--;
            }
            else{
                student.Perehod();
            }
        }
    }

    public void printStudents(List<Student> students,
     int course){
        System.out.println("Студенты на курсе " + course + ":");
        for (Student student : students){
            if(student.getCourse() == course)
            System.out.println(student.getName());
        }
    }
}
