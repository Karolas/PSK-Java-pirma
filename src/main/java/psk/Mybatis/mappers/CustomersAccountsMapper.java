package psk.Mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import psk.Mybatis.models.CustomersAccountsKey;

@Mapper
public interface CustomersAccountsMapper {
    int deleteByPrimaryKey(CustomersAccountsKey key);
    int insert(CustomersAccountsKey record);
}