import React from "react";
import {Route, Switch} from "react-router-dom";
import {Take} from "../../component/take";
import {Login} from "../../component/auth/login";
import {Register} from "../../component/auth/register";
import {Reset} from "../../component/auth/reset";
import {Article} from "./article";
import {Translator} from "./translate";
import {UploaderExample} from "./uploader";

export function Pannel() {
  return (
      <Switch>
          <Route exact path={"/"} component={Article} key={"q"}/>
          <Route exact path={"/art/:id"} component={Article} key={"art"}/>
          <Route path="/pass/:id" component={Take} key={"pass"}/>
          <Route path={"/login"} component={AdminLogin} key={"login"}/>
          <Route path={"/register"} component={AdminRegister} key={"register"}/>
          <Route path={"/reset"} component={AdminReset} key={"reset"}/>
          <Route path={"/translate"} component={Translator} key={"translate"}/>
          <Route path={"/upload"} component={UploaderExample} key={"upload"}/>
      </Switch>)
}

export function AdminLogin(props) {
  return (
      <Login authUrl={"/admin/auth/login"} {...props}/>
  )
}
export function AdminRegister(props) {
  return (
      <Register authUrl={"/admin/auth/register"} {...props}/>
  )
}
export function AdminReset(props) {
  return (
      <Reset authUrl={"/admin/auth/reset"} {...props}/>
  )
}