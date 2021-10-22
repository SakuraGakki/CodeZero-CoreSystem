# CMS
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2021-9-9|available|CodeZero|门户网站管理系统



## 
### 图片上传
**URL:** http://59.110.218.235:8000/cms/wechat/wechatImageUpload

**Type:** POST


**Content-Type:** multipart/form-data

**Description:** 图片上传

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
file|file|No comments found.|true|-

**Request-example:**
```
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/wechat/wechatImageUpload
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "waring": "You may have used non-display generics."
  }
}
```

### 图片替换上传
**URL:** http://59.110.218.235:8000/cms/wechat/wechatImageReplace

**Type:** POST


**Content-Type:** multipart/form-data

**Description:** 图片替换上传

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
file|file|No comments found.|true|-
imgId|string|No comments found.|true|-

**Request-example:**
```
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/wechat/wechatImageReplace --data 'imgId=126'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "waring": "You may have used non-display generics."
  }
}
```

### 新增微信文章
**URL:** http://59.110.218.235:8000/cms/wechat/insertWechatArticle

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 新增微信文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|主键Id|false|-
title|string|文章标题|false|-
url|string|文章链接|false|-
imgId|string|图片ID|false|-
date|string|发布时间|false|-
read|int32|阅读量|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/wechat/insertWechatArticle --data '{
  "seq": "jfajdlj12312lklsd",
  "title": "哈哈哈",
  "url": "http://www.baidu.com",
  "imgId": "1231231231fdsd",
  "date": "2021-10-09 12:12:56",
  "read": 123
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "waring": "You may have used non-display generics."
  }
}
```

### 更新微信文章
**URL:** http://59.110.218.235:8000/cms/wechat/updateWechatArticle

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 更新微信文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|主键Id|false|-
title|string|文章标题|false|-
url|string|文章链接|false|-
imgId|string|图片ID|false|-
date|string|发布时间|false|-
read|int32|阅读量|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/wechat/updateWechatArticle --data '{
  "seq": "jfajdlj12312lklsd",
  "title": "哈哈哈",
  "url": "http://www.baidu.com",
  "imgId": "1231231231fdsd",
  "date": "2021-10-09 12:12:56",
  "read": 123
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "waring": "You may have used non-display generics."
  }
}
```

### 查询微信文章列表
**URL:** http://59.110.218.235:8000/cms/wechat/getWechatArticleList

**Type:** POST


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 查询微信文章列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/cms/wechat/getWechatArticleList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|array|响应对象|-
└─seq|string|主键Id|-
└─title|string|文章标题|-
└─url|string|文章链接|-
└─imgId|string|图片Id|-
└─imgUrl|string|图片链接|-
└─date|string|发布时间|-
└─read|int32|阅读量|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": [
    {
      "seq": "jfajdlj12312lklsd",
      "title": "哈哈哈",
      "url": "http://www.baidu.com",
      "imgId": "1231231231fdsd",
      "imgUrl": "https://www.baidu.com/12312.png",
      "date": "2021-10-09 12:12:56",
      "read": 123
    }
  ]
}
```

## CMS登录相关接口
### 用户登录接口
**URL:** http://59.110.218.235:8000/cms/login

**Type:** POST

**Author:** codezero

**Content-Type:** application/json; charset=utf-8

**Description:** 用户登录接口

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
userId|string|用户ID|false|-
password|string|密码|false|-
ip|string|登录IP|false|-
city|string|登录地点|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/login --data '{
  "userId": "userId",
  "password": "password",
  "ip": "127.0.0.1",
  "city": "中国 辽宁 沈阳 联通"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─userId|string|用户ID|-
└─userName|string|用户名|-
└─avator|string|头像|-
└─sex|string|性别|-
└─birthday|string|生日|-
└─phone|string|手机号|-
└─address|string|地址|-
└─email|string|Email|-
└─wechatId|string|微信openId|-
└─lastIp|string|上次登录ip|-
└─lastCity|string|上次登录地点|-
└─lastTime|string|上次登录时间|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "userId": "userId",
    "userName": "userName",
    "avator": "avatorUrl",
    "sex": "1",
    "birthday": "2000-01-01",
    "phone": "13000000000",
    "address": "辽宁省沈阳市",
    "email": "abcd@qq.com",
    "wechatId": "djklf13j1l31dfjsl12313",
    "lastIp": "127.0.0.1",
    "lastCity": "中国 辽宁 沈阳 联通",
    "lastTime": "2021-01-01"
  }
}
```


