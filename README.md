# CodeZero-CoreSystem
## 核心系统

# Website
## 门户网站服务
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2021-9-9|available|CodeZero|门户网站



## 留言信息相关接口
### 获取留言列表（包含回复）
**URL:** http://59.110.218.235:8000/website/messageboard/getMessageBoardList

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取留言列表（包含回复）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/getMessageBoardList --data 'size=417&current=119'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─searchCount|boolean|是否进行 count 查询|-
└─pages|int64|当前分页总页数|-
└─hitCount|boolean|是否命中count缓存|-
└─records|array|查询数据列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int32|留言id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ip|string|留言ip|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─city|string|留言地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|留言时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|留言内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─list|array|回复列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int32|留言id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ip|string|留言ip|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─city|string|留言地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|留言时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|留言内容|-
└─total|int64|总数|-
└─size|int64|每页显示条数，默认 10|-
└─current|int64|当前页|-
└─orders|array|排序字段信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─column|string|需要进行排序的字段|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─asc|boolean|是否正序排列，默认 true|-
└─optimizeCountSql|boolean|自动优化 COUNT SQL|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "searchCount": true,
    "pages": 291,
    "hitCount": true,
    "records": [
      {
        "id": 1,
        "ip": "127.0.0.1",
        "city": "中国 辽宁 沈阳 联通",
        "date": "2021-09-29 00:00:00",
        "content": "哈哈哈",
        "list": "（List）回复列表"
      }
    ],
    "total": 736,
    "size": 310,
    "current": 945,
    "orders": [
      {
        "column": "ztpnip",
        "asc": true
      }
    ],
    "optimizeCountSql": true
  }
}
```

### 插入主留言信息
**URL:** http://59.110.218.235:8000/website/messageboard/insertMessageBoardInfo

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 插入主留言信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|留言id|false|-
ip|string|留言ip|false|-
city|string|留言地址|false|-
date|string|留言时间|false|-
content|string|留言内容|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/insertMessageBoardInfo --data 'content=哈哈哈&ip=127.0.0.1&id=1&date=2021-09-29 00:00:00&city=中国 辽宁 沈阳 联通'
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

### 插入留言回复信息
**URL:** http://59.110.218.235:8000/website/messageboard/insertMessageBoardSubInfo

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 插入留言回复信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|留言id|false|-
ip|string|留言ip|false|-
city|string|留言地址|false|-
date|string|留言时间|false|-
content|string|留言内容|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/insertMessageBoardSubInfo --data 'ip=127.0.0.1&date=2021-09-29 00:00:00&city=中国 辽宁 沈阳 联通&content=哈哈哈&id=1'
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

### 获取未回复留言列表
**URL:** http://59.110.218.235:8000/website/messageboard/getMessageBoardUnreadList

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取未回复留言列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/getMessageBoardUnreadList --data 'current=738&size=293'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─searchCount|boolean|是否进行 count 查询|-
└─pages|int64|当前分页总页数|-
└─hitCount|boolean|是否命中count缓存|-
└─records|array|查询数据列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int32|留言id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ip|string|留言ip|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─city|string|留言地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|留言时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|留言内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─list|array|回复列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int32|留言id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ip|string|留言ip|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─city|string|留言地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|留言时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|留言内容|-
└─total|int64|总数|-
└─size|int64|每页显示条数，默认 10|-
└─current|int64|当前页|-
└─orders|array|排序字段信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─column|string|需要进行排序的字段|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─asc|boolean|是否正序排列，默认 true|-
└─optimizeCountSql|boolean|自动优化 COUNT SQL|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "searchCount": true,
    "pages": 188,
    "hitCount": true,
    "records": [
      {
        "id": 1,
        "ip": "127.0.0.1",
        "city": "中国 辽宁 沈阳 联通",
        "date": "2021-09-29 00:00:00",
        "content": "哈哈哈",
        "list": "（List）回复列表"
      }
    ],
    "total": 287,
    "size": 526,
    "current": 623,
    "orders": [
      {
        "column": "8mfxz0",
        "asc": true
      }
    ],
    "optimizeCountSql": true
  }
}
```

### 获取未读消息数量
**URL:** http://59.110.218.235:8000/website/messageboard/getUnreadMessageCount

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取未读消息数量

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/getUnreadMessageCount
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

### 获取所有留言（包含回复）
**URL:** http://59.110.218.235:8000/website/messageboard/getMessageListAll

**Type:** POST

**Author:** codezero

**Content-Type:** application/json; charset=utf-8

**Description:** 获取所有留言（包含回复）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-
keyword|string|No comments found.|false|-
status|string|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/website/messageboard/getMessageListAll --data '{
  "current": 824,
  "size": 870,
  "keyword": "mlpgrw",
  "status": "kt9ao4"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─searchCount|boolean|是否进行 count 查询|-
└─pages|int64|当前分页总页数|-
└─hitCount|boolean|是否命中count缓存|-
└─records|array|查询数据列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int32|留言id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─msgType|string|留言类型|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─ip|string|留言ip|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─city|string|留言地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|留言时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|留言内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─status|string|状态|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─relatedId|int32|关联id|-
└─total|int64|总数|-
└─size|int64|每页显示条数，默认 10|-
└─current|int64|当前页|-
└─orders|array|排序字段信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─column|string|需要进行排序的字段|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─asc|boolean|是否正序排列，默认 true|-
└─optimizeCountSql|boolean|自动优化 COUNT SQL|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "searchCount": true,
    "pages": 975,
    "hitCount": true,
    "records": [
      {
        "id": 1,
        "msgType": "0",
        "ip": "127.0.0.1",
        "city": "中国 辽宁 沈阳 联通",
        "date": "2021-09-29 00:00:00",
        "content": "哈哈哈",
        "status": "0",
        "relatedId": 0
      }
    ],
    "total": 583,
    "size": 990,
    "current": 874,
    "orders": [
      {
        "column": "9ud63f",
        "asc": true
      }
    ],
    "optimizeCountSql": true
  }
}
```

### 站长新增留言&回复
**URL:** http://59.110.218.235:8000/website/messageboard/adminInsertMessageBoardInfo

**Type:** POST

**Author:** codezero

**Content-Type:** application/json; charset=utf-8

**Description:** 站长新增留言&回复

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|留言id|false|-
ip|string|留言ip|false|-
city|string|留言地址|false|-
date|string|留言时间|false|-
content|string|留言内容|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/website/messageboard/adminInsertMessageBoardInfo --data '{
  "id": 1,
  "ip": "127.0.0.1",
  "city": "中国 辽宁 沈阳 联通",
  "date": "2021-09-29 00:00:00",
  "content": "哈哈哈"
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

### 站长修改留言
**URL:** http://59.110.218.235:8000/website/messageboard/adminUptMessageBoardInfo

**Type:** POST

**Author:** codezero

**Content-Type:** application/json; charset=utf-8

**Description:** 站长修改留言

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|留言id|false|-
ip|string|留言ip|false|-
city|string|留言地址|false|-
date|string|留言时间|false|-
content|string|留言内容|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/website/messageboard/adminUptMessageBoardInfo --data '{
  "id": 1,
  "ip": "127.0.0.1",
  "city": "中国 辽宁 沈阳 联通",
  "date": "2021-09-29 00:00:00",
  "content": "哈哈哈"
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

### 站长删除留言（逻辑删除）
**URL:** http://59.110.218.235:8000/website/messageboard/adminDeleteMessageBoardList

**Type:** POST

**Author:** codezero

**Content-Type:** application/json; charset=utf-8

**Description:** 站长删除留言（逻辑删除）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int32|留言id|false|-
ip|string|留言ip|false|-
city|string|留言地址|false|-
date|string|留言时间|false|-
content|string|留言内容|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/website/messageboard/adminDeleteMessageBoardList --data '[
  {
    "id": 1,
    "ip": "127.0.0.1",
    "city": "中国 辽宁 沈阳 联通",
    "date": "2021-09-29 00:00:00",
    "content": "哈哈哈"
  }
]'
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

## 访客信息相关接口
### 获取访问数量信息
**URL:** http://59.110.218.235:8000/website/visitor/getVisitNum

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取访问数量信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/getVisitNum
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─total|number|总访客数|-
└─today|number|今日访客数|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "total": 100,
    "today": 10
  }
}
```

### 更新访问量信息
**URL:** http://59.110.218.235:8000/website/visitor/uptVisitNum

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 更新访问量信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ip|string|客户端ip|false|-
city|string|客户端所在地及网络运营商|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/uptVisitNum --data 'ip=187.57.122.216&city=s4hqpc'
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
  "data": "o5aiqr"
}
```

### 访客top5列表
**URL:** http://59.110.218.235:8000/website/visitor/topFiveList

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 访客top5列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/topFiveList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|array|响应对象|-
└─ip|string|访客ip|-
└─city|string|访客地址|-
└─total|int32|访问次数|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": [
    {
      "ip": "127.0.0.1",
      "city": "中国 辽宁 沈阳",
      "total": 100
    }
  ]
}
```

### 访客全部数据
**URL:** http://59.110.218.235:8000/website/visitor/visitorInfoList

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 访客全部数据

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/visitorInfoList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|array|响应对象|-
└─ip|string|IP|-
└─city|string|城市|-
└─total|int32|访问次数|-
└─lastTime|string|最后访问时间|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": [
    {
      "ip": "127.0.0.1",
      "city": "中国 辽宁 沈阳",
      "total": 100,
      "lastTime": "2021-09-26 09:36:21"
    }
  ]
}
```

### 每日访问数据列表（10天）
**URL:** http://59.110.218.235:8000/website/visitor/tenVisitorNumList

**Type:** POST

**Author:** codezero

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 每日访问数据列表（10天）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/tenVisitorNumList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─date|string|日期|-
└─total|int32|访客数量|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "date": "2021-09-09",
    "total": 100
  }
}
```


# CMS
## 网站内容管理服务
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



