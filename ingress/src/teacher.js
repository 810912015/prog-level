import React from 'react';

import {createExamStore} from "./store/disStore";
import ReactDOM,{hydrate,render} from "react-dom";
import TeacherApp from './TeacherApp.js'



const store=createExamStore();
//ReactDOM.render(<TeacherApp store={store}/>, document.getElementById('el'));


const rootElement=document.getElementById("el");
if(rootElement.hasChildNodes()){
    hydrate(<TeacherApp store={store}/>,rootElement)
}else{
    render(<TeacherApp store={store}/>,rootElement)
}
