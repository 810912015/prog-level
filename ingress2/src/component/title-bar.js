import React from 'react'
import {Layout} from "antd";
import {Link} from "react-router-dom";
import {QuestionContext} from "./context";
import {Bi} from "./common/busy-indicator";


function Tb(props) {
    let c;
    if(props.loginName){
        c= (<>{props.loginName}</>)
    }else{
        c=(
            <>
            <Link to={"/login"}>登录</Link>
            <Link to={"/register"} style={{marginLeft:"20px"}}>注册</Link>
                </>
        )
    }
   return (

       <div style={{display:"flex"}}>
           <div  className={"main-brand"}>
               <a href={"#"}> 分码网</a>

           </div>
           <div className={"brand"}>
               一个你可以写代码的地方
           </div>
           <div className={"shortcut"}>
              {c}
           </div>
       </div>

   )
}

export function TitleBar() {
   return (
       <QuestionContext.Consumer>
           {
               (c)=><Tb {...c}/>
           }
       </QuestionContext.Consumer>
   )
}
