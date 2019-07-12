package temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * parallelStream
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-12 13:55
 */
public class parallelStream {

    public static List<Integer> buildIntRange() {
        List<Integer> numbers = new ArrayList<>(5);
        for (int i = 0; i < 60000; i++)
            numbers.add(i);
        return Collections.unmodifiableList(numbers);
    }
    public static void main(String[] args) {
        List<Integer> source = buildIntRange();

        // 传统方式的遍历
        long start = System.currentTimeMillis();
        for (int i = 0; i < source.size(); i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("传统方式 : " + (System.currentTimeMillis() - start) + "ms");

        // 单管道stream
        start = System.currentTimeMillis();
        source.stream().forEach(r -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("stream : " + (System.currentTimeMillis() - start) + "ms");

        // 多管道parallelStream
        start = System.currentTimeMillis();
        source.parallelStream().forEach(r -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("parallelStream : " + (System.currentTimeMillis() - start) + "ms");

    }
}