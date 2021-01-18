import cn.kaciner.dao.CustomerDao;
import cn.kaciner.domain.Customer;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kaciner
 * @since 2021-01-17 11:45
 */

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器的配置信息
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     *  根据id查询
     */
    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(4L);
        System.out.println(customer);
    }

    /**
     *  保存或者更新
     *  根据传递的对象是否存在主键id，如果没有id主键属性：保存
     *  存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("风之旅人");
        customer.setCustLevel("vip");
        customer.setCustIndustry("教育");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("Kaciner");
        customer.setCustLevel("svip");
        customerDao.save(customer);
    }

    /**
     *  删除
     */
    @Test
    public void testDelete() {
        customerDao.delete(6L);
    }

    /**
     *  查询所有
     */
    @Test
    public void testFindAll() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     *  查询客户的总数量
     */
    @Test
    public void testCount() {
        long count = customerDao.count();//查询全部的客户数量
        System.out.println(count);
    }

    /**
     *  查询客户id为1的客户是否存在
     */
    @Test
    public void testExists() {
        boolean exists = customerDao.exists(1L);
        System.out.println(exists);
    }

    /**
     *  根据id从数据库查询
     *  findOne：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(1L);
        System.out.println(customer);
    }

}
