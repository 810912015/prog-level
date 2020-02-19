import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import TeacherEntry from "./components/exam/entry/teacher-entry";
import "@babel/polyfill";
import 'whatwg-fetch'


class TeacherApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                    <TeacherEntry/>
                </HashRouter>
            </Provider>
        )
    }

}


export default TeacherApp;
