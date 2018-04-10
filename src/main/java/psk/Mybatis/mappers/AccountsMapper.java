package psk.Mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import psk.Mybatis.models.Accounts;
import psk.Mybatis.models.Transactions;

@Mapper
public interface AccountsMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Accounts record);
    Accounts selectByPrimaryKey(Integer id);
    Integer selectIdByAccountNr(Long accountNr);
    int updateByPrimaryKey(Accounts record);
}