import {Route, withRouter} from "react-router-dom";
import Make from "../question/make";
import LimitQList from "../question/limit-qlist";
import React,{Component} from "react";
import Pass from "../pass/pass";

import Header from "../nav/header";
import Score from "../exam/score";
import {connect} from "react-redux";
import Exam from '../exam/exam'
import {LeList} from "../exam/limit-exam-list";
import Invite from "../exam/invite"
import BusyIndicator from '../../common/busyIndicator'
import BaseEntry,{Footer} from './base-entry'
import {Passes2} from "../pass/Passes";
import {Play} from "../pass/play";



export class Entry extends Component{
    componentWillMount() {
        if(window.location.pathname==="/"){
            this.props.history.push("/qlist2")
        }
    }

    render(){
        return (
            <div>
                <Header/>
                <BusyIndicator/>
                <BaseEntry needLogin={false}>
                    <Route path={"/play"} component={Play} key={"play"}/>
                    <Route path="/pass/:id" component={Pass} key={"pass"}/>
                    <Route path="/make/:id" component={Make} key={"make"}/>
                    <Route path="/qlist2" component={LimitQList} key={"list2"}/>
                    <Route path="/exam/:id" component={Exam} key={"rexam"}/>
                    <Route path="/score" component={Score} key={"rscore"}/>
                    <Route path={"/exams"} component={LeList} key={"elist"}/>
                    <Route path={"/invite"} component={Invite} key={"invite"}/>
                    <Route path={"/epass/:eid/:ename"} component={Passes2} key={"epass"}/>
                </BaseEntry>
                <Footer/>
            </div>
        )
    }
}


const mapStateToProps=(state,ownProps)=>{
    const ld=state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps,{})(Entry))

