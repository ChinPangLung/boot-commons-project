package jdk8.stream;

import lombok.extern.java.Log;
import org.junit.Test;
import org.mockito.internal.matchers.ArrayEquals;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-30 09:39
 */
@Log
public class CollectorsTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("jack", "bob", "alice", "mark");
        List<String> duplicateList = Arrays.asList("jack", "jack", "alice", "mark");

        List<String> arrayList = list.stream().collect(Collectors.toList());
        List<String> linkedList = list.stream().collect(Collectors.toCollection(LinkedList::new));
        Set<String> set = duplicateList.stream().collect(Collectors.toSet());

        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
        for (Map.Entry<String, Integer> next : map.entrySet()) {
            log.info(String.format("key:{%s} ---- value:{%s}", next.getKey(), next.getValue()));
        }

        System.out.println(duplicateList);
        System.out.println(new TreeSet<Object>(duplicateList));
        List<String> collectAndThenResult = duplicateList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(TreeSet::new), ArrayList::new));
        log.info(String.format("{%s}", collectAndThenResult));
    }

    @Test
    public void test01() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setDeptno(111);
        employee.setEmpno(222L);
        employee.setEname("long");
        employee.setSalary(9000);
        Employee employee1 = new Employee();
        employee1.setDeptno(222);
        employee1.setEmpno(222L);
        employee1.setEname("lung");
        employee1.setSalary(8000);
        employees.add(employee);
        employees.add(employee1);

        employees = employees.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::getEmpno))), ArrayList::new));
        System.out.println(employees);
    }


}