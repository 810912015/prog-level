import React, {Component} from "react";
import {Route, withRouter} from "react-router-dom";
import Exam from "../exam/exam";
import {EList} from "../exam/examList";
import {connect} from "react-redux";
import BaseEntry,{Footer} from './base-entry'
import BusyIndicator from '../../common/busyIndicator'
import AdminHeader from "../nav/admin-header"
import {UserMgr} from "../admin/user";
import {FirmMgr} from "../admin/firm";

export class AdminEntry extends Component{
    render(){
        return (
            <div>
                <AdminHeader/>
                <BusyIndicator/>
                <BaseEntry>
                    <Route path="/exam/:id" component={Exam} key={"rexam"}/>
                    <Route path={"/exams"} component={EList} key={"elist"}/>
                    <Route path={"/user"} component={UserMgr} key={"user"}/>
                    <Route path={"/firm"} component={FirmMgr} key={"firm"}/>
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

export default withRouter(connect(mapStateToProps,{})(AdminEntry))
