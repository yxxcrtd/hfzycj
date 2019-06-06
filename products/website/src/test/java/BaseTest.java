import com.hfzycj.service.InformationService;
import com.hfzycj.util.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/applicationContext.xml", "classpath*:/mvc-servlet.xml" })
@WebAppConfiguration(value = "src/main/webapp")
public class BaseTest {

    @Autowired
    private InformationService informationService;

    @Test
    public void test() throws Exception {



        System.out.println(MD5.toMD5("123456_website"));



    }
}
