const getNowFormatDate = function(type) {
    let date = new Date()
    let seperator1 = "-"
    let seperator2 = "/"
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let strDate = date.getDate()
    if (month >= 1 && month <= 9) {
        month = "0" + month
    }
    if (strDate >= 1 && strDate <= 9) {
        strDate = "0" + strDate
    }
    switch(type){
        case "YYYY-MM-DD":
            return year + seperator1 + month + seperator1 + strDate
        case "MM/DD":
            return month + seperator2 + strDate
        case "YYYY":
            return year
        case "MM":
            return month
        case "DD":
            return strDate
    }
}

const getNowFormatTime = function(type){
    let seperator = ":"
    let hour = new Date().getHours()
    let minute = new Date().getMinutes()
    if (minute < 10) {
        minute = '0' + minute
    }
    let second = new Date().getSeconds()
    if (second < 10) {
        second = '0' + second
    }
    switch(type){
        case "hh:mm:ss":
            return hour + seperator + minute + seperator + second
        case "hh:mm":
            return hour + seperator + minute
    }
}


//
export {
    getNowFormatDate,
    getNowFormatTime
}