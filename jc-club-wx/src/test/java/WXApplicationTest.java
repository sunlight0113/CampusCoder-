import com.lunsir.wx.WxApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lunSir
 * @create 2024-10-10 14:49
 */
@SpringBootTest(classes = WxApplication.class)
public class WXApplicationTest {
    @Test
    public void test1() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.indexOf(1));
    }
}
