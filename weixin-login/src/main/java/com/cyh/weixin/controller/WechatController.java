package com.cyh.weixin.controller;

import com.cyh.weixin.model.WechatUser;
import com.cyh.weixin.utils.ResponseObject;
import lombok.val;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @author cyh
 * @date 2021/1/6 11:06
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

   protected Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
  private WxMpService wxMpService;

   @GetMapping("/auth")
   public ResponseObject authorize(String authCallbackUrl,String returnUrl){
      // 获取微信返回的重定向url
      String redirectUrl  = wxMpService.oauth2buildAuthorizationUrl(
              authCallbackUrl,
              WxConsts.OAuth2Scope.SNSAPI_USERINFO,
              URLEncoder.encode(returnUrl));
      return new ResponseObject(redirectUrl);
   }

   /**
    * 初次授权获取用户信息
    * @param code
    * @param redirectUrl
    * @return
    */
   @GetMapping("/auth/user/info")
   public ResponseObject userInfo(@RequestParam("code") String code, @RequestParam("state") String redirectUrl){
      WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
      WxMpUser wxMpUser=null;
      //用code获取access_token 信息
      try {
         wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
         wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
      } catch (WxErrorException e) {
         e.printStackTrace();
      }
      String openId = wxMpOAuth2AccessToken.getOpenId();
      WechatUser wechatUser = new WechatUser(wxMpUser,wxMpOAuth2AccessToken.getAccessToken());
      return new ResponseObject(wechatUser);

   }

   public ResponseObject getUserInfo(String openid,String token){
      WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
      wxMpOAuth2AccessToken.setOpenId(openid);
      wxMpOAuth2AccessToken.setAccessToken(token);
      boolean b = wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken);
      if(!b){
         //已失效
         try {
            wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
         } catch (WxErrorException e) {
            e.printStackTrace();
         }
      }
      //获取用户信息
      try {
         WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
         WechatUser wechatUser = new WechatUser(wxMpUser, wxMpOAuth2AccessToken.getAccessToken());
         return new ResponseObject(wechatUser);
      } catch (WxErrorException e) {
         e.printStackTrace();
      }
    return null;


   }
}
