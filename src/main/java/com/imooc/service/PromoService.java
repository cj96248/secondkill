package com.imooc.service;

import com.imooc.error.BusinessException;
import com.imooc.service.model.PromoModel;

public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId) throws BusinessException;
}
