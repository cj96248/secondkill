package com.imooc.service.impl;

import com.imooc.dao.PromoDoMapper;
import com.imooc.dataobject.PromoDo;
import com.imooc.error.BusinessException;
import com.imooc.service.PromoService;
import com.imooc.service.model.PromoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDoMapper promoDoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) throws BusinessException {
        PromoDo promoDo = promoDoMapper.selectByItemId(itemId);
        PromoModel promoModel = convertToPromoModel(promoDo);
        if(promoModel == null){
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        if(promoModel.getStartTime().isAfter(now)){
            promoModel.setStatus(1);
        }else if(promoModel.getEndTime().isBefore(now)){
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertToPromoModel(PromoDo promoDo){
        if(promoDo == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDo, promoModel);
        promoModel.setStartTime(dateToLocalDate(promoDo.getStartTime()));
        promoModel.setEndTime(dateToLocalDate(promoDo.getEndTime()));
        return promoModel;
    }

    private static LocalDateTime dateToLocalDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}
