import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dekanat dekanat = new Dekanat();

        List<Integer> grades1 = List.of(4, 5, 3);
        List<Integer> grades2 = List.of(2, 3, 1);
        List<Integer> grades3 = List.of(3, 4, 5);

        dekanat.addStudent(new Student("Niyaz", "A1", 1, grades1));
        dekanat.addStudent(new Student("Mihail", "A2", 1, grades2));
        dekanat.addStudent(new Student("Artem", "A1", 1, grades3));
        dekanat.addStudent(new Student("Makar", "A1", 0, grades3));

        dekanat.Session();
        dekanat.printStudents(dekanat.students, 2);
        dekanat.printStudents(dekanat.students,1);
    }
}
