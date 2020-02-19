import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import FirmEntry from "./components/exam/entry/firm-entry";
import "@babel/polyfill";
import 'whatwg-fetch'


class FirmApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                    <FirmEntry/>
                </HashRouter>
            </Provider>
        )
    }

}


export default FirmApp;
