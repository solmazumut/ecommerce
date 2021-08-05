package com.microservices.ecommerce.promotion.service.routes.request.models;

import java.util.ArrayList;

public class RequestWithIdAndList {
    private long promotionId;
    private ArrayList<Long> IdList;

    public RequestWithIdAndList() {
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public ArrayList<Long> getIdList() {
        return IdList;
    }

    public void setIdList(ArrayList<Long> idList) {
        IdList = idList;
    }
}
