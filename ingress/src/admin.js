import React from 'react';

import {createExamStore} from "./store/disStore";
import ReactDOM,{hydrate,render} from "react-dom";
import AdminApp from './AdminApp.js'



const store=createExamStore();
//ReactDOM.render(<AdminApp store={store}/>, document.getElementById('el'));
const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<AdminApp store={store}/>,rootElement)
}else{
    render(<AdminApp store={store}/>,rootElement)
}
