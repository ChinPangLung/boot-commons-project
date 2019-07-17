package temp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * BigDecimal
 *
 * @author longzhanpeng chinpang9527@gmail.com
 * @since 2019-07-11 09:01
 */
@Slf4j
public class BigDecimalTest {

    @Test
    public void test1() {
        BigDecimal zero = BigDecimal.ZERO;
        log.info(String.valueOf(zero));
        BigDecimal bigDecimal1 = new BigDecimal(1);
        System.out.println(bigDecimal1);
        int i = bigDecimal1.compareTo(zero);
        System.out.println(i);
        BigDecimal bigDecimal2 = new BigDecimal(20);
        int i1 = bigDecimal1.compareTo(bigDecimal2);
        System.out.println(i1);
        System.out.println("==================================");
        BigDecimal bigDecimal3 = new BigDecimal(12.3456789);
        System.out.println(bigDecimal3);
        System.out.println(bigDecimal3.setScale(4, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal3);
        System.out.println(bigDecimal3.setScale(4, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal3.setScale(4,BigDecimal.ROUND_HALF_UP));
    }
}