# CodeZero-CoreSystem
## 核心系统

# Website
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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/getMessageBoardList --data 'current=910&size=784'
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
    "pages": 869,
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
    "total": 819,
    "size": 310,
    "current": 184,
    "orders": [
      {
        "column": "f6itlu",
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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/insertMessageBoardInfo --data 'id=1&content=哈哈哈&ip=127.0.0.1&city=中国 辽宁 沈阳 联通&date=2021-09-29 00:00:00'
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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/insertMessageBoardSubInfo --data 'id=1&date=2021-09-29 00:00:00&ip=127.0.0.1&content=哈哈哈&city=中国 辽宁 沈阳 联通'
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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/messageboard/getMessageBoardUnreadList --data 'current=329&size=433'
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
    "pages": 237,
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
    "total": 756,
    "size": 832,
    "current": 85,
    "orders": [
      {
        "column": "8v9qxd",
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
  "current": 53,
  "size": 111,
  "keyword": "f7s664",
  "status": "qb9x80"
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
    "pages": 594,
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
    "total": 407,
    "size": 713,
    "current": 13,
    "orders": [
      {
        "column": "bsnpp2",
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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/uptVisitNum --data 'ip=205.36.170.53&city=4mrv7k'
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
  "data": "dp9vsu"
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
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2021-9-9|available|CodeZero|门户网站管理系统



## 
### 保存文章
**URL:** http://59.110.218.235:8000/cms/article/saveArticleInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 保存文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/saveArticleInfo --data '{"articleId":"0439012-4jkljkl3123-1231231","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","content":"<Type>LongText","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}'
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

### 文章列表查询（分页）
**URL:** http://59.110.218.235:8000/cms/article/getAllArticleInfosList

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 文章列表查询（分页）

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

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/getAllArticleInfosList --data '{
  "current": 303,
  "size": 837,
  "keyword": "qgropx"
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleId|string|文章ID|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleTitle|string|文章标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleTag|string|文章标签|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─readNum|int32|阅读数|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─status|string|状态|-
└─total|int64|总数|-
└─size|int64|每页显示条数，默认 10|-
└─current|int64|当前页|-
└─orders|array|排序字段信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─column|string|需要进行排序的字段|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─asc|boolean|是否正序排列，默认 true|-
└─optimizeCountSql|boolean|自动优化 COUNT SQL|-

**Response-example:**
```
{"status":0,"message":"Success","data":{"searchCount":true,"pages":22,"hitCount":true,"records":[{"articleId":"2931-djfk1-23112123jkldkfs","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}],"total":679,"size":370,"current":856,"orders":[{"column":"jso0y7","asc":true}],"optimizeCountSql":true}}
```

### 图片上传
**URL:** http://59.110.218.235:8000/cms/article/articleImageUpload

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
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/article/articleImageUpload
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

### 根据文章ID获取详情（cms）
**URL:** http://59.110.218.235:8000/cms/article/getArticleContentById

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 根据文章ID获取详情（cms）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/getArticleContentById --data '{"articleId":"0439012-4jkljkl3123-1231231","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","content":"<Type>LongText","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─articleId|string|文章ID|-
└─content|string|文章内容|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "articleId": "0439012-4jkljkl3123-1231231",
    "content": "<Type>LongText"
  }
}
```

### 根据文章ID获取详情（门户）
**URL:** http://59.110.218.235:8000/cms/article/articleContentById

**Type:** POST


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 根据文章ID获取详情（门户）

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/cms/article/articleContentById --data 'date=2021-10-10 12:12:33&articleTitle=文章标题&articleTag=科技,软件开发,系统&content=<Type>LongText&readNum=0&status=0&articleId=0439012-4jkljkl3123-1231231'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|object|响应对象|-
└─articleId|string|文章ID|-
└─content|string|文章内容|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": {
    "articleId": "0439012-4jkljkl3123-1231231",
    "content": "<Type>LongText"
  }
}
```

### 更新文章
**URL:** http://59.110.218.235:8000/cms/article/updateArticleInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 更新文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/updateArticleInfo --data '{"articleId":"0439012-4jkljkl3123-1231231","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","content":"<Type>LongText","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}'
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

### 发布文章
**URL:** http://59.110.218.235:8000/cms/article/publishArticle

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 发布文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/publishArticle --data '{"articleId":"0439012-4jkljkl3123-1231231","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","content":"<Type>LongText","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}'
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

### 下架文章
**URL:** http://59.110.218.235:8000/cms/article/cancelArticle

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 下架文章

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/article/cancelArticle --data '{"articleId":"0439012-4jkljkl3123-1231231","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","content":"<Type>LongText","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}'
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

### 已发布文章列表查询
**URL:** http://59.110.218.235:8000/cms/article/getPublishArticleInfosList

**Type:** POST


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 已发布文章列表查询

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-
keyword|string|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/cms/article/getPublishArticleInfosList --data 'current=994&keyword=sukrnb&size=782'
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleId|string|文章ID|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleTitle|string|文章标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─articleTag|string|文章标签|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─readNum|int32|阅读数|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─status|string|状态|-
└─total|int64|总数|-
└─size|int64|每页显示条数，默认 10|-
└─current|int64|当前页|-
└─orders|array|排序字段信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─column|string|需要进行排序的字段|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─asc|boolean|是否正序排列，默认 true|-
└─optimizeCountSql|boolean|自动优化 COUNT SQL|-

**Response-example:**
```
{"status":0,"message":"Success","data":{"searchCount":true,"pages":726,"hitCount":true,"records":[{"articleId":"2931-djfk1-23112123jkldkfs","articleTitle":"文章标题","articleTag":""科技","软件开发","系统"","readNum":0,"date":"2021-10-10 12:12:33","status":"0"}],"total":415,"size":645,"current":7,"orders":[{"column":"lie5ux","asc":true}],"optimizeCountSql":true}}
```

### 增加文章阅读数
**URL:** http://59.110.218.235:8000/cms/article/addArticleReadNum

**Type:** POST


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 增加文章阅读数

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
articleId|string|文章ID|false|-
articleTitle|string|文章标题|false|-
articleTag|string|文章标签|false|-
content|string|文章内容|false|-
readNum|int32|阅读数|false|-
date|string|发布时间|false|-
status|string|状态|false|-

**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/cms/article/addArticleReadNum --data 'date=2021-10-10 12:12:33&articleId=0439012-4jkljkl3123-1231231&articleTag=科技,软件开发,系统&status=0&articleTitle=文章标题&readNum=0&content=<Type>LongText'
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

## 
### 获取日常信息分页列表
**URL:** http://59.110.218.235:8000/cms/daily/getDailyInfoListPage

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 获取日常信息分页列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/daily/getDailyInfoListPage --data '{
  "current": 391,
  "size": 537
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─seq|string|日常ID|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|日常内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imageId|string|配图Id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imageUrl|string|配图地址|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─status|string|状态|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|时间|-
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
    "pages": 899,
    "hitCount": true,
    "records": [
      {
        "seq": "jdklf-fdadf-1231df-dfsd",
        "content": "今天天气真好",
        "imageId": "dfd1231231dfsd",
        "imageUrl": "http://www.baidu.com/123.jpg",
        "status": "1",
        "date": "2021-10-22 13:12:33"
      }
    ],
    "total": 707,
    "size": 286,
    "current": 889,
    "orders": [
      {
        "column": "65bdyd",
        "asc": true
      }
    ],
    "optimizeCountSql": true
  }
}
```

### 获取近期日常列表
**URL:** http://59.110.218.235:8000/cms/daily/getDailyInfoList

**Type:** POST


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取近期日常列表

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Request-example:**
```
curl -X POST -H 'token' -i http://59.110.218.235:8000/cms/daily/getDailyInfoList
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
status|int32|状态码|-
message|string|响应消息|-
data|array|响应对象|-
└─seq|string|日常ID|-
└─content|string|日常内容|-
└─imageId|string|配图Id|-
└─imageUrl|string|配图地址|-
└─status|string|状态|-
└─date|string|时间|-

**Response-example:**
```
{
  "status": 0,
  "message": "Success",
  "data": [
    {
      "seq": "jdklf-fdadf-1231df-dfsd",
      "content": "今天天气真好",
      "imageId": "dfd1231231dfsd",
      "imageUrl": "http://www.baidu.com/123.jpg",
      "status": "1",
      "date": "2021-10-22 13:12:33"
    }
  ]
}
```

### 图片上传
**URL:** http://59.110.218.235:8000/cms/daily/dailyImageUpload

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
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/daily/dailyImageUpload
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
**URL:** http://59.110.218.235:8000/cms/daily/dailyImageReplace

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
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/daily/dailyImageReplace --data 'imgId=115'
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

### 新增日常信息
**URL:** http://59.110.218.235:8000/cms/daily/addDailyInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 新增日常信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|日常ID|false|-
content|string|日常内容|false|-
imageId|string|配图Id|false|-
status|string|状态|false|-
date|string|时间|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/daily/addDailyInfo --data '{
  "seq": "jdklf-fdadf-1231df-dfsd",
  "content": "今天天气真好",
  "imageId": "dfd1231231dfsd",
  "status": "1",
  "date": "2021-10-22 13:12:33"
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

### 更新日常信息
**URL:** http://59.110.218.235:8000/cms/daily/uptDailyInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 更新日常信息

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|日常ID|false|-
content|string|日常内容|false|-
imageId|string|配图Id|false|-
status|string|状态|false|-
date|string|时间|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/daily/uptDailyInfo --data '{
  "seq": "jdklf-fdadf-1231df-dfsd",
  "content": "今天天气真好",
  "imageId": "dfd1231231dfsd",
  "status": "1",
  "date": "2021-10-22 13:12:33"
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

### 发布
**URL:** http://59.110.218.235:8000/cms/daily/publishDailyInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 发布

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|日常ID|false|-
content|string|日常内容|false|-
imageId|string|配图Id|false|-
status|string|状态|false|-
date|string|时间|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/daily/publishDailyInfo --data '{
  "seq": "jdklf-fdadf-1231df-dfsd",
  "content": "今天天气真好",
  "imageId": "dfd1231231dfsd",
  "status": "1",
  "date": "2021-10-22 13:12:33"
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

### 下架
**URL:** http://59.110.218.235:8000/cms/daily/cancelDailyInfo

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 下架

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
seq|string|日常ID|false|-
content|string|日常内容|false|-
imageId|string|配图Id|false|-
status|string|状态|false|-
date|string|时间|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/daily/cancelDailyInfo --data '{
  "seq": "jdklf-fdadf-1231df-dfsd",
  "content": "今天天气真好",
  "imageId": "dfd1231231dfsd",
  "status": "1",
  "date": "2021-10-22 13:12:33"
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
curl -X POST -H 'Content-Type: multipart/form-data' -H 'token' -i http://59.110.218.235:8000/cms/wechat/wechatImageReplace --data 'imgId=115'
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

### 
**URL:** http://59.110.218.235:8000/cms/wechat/getWechatArticleListPage

**Type:** POST


**Content-Type:** application/json; charset=utf-8

**Description:** 

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|desc|false|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
current|int64|No comments found.|false|-
size|int64|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -H 'token' -i http://59.110.218.235:8000/cms/wechat/getWechatArticleListPage --data '{
  "current": 648,
  "size": 605
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─seq|string|主键Id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─title|string|文章标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─url|string|文章链接|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imgId|string|图片Id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imgUrl|string|图片链接|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─date|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─read|int32|阅读量|-
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
    "pages": 801,
    "hitCount": true,
    "records": [
      {
        "seq": "jfajdlj12312lklsd",
        "title": "哈哈哈",
        "url": "http://www.baidu.com",
        "imgId": "1231231231fdsd",
        "imgUrl": "https://www.baidu.com/12312.png",
        "date": "2021-10-09 12:12:56",
        "read": 123
      }
    ],
    "total": 970,
    "size": 75,
    "current": 811,
    "orders": [
      {
        "column": "gaixf8",
        "asc": true
      }
    ],
    "optimizeCountSql": true
  }
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








