package com.zsm.mini.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zsm.mini.config.ApplicationConfig;
import com.zsm.mini.dao.UserMapper;
import com.zsm.mini.dao.biz.UserBizMapper;
import com.zsm.mini.domain.User;
import com.zsm.mini.dto.ResultDto;
import com.zsm.mini.util.HttpClientHelper;
import com.zsm.mini.util.StringUtils;
import com.zsm.mini.util.WXUtils;
import com.zsm.mini.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/wechat/mini")
@Api("小程序调用到相关的api")
public class WechatMiniController {
	
	@Autowired
	private UserBizMapper userBizMapper;
	
	/**
	 * 首次获取用户信息
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/code/user")
	@ApiOperation(value = "根据code查询用户信息", notes = "拿到用户信息注册用户")
	@ApiImplicitParam(name = "data", value="用户info", paramType="query", required = true, dataType = "String")
	public @ResponseBody ResultDto registerUser(String data) {
		ResultDto dto = new ResultDto();
		if(StringUtils.isEmptyString(data)) {
			dto.setSuccess(false);
			dto.setMsg("请求数据不能为空！");
			return dto;
		}
		//获取请求数据
		JSONObject requsetJson = JSONObject.parseObject(data);
		String js_code = requsetJson.getString("code");
		JSONObject jsonObj = requsetJson.getJSONObject("info");
		JSONObject resulJson = _code2Session(js_code);
		if(resulJson != null) {
			//解密用户数据
			if(resulJson.get("session_key") != null) {
				String session_key = resulJson.getString("session_key");
				String encryptedData = jsonObj.getString("encryptedData");
				String iv = jsonObj.getString("iv");
				JSONObject jsonObject = WXUtils.getUserInfo(encryptedData, session_key, iv);
				User user = userBizMapper.selectUserByOpenId(jsonObject.getString("openId"));
				if(user == null) {
					user = new User();
					user.setFid(UUID.randomUUID().toString());
					user.setNickname(jsonObject.getString("nickName"));
					user.setGender(jsonObject.getInteger("gender"));
					user.setAvatarurl(jsonObject.getString("avatarUrl"));
					user.setUnionid(jsonObject.getString("unionId"));
					user.setOpenid(jsonObject.getString("openId"));
					user.setCountry(jsonObject.getString("country"));
					user.setProvince(jsonObject.getString("province"));
					user.setCity(jsonObject.getString("city"));
					userBizMapper.insert(user);
				}
				UserVo vo = new UserVo();
				vo.setOpenId(user.getOpenid());
				dto.setUserObject(vo);
				dto.setSuccess(true);
				dto.setMsg("请求成功!");
			}
		}
		return dto;
		
	}
	
	/**
	 * 判断用户是否已授权
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/code/isbind")
	@ApiOperation(value = "根据code查询用户是否授权", notes = "根据code查询用户是否授权")
	@ApiImplicitParam(name = "code", value="login-code", paramType="query", required = true, dataType = "String")
	public @ResponseBody ResultDto getOpenIdByCode(String code) {
		ResultDto dto = new ResultDto();
		if(StringUtils.isEmptyString(code)) {
			dto.setSuccess(false);
			dto.setMsg("code不能为空！");
			return dto;
		}
		//获取请求数据
		JSONObject resulJson = _code2Session(code);
		if(resulJson != null) {
			String openid = resulJson.getString("openid");
			User user = userBizMapper.selectUserByOpenId(openid);
			if(user != null) {
				dto.setSuccess(true);
				dto.setUserObject(openid);
			}else {
				dto.setSuccess(false);
			}
			dto.setMsg("请求成功!");
		}
		return dto;
		
	}
	/**
	 * 判断用户是否已授权
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/code/phone")
	@ApiOperation(value = "根据phone查询用户是否授权", notes = "根据code查询用户是否授权")
	@ApiImplicitParam(name = "data", value="手机加密信息", paramType="query", required = true, dataType = "String")
	public @ResponseBody ResultDto registerUserPhone(String data) {
		ResultDto dto = new ResultDto();
		if(StringUtils.isEmptyString(data)) {
			dto.setSuccess(false);
			dto.setMsg("data不能为空！");
			return dto;
		}
		JSONObject json = JSONObject.parseObject(data);
		String code = json.getString("code");
		String iv = json.getString("iv");
		String encryptedData = json.getString("encryptedData");
		//获取请求数据
		JSONObject resulJson = _code2Session(code);
		if(resulJson != null) {
			String session_key = resulJson.getString("session_key");
			String openId = resulJson.getString("openid");
			JSONObject jsonObject = WXUtils.getUserInfo(encryptedData, session_key, iv);
			if(jsonObject != null) {
				String phoneNumber = jsonObject.getString("phoneNumber");
				User user = userBizMapper.selectUserByOpenId(openId);
				if(user != null) {
					user.setPhone(phoneNumber);
					userBizMapper.updateByPrimaryKey(user);
					dto.setSuccess(true);
					dto.setMsg("授权成功！");
				}else {
					dto.setSuccess(false);
					dto.setMsg("授权失败！");
				}
			}
		}
		return dto;
		
	}
	
	public JSONObject _code2Session(String code) {
		//获取openId和session_key
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("appid", ApplicationConfig.APPID);
		parameters.put("secret", ApplicationConfig.APPSECRET);
		parameters.put("js_code", code);
		parameters.put("grant_type", ApplicationConfig.GRANT_TYPE);
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		String result = HttpClientHelper.requestBodyString(url, parameters);
		JSONObject resulJson = null;
		if(!"".equals(result)) {
			//解密用户数据
			resulJson = JSONObject.parseObject(result);
		}
		return resulJson;
	}
}
