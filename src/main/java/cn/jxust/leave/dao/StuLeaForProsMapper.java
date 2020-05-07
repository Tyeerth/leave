package cn.jxust.leave.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 *
 * @Author   lcs
 * @Date 20:23 2019/11/17
 **/
@Repository
public interface StuLeaForProsMapper {

//    List<StuLeaForPros> selectStuLeaForProsByCardNumberLike(@Param("cardNumber") String cardNumber);
//
//    List<StuLeaForPros> selectByLimitAndOffset(@Param("offset") Integer offset,
//                                               @Param("limit") Integer limit);


    int countOnes();




}
