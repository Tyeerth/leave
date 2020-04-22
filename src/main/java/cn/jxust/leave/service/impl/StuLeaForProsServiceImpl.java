package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.StuLeaForProsMapper;
import cn.jxust.leave.po.vo.StuLeaForPros;
import cn.jxust.leave.service.StuLeaForProsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuLeaForProsServiceImpl implements StuLeaForProsService {
    @Autowired
    StuLeaForProsMapper stuLeaForProsMapper;

    @Override
    public List<StuLeaForPros> getStuLeaForProsList(Integer offset, Integer limit){
        return stuLeaForProsMapper.selectByLimitAndOffset(offset, limit);

    }

    @Override
    public List<StuLeaForPros>  getStuLeaForProsByCardNumberLike(String cardNumber){
        return stuLeaForProsMapper.selectStuLeaForProsByCardNumberLike(cardNumber);
    }

    @Override
    public int getStuLeaForProsCount(){

        return stuLeaForProsMapper.countOnes();
    }




}
