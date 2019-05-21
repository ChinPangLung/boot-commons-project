package jdk8;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    @org.junit.Test
    public void TestLamdbaAndStream() {
        List<Man> mans = new ArrayList<>();
        mans.add(new Man("002", "李四", Arrays.asList(new Card("招商银行", "6225800002"), new Card("建设银行", "6227035248"))));
        mans.add(new Man("003", "王五", Arrays.asList(new Card("建设银行", "6227056547"), new Card("中国银行", "6013832547"), new Card("民生银行", "4074058542"))));
        mans.add(new Man("004", "赵六", Arrays.asList(new Card("工商银行", "9558832458"), new Card("工商银行", "9558832547"), new Card("建设银行", "6227032578"))));
        mans.add(new Man("005", "孙七", Arrays.asList(new Card("中国银行", "6013825847"), new Card("农业银行", "6228836547"), new Card("招商银行", "6225014582"))));
        mans.add(new Man("006", "张三", Arrays.asList(new Card("工商银行", "9558832587"), new Card("交通银行", "6222814578"), new Card("工商银行", "9558865427"))));
        mans.add(new Man("007", "张三", Arrays.asList(new Card("招商银行", "1158832587"), new Card("交通银行", "6344814578"), new Card("工商银行", "2131133133"))));

//        获取所有人的名字
        mans.forEach(man -> System.out.println(man.getName()));

        //获取所有人的名字集合
        List<String> collect = mans.stream().map(man -> man.getName()).collect(Collectors.toList());
        System.out.println(collect.toString());

//        获取姓名为张三的集合
        List<Man> collect1 = mans.stream().filter(man -> "张三".equals(man.getName())).collect(Collectors.toList());
        System.out.println(collect1.toString());

        //获取姓名为张三的人的银行卡集合
        List<Card> collect2 = mans.stream()
                .filter(man -> "张三".equals(man.getName()))
                .flatMap(m -> m.getCards().stream()).collect(Collectors.toList());
        System.out.println(collect2);


        //处理双列集合
        Map<Object, Object> map = new HashMap<>();
        map.put("1123", "123");
        map.put("1223", "223");
        map.put("1323", "323");
        map.forEach((x, y) -> {
            System.out.println(x + " : " + y);
        });
    }
}
