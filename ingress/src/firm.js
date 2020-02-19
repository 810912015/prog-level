import React from 'react';

import {createExamStore} from "./store/disStore";
import ReactDOM,{hydrate,render} from "react-dom";
import FirmApp from './FirmApp.js'



const store=createExamStore();
//ReactDOM.render(<FirmApp store={store}/>, document.getElementById('el'));


const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<FirmApp store={store}/>,rootElement)
}else{
    render(<FirmApp store={store}/>,rootElement)
}
