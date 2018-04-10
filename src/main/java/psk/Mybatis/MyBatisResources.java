package psk.Mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@Slf4j
@ApplicationScoped
public class MyBatisResources {

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed. (MyBatis)");
    }

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private SqlSessionFactory produceSqlSessionFactory() {
        try {
            SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatisConfig.xml"));
            return builder;
        } catch (IOException e) {
            log.error("produceSqlSessionFactory(): " , e);
            throw new RuntimeException("produceSqlSessionFactory(): " , e);
        } catch (Exception e) {
            System.out.println("produceSqlSessionFactory(): "  + e);
            throw new RuntimeException("produceSqlSessionFactory(): " , e);
        }

        finally {
            System.out.println("Session factory");
        }
    }
}