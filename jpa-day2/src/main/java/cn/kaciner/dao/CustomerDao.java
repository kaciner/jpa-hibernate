package cn.kaciner.dao;

import cn.kaciner.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Kaciner
 * @since 2021-01-17 11:40
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    //@Query 使用jpql的方式查询。
    @Query(value="from Customer")
    public List<Customer> findAllCustomer();

    /**
     * 案例：根据客户名称查询客户
     */
    @Query(value = "from Customer where custName = ?1")
    public Customer findJpql(String custName);

    /**
     * 案例：根据客户名称和客户id查询客户
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCustNameAndId(String name, Long id);

    /**
     * 案例：根据客户id更新客户名称
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustomer(Long id, String name);

    /**
     * 案例：原生sql查询所有
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Object []> findAllSql();

    /**
     * 案例：根据客户id更新客户名称
     */
    @Query(value = "select * from cst_customer where cust_name like ?", nativeQuery = true)
    public List<Object []> findSql(String name);

    //使用客户名称的精准匹配查询
    public Customer findByCustName(String name);

    //使用客户名称的模糊匹配查询
    public List<Customer> findByCustNameLike(String name);

    //使用客户名称的模糊匹配和客户行业的精准匹配查询
    public List<Customer> findByCustNameLikeAndCustIndustry(String name, String industry);
}
