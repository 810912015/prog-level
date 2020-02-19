import React from 'react'
import {withRouter, Route} from "react-router-dom";
import Login from '../../auth/login'
import Register from '../../auth/Regsiter'
import Reset from '../../auth/reset'
import Profile from '../../auth/Profile'
import {LoginHeader} from "../nav/header";

export class LoginEntry extends React.Component {
    componentWillMount() {
        if(window.location.pathname==="/login"||window.location.pathname==="/login.html"){
            if(!window.location.href.endsWith("login")&&window.location.href.indexOf("register")<0){
                window.location+='login'
            }
        }
    }

    render() {
        return (
            <>
                <LoginHeader/>
                <Route path="/login" component={Login} key={"login"}/>
                <Route path={"/register"} component={Register} key={"register"}/>
                <Route path={"/reset"} component={Reset} key={"reset"}/>
                <Route path={"/profile"} component={Profile}/>
            </>
        )
    }

}
