package psk.Mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import psk.Mybatis.models.Transactions;

@Mapper
public interface TransactionsMapper {

    List<Transactions> selectTransfersByCustomer(@Param("accId") Integer accountId, @Param("custId") Integer customerId);
    int deleteByPrimaryKey(Integer id);
    int insert(Transactions record);
    Transactions selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(Transactions record);
}