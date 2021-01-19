import cn.kaciner.dao.CustomerDao;
import cn.kaciner.domain.Customer;
import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author Kaciner
 * @since 2021-01-18 16:59
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    CustomerDao customerDao;

    /**
     * 根据条件查询单个对象
     */
    @Test
    public void testSpec() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.equal(custName, "Kaciner");
                return predicate;
            }
        };
        Customer customer= customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 根据条件查询多个对象
     */
    @Test
    public void testSpec1() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                //1.构造客户名的精准匹配查询
                Predicate p1 = cb.equal(custName, "Kaciner");
                //2.构造所属行业的精准匹配查询
                Predicate p2 = cb.equal(custIndustry, "教育");
                //3.将多个查询条件组合到一起
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 根据客户名称模糊查询
     */
    @Test
    public void testSpec2() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), "Kaciner%");
                return like;
            }
        };
        //List<Customer> list = customerDao.findAll(spec);
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testSpec3() {
        Specification<Customer> spec = null;
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerDao.findAll(spec, pageable);
        //获取数据集合
        List<Customer> list = page.getContent();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        //获取总条数
        long elements = page.getTotalElements();
        System.out.println(elements);
        //获取总页数
        int pages = page.getTotalPages();
        System.out.println(pages);
    }
}
