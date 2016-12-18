import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by kraken on 09.10.16.
 */

public class Student implements Comparable<Student> {

    int age;
    String name;
    int departament;
    private Comparator<Student> comparator = byAge;

    final static Comparator<Student> byAge = (a, b) -> {
        return a.age > b.age ? 1 : a == b ? 0 : -1;
    };
    final static Comparator<Student> byDep = (a, b) -> {
        return a.departament > b.departament ? 1 : a == b ? 0 : -1;
    };
    final static Comparator<Student> byName = (a, b) -> {
        return a.name.equals(b.name) ? 1 : a.equals(b) ? 0 : -1;
    };

    public Student(int age, String name, int departament) {
        this.age = age;
        this.name = name;
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", departament=" + departament +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        return this.comparator.compare(this, student);
    }
}


class Sorter {

    Comparator<Student> comparator;

    public void setComparator(Comparator<Student> t) {
        this.comparator = t;
    }

    public static <T> T swap(T... a) {
        return a[0];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Sorter sorter = new Sorter();
        Scanner s =new Scanner(new FileInputStream("input.txt"));
        Student[] list = new Student[s.nextInt()];
        for (int i = 0; i<list.length ; i++) {
            int age=s.nextInt();
            String name=s.next();
            int dep=s.nextInt();
            list[i]=new Student(age,name,dep);
        }
        test(list,sorter);


    }


    public  static  void  test(Student[] list,Sorter sorter){

        System.out.println("buble sort by age");
        Student[] list1=list.clone();
        sorter.setComparator(Student.byAge);
        sorter.bublesort(list1,0,list.length);
        System.out.println(Arrays.toString(list1));

        System.out.println("buble sort by name");
        Student[] list2=list.clone();
        sorter.setComparator(Student.byName);
        sorter.bublesort(list1,0,list.length);
        System.out.println(Arrays.toString(list1));


        System.out.println("buble sort by dep");
        Student[] list3=list.clone();
        sorter.setComparator(Student.byDep);
        sorter.bublesort(list1,0,list.length);
        System.out.println(Arrays.toString(list1));


        System.out.println("---------------------------------------------------");

        System.out.println("buble sort by age");
        Student[] list4=list.clone();
        sorter.setComparator(Student.byAge);
        sorter.qsort(list1,0,list.length-1);
        System.out.println(Arrays.toString(list1));

        System.out.println("buble sort by name");
        Student[] list5=list.clone();
        sorter.setComparator(Student.byName);
        sorter.qsort(list1,0,list.length-1);
        System.out.println(Arrays.toString(list1));


        System.out.println("buble sort by dep");
        Student[] list6=list.clone();
        sorter.setComparator(Student.byDep);
        sorter.qsort(list1,0,list.length-1);
        System.out.println(Arrays.toString(list1));

    }

    public   void bublesort(Student[] arr, int a, int b) {
        for (int i = a; i < b; i++) {
            for (int j = a; j < b; j++) {
                if (this.comparator.compare(arr[j],arr[i])==1) arr[i] = swap(arr[j], arr[j] = arr[i]);;
            }
        }

    }


    public   void qsort(Student[] arr, int a, int b) {
        if (a < b) {
            int i = a, j = b;
            Student x = arr[(i + j) / 2];

            do {
                while (this.comparator.compare(arr[i],x) < 0) i++;
                while (this.comparator.compare(x,arr[j]) < 0) j--;

                if (i <= j) {
                    Student tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }

            } while (i <= j);

            qsort(arr, a, j);
            qsort(arr, i, b);
        }
    }
}
