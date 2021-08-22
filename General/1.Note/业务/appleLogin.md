## 

## 1. 微信登录

1. https://developers.weixin.qq.com/doc/oplatform/Mobile_App/WeChat_Login/Authorized_API_call_UnionID.html
2. `LoginWeChatManager` -> `h5Login` -> `wxWapLogin` 
   1. 请求获取 access_token：`getAccessToken`
   2. 获取微信用户信息：`getWechatInfo`
   3. 登录接口：`loginByUnionId`

## 2. 苹果登录

1. https://developer.apple.com/documentation/sign_in_with_apple/sign_in_with_apple_rest_api/authenticating_users_with_sign_in_with_apple
2. Use the `authorization code` to verify the token claims with Apple servers, and exchange them for refresh tokens. For more information, see [Generate and Validate Tokens](https://developer.apple.com/documentation/sign_in_with_apple/generate_and_validate_tokens).

```
uxWHNdWjb2IxY3BBje5H
AppleIDUserDTO{openid='000675.a07f402c3c6e40e3b6d4e525aa614927.1745', authorizationCode='c6d87196d68c0495abdac5fd432b80cbd.0.rwxv.LFa02ifmEMLvPN9RrdPEoA'}
2021-07-29 13:58:02,558 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.service.passport.impl.LoginAppleIDManagerImpl : openId000675.a07f402c3c6e40e3b6d4e525aa614927.1745
2021-07-29 13:58:02,559 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.framework.cache.MybatisRedisCache : 获取key：com.enation.app.javashop.mapper.member.ConnectMapper--1520177862:7412909662:com.enation.app.javashop.mapper.member.ConnectMapper.selectOne:0:2147483647:SELECT  id,member_id,union_id,union_type,unbound_time  FROM es_connect 
 
 WHERE (union_id = ? AND union_type = ?):000675.a07f402c3c6e40e3b6d4e525aa614927.1745:APPLEID:MybatisSqlSessionFactoryBean
cache + null
2021-07-29 13:58:02,560 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.ConnectMapper : Cache Hit Ratio [com.enation.app.javashop.mapper.member.ConnectMapper]: 0.0
2021-07-29 13:58:02,560 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.ConnectMapper.selectOne : ==>  Preparing: SELECT id,member_id,union_id,union_type,unbound_time FROM es_connect WHERE (union_id = ? AND union_type = ?) 
2021-07-29 13:58:02,560 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.ConnectMapper.selectOne : ==> Parameters: 000675.a07f402c3c6e40e3b6d4e525aa614927.1745(String), APPLEID(String)
2021-07-29 13:58:02,595 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.ConnectMapper.selectOne : <==      Total: 1
2021-07-29 13:58:02,595 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.framework.cache.MybatisRedisCache : 存入key：com.enation.app.javashop.mapper.member.ConnectMapper--1520177862:7412909662:com.enation.app.javashop.mapper.member.ConnectMapper.selectOne:0:2147483647:SELECT  id,member_id,union_id,union_type,unbound_time  FROM es_connect 
 
 WHERE (union_id = ? AND union_type = ?):000675.a07f402c3c6e40e3b6d4e525aa614927.1745:APPLEID:MybatisSqlSessionFactoryBean
2021-07-29 13:58:02,601 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.framework.cache.MybatisRedisCache : 获取key：com.enation.app.javashop.mapper.member.MemberMapper---29351330:1576607079:com.enation.app.javashop.mapper.member.MemberMapper.selectById:0:2147483647:SELECT member_id,uname,email,password,create_time,sex,birthday,province_id,city_id,county_id,town_id,province,city,county,town,address,mobile,tel,grade_point,consum_point,msn,remark,last_login,login_count,is_cheked,register_ip,recommend_point_state,info_full,find_code,face,midentity,disabled,shop_id,have_shop,nickname,language FROM es_member WHERE member_id=? :1420800352782442498:MybatisSqlSessionFactoryBean
cache + null
2021-07-29 13:58:02,602 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.MemberMapper : Cache Hit Ratio [com.enation.app.javashop.mapper.member.MemberMapper]: 0.0
2021-07-29 13:58:02,603 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.MemberMapper.selectById : ==>  Preparing: SELECT member_id,uname,email,password,create_time,sex,birthday,province_id,city_id,county_id,town_id,province,city,county,town,address,mobile,tel,grade_point,consum_point,msn,remark,last_login,login_count,is_cheked,register_ip,recommend_point_state,info_full,find_code,face,midentity,disabled,shop_id,have_shop,nickname,language FROM es_member WHERE member_id=? 
2021-07-29 13:58:02,603 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.MemberMapper.selectById : ==> Parameters: 1420800352782442498(Long)
2021-07-29 13:58:02,613 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.mapper.member.MemberMapper.selectById : <==      Total: 1
2021-07-29 13:58:02,613 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.framework.cache.MybatisRedisCache : 存入key：com.enation.app.javashop.mapper.member.MemberMapper---29351330:1576607079:com.enation.app.javashop.mapper.member.MemberMapper.selectById:0:2147483647:SELECT member_id,uname,email,password,create_time,sex,birthday,province_id,city_id,county_id,town_id,province,city,county,town,address,mobile,tel,grade_point,consum_point,msn,remark,last_login,login_count,is_cheked,register_ip,recommend_point_state,info_full,find_code,face,midentity,disabled,shop_id,have_shop,nickname,language FROM es_member WHERE member_id=? :1420800352782442498:MybatisSqlSessionFactoryBean
2021-07-29 13:58:02,615 DEBUG [http-nio-7002-exec-6] com.enation.app.javashop.service.passport.impl.LoginManagerImpl : 三方登录openId为：null;unionid为000675.a07f402c3c6e40e3b6d4e525aa614927.1745

```

```json
authInfoRes = {
	"errMsg": "getauthInfo:ok",
	"authInfo": {
		"openId": "000675.a07f402c3c6e40e3b6d4e525aa614927.1745",
		"fullName": {
			"familyName": "Wang",
			"giveName": "Zhimeng",
			"givenName": "Zhimeng"
		},
		"email": denniswzm @gmail.com,
		"authorizationCode": "caea27255ed0f495a94fe0b5870b410d3.0.swxv.sffKiQW3d0wMUtjDpXirWw",
		"identityToken": "eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLm90dDEyMy5hcHAxMjMiLCJleHAiOjE2Mjc1ODA3MDMsImlhdCI6MTYyNzQ5NDMwMywic3ViIjoiMDAwNjc1LmEwN2Y0MDJjM2M2ZTQwZTNiNmQ0ZTUyNWFhNjE0OTI3LjE3NDUiLCJjX2hhc2giOiJMRWUwOWlhUjBKT3hEN1RCcW9rNW53IiwiZW1haWwiOiJkZW5uaXN3em1AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNjI3NDk0MzAzLCJub25jZV9zdXBwb3J0ZWQiOnRydWUsInJlYWxfdXNlcl9zdGF0dXMiOjJ9.MlA5729RCgjWM9jj3lIeoz_GUhnLqsZX1GpyqEElDhUxXcJyTxusXCZzgq6MW8OO3-mGUtFI1uWoOu4sMaWOq0TAz8hTzS5P5Kp8J2dUaJxcqIzWd8nUdUIBV9Mml03qMcIMAfF7xuM6aF--Js_xC3u1UEQjTgxeo5caun6-QxUMQfP9d3dggxhkJt30dCUXj34sUjXTFyGgjF0MSeZb7mjSjllPt3QBK-CsqyB3apmZvik7n-YZexu-l1rUaKbouWJtLZG_QquwXz1b6WAFV16pzldpneESIU9ukxpwdRK7eTPdWaUX-8X8JijJMNacnlukmUA0HUhdJ6iz0gWp9Q",
		"realauthStatus": 2
	}
}

at pages / auth / login.vue: 552
13: 45: 36.180 login = {
	"authResult": {
		"access_token": "eyJraWQiOiJZdXlYb1kiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLm90dDEyMy5hcHAxMjMiLCJleHAiOjE2Mjc1ODA3MDMsImlhdCI6MTYyNzQ5NDMwMywic3ViIjoiMDAwNjc1LmEwN2Y0MDJjM2M2ZTQwZTNiNmQ0ZTUyNWFhNjE0OTI3LjE3NDUiLCJjX2hhc2giOiJMRWUwOWlhUjBKT3hEN1RCcW9rNW53IiwiZW1haWwiOiJkZW5uaXN3em1AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNjI3NDk0MzAzLCJub25jZV9zdXBwb3J0ZWQiOnRydWUsInJlYWxfdXNlcl9zdGF0dXMiOjJ9.MlA5729RCgjWM9jj3lIeoz_GUhnLqsZX1GpyqEElDhUxXcJyTxusXCZzgq6MW8OO3-mGUtFI1uWoOu4sMaWOq0TAz8hTzS5P5Kp8J2dUaJxcqIzWd8nUdUIBV9Mml03qMcIMAfF7xuM6aF--Js_xC3u1UEQjTgxeo5caun6-QxUMQfP9d3dggxhkJt30dCUXj34sUjXTFyGgjF0MSeZb7mjSjllPt3QBK-CsqyB3apmZvik7n-YZexu-l1rUaKbouWJtLZG_QquwXz1b6WAFV16pzldpneESIU9ukxpwdRK7eTPdWaUX-8X8JijJMNacnlukmUA0HUhdJ6iz0gWp9Q",
		"openid": "000675.a07f402c3c6e40e3b6d4e525aa614927.1745"
	},
	"errMsg": "login:ok"
}
```

```
[
ConnectDO{id=1422566513664765953, memberId=1422566513404719107, unionId='001739.c0e8b146bf1344ea87f88cb501f292df.1424', unionType='APPLEID', unboundTime=null}, ConnectDO{id=1422566513664765954, memberId=1422566513404719108, unionId='001739.c0e8b146bf1344ea87f88cb501f292df.1424', unionType='APPLEID', unboundTime=null}, ConnectDO{id=1422566513664765955, memberId=1422566513404719105, unionId='001739.c0e8b146bf1344ea87f88cb501f292df.1424', unionType='APPLEID', unboundTime=null}, ConnectDO{id=1422566513727680514, memberId=1422566513404719106, unionId='001739.c0e8b146bf1344ea87f88cb501f292df.1424', unionType='APPLEID', unboundTime=null}
]
```





