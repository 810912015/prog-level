import React from 'react';

import {createExamStore} from "./store/disStore";
import ReactDOM,{hydrate,render} from "react-dom";
import EtApp from './EtApp.js'
//import AdminApp from "./admin";



const store=createExamStore();
//ReactDOM.render(<EtApp store={store}/>, document.getElementById('el'));

const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<EtApp store={store}/>,rootElement)
}else{
    render(<EtApp store={store}/>,rootElement)
}
