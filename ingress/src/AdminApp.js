import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import AdminEntry from "./components/exam/entry/admin-entry";
import "@babel/polyfill";
import 'whatwg-fetch'


class AdminApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                  <AdminEntry/>
                </HashRouter>
            </Provider>
        )
    }

}


export default AdminApp;
