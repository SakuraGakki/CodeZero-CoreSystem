/**
 * 对象数组键去重值求和
 * @params arr=>符合格式的原始对象数组
 * @params key=>作为求和条件的对象属性名
 * @params val=>需要求和的对象属性名
 * @return 键值分别为name，value
 */
export function objArrayDuplicateSum(arr,key,val) {
    let newArr = Array.from(
        arr.reduce((dict,item)=>{
            if(dict.has(item[key])){
                dict.get(item[key]).push(item[val])
            }else{
                dict.set(item[key],[item[val]])
            }
            return dict
        },new Map())).map(item => ({name:item[0],value:item[1]}))
    for(let j=0,k=0;j<newArr.length;j++){
        if(j==k){
            let i=0
            newArr[j].value.forEach(function (item) {
                i+=item;
                newArr[j].value=i
            })
            k++
        }
    }
    return newArr
}

/**
 * 数组反向排序
 */
export function sort(arr){
    let temp = {}
    let length = arr.length
    for(let i=0;i<length/2;i++){
        temp = arr[i]
        arr[i] = arr[length-i-1]
        arr[length-i-1] = temp
    }
    return arr
}
