import React from 'react';

import {createExamStore} from "./store/disStore";
import ReactDOM,{hydrate,render} from "react-dom";
import ToolsApp from "./ToolsApp"



const store=createExamStore();
//ReactDOM.render(<AdminApp store={store}/>, document.getElementById('el'));
const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<ToolsApp store={store}/>,rootElement)
}else{
    render(<ToolsApp store={store}/>,rootElement)
}
