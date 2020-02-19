
import React from 'react';
import {Provider} from "react-redux";
import {HashRouter} from "react-router-dom";
import {Passes} from "./components/exam/pass/Passes";
import "@babel/polyfill";
import 'whatwg-fetch'


class EtApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                    <Passes/>
                </HashRouter>
            </Provider>
        )
    }

}

export default EtApp;
