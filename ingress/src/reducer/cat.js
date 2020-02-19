import {LOGIN,LOGOUT,REGISTRE,RESET} from "../action/cat";
import {BaseReducer,CallReducer} from "./base-reducer";
import {LOGIN_KEY} from "../store/disStore";


class LoginCatReducer extends BaseReducer{
    constructor(){
        super(LOGIN)
    }
    process(state,action){
        window.sessionStorage.setItem(LOGIN_KEY,JSON.stringify(action.data));
        return action.data;
    }
}

class LogoutReducer extends BaseReducer{
    constructor(){
        super(LOGOUT)
    }
    process(state, action) {
        window.sessionStorage.setItem(LOGIN_KEY,JSON.stringify(action.data));
        return action.data;
    }
}
class RegisterReducer extends BaseReducer{
    constructor(){
        super(REGISTRE)
    }
    process(state, action) {

        return action.data;
    }
}

class ResetReducer extends BaseReducer{
    constructor(){
        super(RESET)
    }
    process(state, action) {

        return action.data;
    }
}


const CAT_REDUCERS=[new LoginCatReducer(),new LogoutReducer(),new RegisterReducer(),new ResetReducer()]

export const login=(state={},action)=>{

    let r= CallReducer(CAT_REDUCERS)(state,action)

    return r;
}




