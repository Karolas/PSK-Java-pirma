package psk.Mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import psk.Mybatis.models.Accounts;
import psk.Mybatis.models.Customers;

@Mapper
public interface CustomersMapper {
    List<Customers> selectAllCustomers();
    List<Accounts> selectAccountsForCustomer();

    int insert(Customers record);
    Customers selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(Customers record);
    int deleteByPrimaryKey(Integer id);
}