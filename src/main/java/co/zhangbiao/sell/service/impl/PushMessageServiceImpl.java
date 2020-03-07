package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.properties.WechatProperties;
import co.zhangbiao.sell.service.PushMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {

    private static final Logger logger = LoggerFactory.getLogger(PushMessageServiceImpl.class);

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatProperties wechatProperties;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatProperties.getTemplateMap().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，请记得收货哦！"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","18500199974"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5","￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临！")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            logger.error("【微信模板消息】发送失败,{}", e.getMessage());
        }
    }
}
