export const formatTime = (type) => {
  const date = new Date
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  
    switch(type){
      case "YYYY-MM-DD":
        return [year, month, day].map(formatNumber).join('-')
        break
      case "YYYY/MM/DD":
        return [year, month, day].map(formatNumber).join('/')
        break
      case "YYYY-MM-DD hh:mm:ss":
        return [year, month, day].map(formatNumber).join('-') +
        ' ' +
        [hour, minute, second].map(formatNumber).join(':')
        break
      case "YYYY/MM/DD hh:mm:ss":
        return [year, month, day].map(formatNumber).join('/') +
        ' ' +
        [hour, minute, second].map(formatNumber).join(':')
        break
        
    }
    
  
}

const formatNumber = (n) => {
  const s = n.toString()
  return s[1] ? s : '0' + s
}


module.exports = {
  formatTime
}
