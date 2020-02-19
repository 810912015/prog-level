import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import Entry from "./components/exam/entry/entry";
import {RichEditor,LineEditor} from "./components/common/RichEditor";
import {Play} from "./components/exam/pass/play";
import {MyformExample,BsListExample} from "./components/common/bsInlineForm";
import "@babel/polyfill";
import 'whatwg-fetch'



class App extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                  <Entry/>
                </HashRouter>
            </Provider>
        )
    }

}


export default App;
