import React from 'react';
import ReactDOM,{hydrate,render} from 'react-dom';
import './index.css';
import LoginApp from './loginApp'
import {createExamStore} from './store/disStore'

const store=createExamStore();
const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<LoginApp store={store}/>,rootElement)
}else{
    render(<LoginApp store={store}/>,rootElement)
}

// ReactDOM.render(<App store={store}/>, document.getElementById('el'));


