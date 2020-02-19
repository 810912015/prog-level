/*
reducer基类:包装action的相等性判断
使用者应调用handler方法
子类应重写process方法
 */
export class BaseReducer {
    constructor(type){
        this.type=type;
    }
    handle(state={},action){
        if(action.type===this.type){
            return this.process(state,action)
        }
        return null;
    }
    process(state,action) {
        throw new Error("need implement")
    }
}

//高阶函数,用于调用reducer,参数是reducer类,返回值是reducer调用函数
export function CallReducer(rs) {
    return function (state,action) {
        let rr=state;
        for(let i=0;i<rs.length;i++){
            let r=rs[i];
            let tr=r.handle(state,action);
            if(tr!=null){
                rr=tr;
                break;
            }
        }
        return rr;
    }
}