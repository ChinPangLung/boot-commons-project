package jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {


    @Test
    public void test() {
        List<String> proNames = Arrays.asList(new String[]{"NI", "Hao", "LamdbA"});
        System.out.println(proNames.toString());

        List<String> collect = proNames.stream().map((name) -> name.toLowerCase()).collect(Collectors.toList());
        System.out.println("=================");
        System.out.println("处理后的proNames"+proNames.toString());
        System.out.println(collect.toString());

        List<Object> proNames1 = Arrays.asList(new String[]{"NI", "Hao", "LamdbA", null});
        int size = proNames1.stream().filter(name -> name != null).collect(Collectors.toList()).size();
        System.out.println(size);

    }

}
