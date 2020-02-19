import React, {Component} from "react";
import {Route, withRouter} from "react-router-dom";
import Make from "../question/make";
import QList from "../question/qlist";
import Exam from "../exam/exam";
import {EList} from "../exam/examList";
import Invite from "../exam/invite";
import {connect} from "react-redux";
import BaseEntry,{Footer} from './base-entry'
import BusyIndicator from '../../common/busyIndicator'
import TeacherHeader from "../nav/teacher-header"
import Pass from '../pass/pass'
import {Play} from "../pass/play";
import {Judge} from "../exam/Judge";

export class TeacherEntry extends Component{
    render(){
        return (
            <div>
                <TeacherHeader/>
                <BusyIndicator/>
                <BaseEntry >
                    <Route path={"/play"} component={Play} key={"play"}/>
                    <Route path="/pass/:id" component={Pass} key={"pass"}/>
                    <Route path="/make/:id" component={Make} key={"make"}/>
                    <Route path="/qlist" component={QList} key={"list"}/>
                    <Route path="/exam/:id" component={Exam} key={"rexam"}/>
                    <Route path={"/exams"} component={EList} key={"elist"}/>
                    <Route path={"/invite"} component={Invite} key={"invite"}/>
                    <Route path={"/judge"} component={Judge} key={"judge"}/>
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

export default withRouter(connect(mapStateToProps,{})(TeacherEntry))
