import { combineReducers } from 'redux'

import {login} from './cat'
import {questions} from "./question";


const appReducer=combineReducers({login,questions})

const rootReducer=(state,action)=>{
    return appReducer(state,action)
}

export default rootReducer;
