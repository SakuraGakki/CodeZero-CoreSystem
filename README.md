# CodeZero-CoreSystem
核心系统



# Website
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2021-9-9|available|CodeZero|desc



## 访客信息相关接口
### 获取访问数量信息
**URL:** http://59.110.218.235:8000/website/visitor/getVisitNum

**Type:** POST


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
curl -X POST -H 'token' -i http://59.110.218.235:8000/website/visitor/uptVisitNum --data 'city=0ah662&ip=166.50.124.204'
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
  "data": "2db8fe"
}
```


