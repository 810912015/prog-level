import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import {LoginEntry} from "./components/exam/entry/login-entry";
import {RichEditor,LineEditor} from "./components/common/RichEditor";
import './css/bootstrap.css'
import "@babel/polyfill";
import 'whatwg-fetch'



class LoginApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                    <LoginEntry/>
                </HashRouter>
            </Provider>
        )
    }

}


export default LoginApp;
