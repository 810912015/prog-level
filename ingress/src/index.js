import React from 'react';
import ReactDOM,{hydrate,render} from 'react-dom';
import './index.css';
import App from './App'
import {createExamStore} from './store/disStore'

const store=createExamStore();
const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<App store={store}/>,rootElement)
}else{
    render(<App store={store}/>,rootElement)
}

// ReactDOM.render(<App store={store}/>, document.getElementById('el'));


