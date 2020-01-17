package com.LOLdaojucheng.controller.scheduler;

import com.LOLdaojucheng.service.IOrderService;
import com.LOLdaojucheng.utils.PropertiesUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/***
 * 定时关闭订单
 */
@Component
public class CloserOrder {
    @Autowired
    IOrderService iOrderService;
    @Scheduled(cron="0 0 */1 * * *")
    public void CloseOrder(){
        System.out.println("=====定时关单=====");
        Integer timeOut = Integer.parseInt( PropertiesUtils.readByKey("close.order.time"));
        String date = com.LOLdaojucheng.utils.DateUtils.DateToString( DateUtils.addHours(new Date(),-timeOut));
        iOrderService.closeOrder(date);
    }
}
