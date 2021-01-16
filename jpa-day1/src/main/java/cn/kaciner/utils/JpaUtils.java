package cn.kaciner.utils;

/**
 * @author Kaciner
 * @since 2021-01-16 20:44
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂的浪费资源和耗时的问题
 * 通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 */
public final class JpaUtils {
    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory factory;

    static {
        //1.加载配置文件，创建EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 使用管理器工厂生产一个管理器对象
     * @return
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
