package jdk8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    //记住：Stream API中的方法并不会影响原始集合

    private List<Employee> employees;

    @Before
    public void inti() {
        Employee e1 = new Employee(7369L, "SMITH", 800, 20);
        Employee e2 = new Employee(7499L, "ALLEN", 1600, 30);
        Employee e3 = new Employee(7521L, "WARD", 1250, 30);
        Employee e4 = new Employee(7782L, "CLARK", 2450, 10);
        Employee e5 = new Employee(7876L, "ADAMS", 1100, 20);

        employees = Arrays.asList(e1, e2, e3, e4, e5);
    }


    @Test
    public void test() {
        List<String> proNames = Arrays.asList(new String[]{"NI", "Hao", "LamdbA"});
        System.out.println(proNames.toString());

        List<String> collect = proNames.stream().map((name) -> name.toLowerCase()).collect(Collectors.toList());
        System.out.println("=================");
        System.out.println("处理后的proNames" + proNames.toString());
        System.out.println(collect.toString());

        List<Object> proNames1 = Arrays.asList(new String[]{"NI", "Hao", "LamdbA", null});
        int size = proNames1.stream().filter(name -> name != null).collect(Collectors.toList()).size();
        System.out.println(size);

    }

    @Test
    public void foreach() {
        employees.forEach(System.out::println);
    }

    @Test
    public void map() {
        List<String> collect = employees.stream().map(Employee::getEname).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void maptoInt() {
        int sum = employees.stream().mapToInt(Employee::getSalary).sum();
        System.out.println(sum);
    }

    @Test
    public void filter() {
        List<Employee> collect = employees.stream().filter(employee -> employee.getSalary() > 1500).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void sorted() {
        System.out.println("==========before=======");
        employees.forEach(System.out::println);
        List<Employee> collect = employees.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());
        System.out.println("==========after=========");
        collect.forEach(System.out::println);
        List<Employee> reversed = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println("========reversed=========");
        reversed.forEach(System.out::println);
        System.out.println("=======original List==========");
        employees.forEach(System.out::println);

    }


}
