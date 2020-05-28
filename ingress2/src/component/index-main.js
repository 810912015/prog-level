import React from 'react'
import {TagBag,QuestionBag,RecommendBag} from "./question";
import {Row,Col} from 'antd'
import {QuestionContext} from "./context";
import { Route, Switch} from 'react-router-dom'
import {Take} from "./take";
import {Login} from "./auth/login";
import {Register} from "./auth/register";
import {Reset} from "./auth/reset";
import {Papers} from "./article/paper";

function Qb() {
    return (
        <QuestionContext.Consumer>
            {(context)=><QuestionBag {...context}/>}
        </QuestionContext.Consumer>
    )
}

function IndexMain() {
    return (
        <Row>
            <Col xs={24} sm={24} md={{span: 3, offset: 2}}>
                <TagBag/>
                <img src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/qr.jpg"} alt={""}/>
            </Col>
            <Col xs={24} sm={24} md={13} style={{minHeight: "80%"}}>
               <Qb/>
            </Col>
            <Col xs={24} sm={24} md={6}>
                <img src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/code-search19.png"} style={{width:"100%",height:"auto"}} alt={""}/>
                {<RecommendBag/>}
            </Col>
        </Row>)
}

export function Ing() {
   return (
       <Switch>
           <Route exact path={"/"} component={IndexMain} key={"q"}/>
           <Route path={"/papers/:id"} component={Papers} key={"papers"}/>
           <Route path={"/papers"} component={Papers} key={"papers1"}/>
           <Route path="/pass/:id" component={Take} key={"pass"}/>
           <Route path={"/login"} component={Login} key={"login"}/>
           <Route path={"/register"} component={Register} key={"register"}/>
           <Route path={"/reset"} component={Reset} key={"reset"}/>
       </Switch>)
}
