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
          <Route path={"/login"} component={Login} key={"login"}/>
          <Route path={"/register"} component={Register} key={"register"}/>
          <Route path={"/reset"} component={Reset} key={"reset"}/>
          <Route path={"/translate"} component={Translator} key={"translate"}/>
          <Route path={"/upload"} component={UploaderExample} key={"upload"}/>
      </Switch>)
}