package cn.kaciner.test;

/**
 * @author Kaciner
 * @since 2021-01-16 21:29
 */

import cn.kaciner.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {
    /**
     * 查询全部
     * sql: SELECT * FROM cst_customer
     * jpql: from Customer
     */
    @Test
    public void testFindAll() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //查询全部
        //String jpql = "from Customer";
        //Query query = em.createQuery(jpql);// 创建Query查询对象，query对象才是执行jpql的对象

        //排序查询
        //String jpql = "from Customer order by custId desc";
        //Query query = em.createQuery(jpql);

        //统计总数
        String jpql = "select count(1) from Customer";
        Query query = em.createQuery(jpql);

        //发送查询，并封装结果集
//        List list = query.getResultList();
//        for (Object o : list) {
//            System.out.println(o);
//        }
        Object result = query.getSingleResult();
        System.out.println(result);

        ts.commit();
        em.close();
    }

    /**
     * 分页查询
     * sql: SELECT * FROM cst_customer limit ?,?
     * jpql: from Customer
     */
    @Test
    public void testPage() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //1.根据jpql语句创建Query查询对象
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        //2.对参数赋值：分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //3.发送查询，并封装结果
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }

    /**
     * 条件查询
     * sql: SELECT * FROM cst_customer WHERE cust_name LIKE ?
     * jpql: from Customer where custName like ?
     */
    @Test
    public void testCondition() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //1.根据jpql语句创建Query查询对象
        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql);
        //2.对参数赋值：占位符参数
        query.setParameter(1,"Kaciner%");
        //3.发送查询，并封装结果
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        ts.commit();
        em.close();
    }

}
