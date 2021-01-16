package cn.kaciner.test;

import cn.kaciner.domain.Customer;
import cn.kaciner.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author Kaciner
 * @since 2021-01-15 13:26
 */
public class JpaTest {
    @Test
    public void testSave() {
        // 1.加载配置文件创建工厂（实体管理器工厂）对象
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        // 2.通过实体管理器工厂获取实体管理器
        //EntityManager entityManager = factory.createEntityManager();
        EntityManager entityManager = JpaUtils.getEntityManager();
        // 3.获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); //开启事务
        // 4.完成增删改查操作
        Customer customer = new Customer();
        customer.setCustName("Kaciner");
        customer.setCustIndustry("教育");

        //保存
        entityManager.persist(customer);
        // 5.提交事务
        transaction.commit();
        // 6.释放资源
        entityManager.close();
        //factory.close();
    }

    /**
     *  保存
     */
    @Test
    public void save() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("Kaciner");
        em.persist(customer);
        transaction.commit();
        em.close();
    }

    /**
     * 根据id查询客户
     */
    @Test
    public void find() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        /**
         * find: 根据id查询数据
         * class: 查询数据的结果需要包装的实体类类型的字节码
         * id: 查询的主键的取值
         */

        /**
         * 立即加载 在调用find方法的时候就会发送sql语句查询数据库
         * Customer customer = em.find(Customer.class, 1L);
         * 延迟加载 调用getReference方法不会立即发送sql语句查询数据库，当调用查询结果对象时才会发送sql语句
         * 获取的对象是一个动态代理对象
         */
        Customer customer = em.getReference(Customer.class, 1L);
        System.out.println(customer);
        ts.commit();
        em.close();
    }

    /**
     * 删除客户的案例
     */
    @Test
    public void delete() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        Customer customer = em.getReference(Customer.class, 1L);
        em.remove(customer);
        ts.commit();
        em.close();
    }

    /**
     * 更新
     */
    @Test
    public void update() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        Customer customer = em.getReference(Customer.class, 2L);
        customer.setCustName("K");
        em.merge(customer);
        ts.commit();
        em.close();
    }
}
