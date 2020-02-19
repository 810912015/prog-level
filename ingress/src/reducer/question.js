import {ALL_QUESTION, CREATE_QUESTION, DEL_QUESTION, MY_QUESTION} from "../action/question";
import {BaseReducer, CallReducer} from "./base-reducer";
import {LOGOUT} from "../action/cat";

export function findArray(a,judge) {
    for(let i=0;i<a.length;i++){
        if(judge(a[i],i)){
            return a[i]
        }
        return null;
    }
}

///合并数字,key相同保留新的,第二个数组认为是新的
export function mergeArray(b, a, getKey) {
    let r = [];
    if (a && a.length) {
        for (let i = 0; i < a.length; i++) {
            r.push(a[i])
        }
    }

    function contains(arr, el, fk) {
        for (let i = 0; i < arr.length; i++) {
            if (fk(arr[i] === fk(el))) {
                return true;
            }
        }
        return false;
    }

    if (b && b.length) {
        for (let i = 0; i < b.length; i++) {
            if (!contains(r, b[i], getKey)) {
                r.push(b[i]);
            }
        }
    }
    return r;
}

class AllQuestion extends BaseReducer {
    constructor() {
        super(ALL_QUESTION)
    }

    process(state, action) {
        if (!(action.pager && action.pager.length)) return state;
        let t = mergeArray(state.all, action.pager, a => a.id).sort((a,b)=>a.id>b.id?-1:1);
        return {all: t};
    }
}

class MyQuestion extends BaseReducer {
    constructor() {
        super(MY_QUESTION)
    }

    process(state, action) {
        let r = mergeArray(state.mine, action.list, i => i.id).sort((a,b)=>a.id>b.id?-1:1);

        return {mine: r}
    }
}

class WhenLogout extends BaseReducer {
    constructor() {
        super(LOGOUT)
    }

    process(state, action) {
        return {mine: []}
    }

}

class CreateQuestion extends BaseReducer {
    constructor() {
        super(CREATE_QUESTION)
    }

    process(state, action) {
        if (!action.data) {
            return state;
        }
        let a = []
        if (state.all) {
            for (let i = 0; i < state.all.length; i++) {
                let t = state.all[i]
                if (t.id === action.data.id) {
                    continue;
                }
                a.push(t);
            }
        }
        a.push(action.data)
        return {all: a}
    }
}

class DelQuestion extends BaseReducer {
    constructor() {
        super(DEL_QUESTION)
    }

    process(state, action) {
        let a = []
        if (state.all) {
            for (let i = 0; i < state.all.length; i++) {
                let t = state.all[i]
                if (t.id === action.id) {
                    continue;
                }
                a.push(t);
            }
        }
        return {all: a}

    }
}

const QUESTION_REDUCERS = [new AllQuestion(),
    new CreateQuestion(),
    new DelQuestion(),
    new MyQuestion(),
    new WhenLogout()]

export const questions = (state = {}, action) => {

    let r = CallReducer(QUESTION_REDUCERS)(state, action)
    return r;
}

