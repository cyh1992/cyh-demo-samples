package com.cyh.weixin.model;

import lombok.Data;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author cyh
 * @date 2021/1/6 10:51
 */
@Data
public class WechatUser {
    private String openId;
    private String accessToken;
    private String unionId;
    private String nickName;
    private String language;
    private String country;
    private String province;
    private String city;
    private Integer sex;
    private String sexDesc;
    private String headImgUrl;

    public WechatUser(WxMpUser wxMpUser, String accessToken){
        this.setAccessToken(accessToken);
        this.setOpenId(wxMpUser.getOpenId());
        this.setUnionId(wxMpUser.getUnionId());
        this.setNickName(wxMpUser.getNickname());
        this.setLanguage(wxMpUser.getLanguage());
        this.setCountry(wxMpUser.getCountry());
        this.setProvince(wxMpUser.getCity());
        this.setCity(wxMpUser.getCity());
        this.setSex(wxMpUser.getSex());
        this.setSexDesc(wxMpUser.getSexDesc());
        this.setHeadImgUrl(wxMpUser.getHeadImgUrl());
    }
}
