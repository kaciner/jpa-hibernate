import cn.kaciner.dao.CustomerDao;
import cn.kaciner.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kaciner
 * @since 2021-01-17 20:52
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    CustomerDao customerDao;

    @Test
    public void testFindJPQL() {
        Customer customer = customerDao.findJpql("Kaciner");
        System.out.println(customer);
    }

    @Test
    public void testFindCustNameAndId() {
        Customer customer = customerDao.findCustNameAndId("Kaciner", 1L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(2L, "Kaciner");
    }

    @Test
    public void testFindAllsql() {
        List<Object[]> customer = customerDao.findAllSql();
        for (Object[] objects : customer) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testFindsql() {
        List<Object[]> customer = customerDao.findSql("Kaciner");
        for (Object[] objects : customer) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testFindByCustName() {
        Customer customer = customerDao.findByCustName("Kaciner");
        System.out.println(customer);
    }

    @Test
    public void testFindByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("Kaciner%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindByCustNameLikeAnAndCustIndustry() {
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("Kaciner%", "教育");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

}
