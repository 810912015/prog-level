import React, {Component} from "react";
import {Route, withRouter} from "react-router-dom";
import Exam from "../exam/exam";
import {EList} from "../exam/examList";
import {FirmInvite, InviteeMgr} from "../exam/invite";
import {connect} from "react-redux";
import BaseEntry,{Footer} from './base-entry'
import BusyIndicator from '../../common/busyIndicator'
import FirmHeader from "../nav/firm-header"
import {Judge} from "../exam/Judge";
import {FirmScore} from "../exam/firm-score";

export class FirmEntry extends Component{
    render(){
        return (
            <div>
                <FirmHeader/>
                <BusyIndicator/>
                <BaseEntry>
                    <Route path={"/invitee"} component={InviteeMgr} key={"invitee"}/>
                    <Route path="/exam/:id" component={Exam} key={"rexam"}/>

                    <Route path={"/exams"} component={EList} key={"elist"}/>
                    <Route path={"/invite"} component={FirmInvite} key={"invite"}/>
                    <Route path={"/judge"} component={Judge} key={"judge"}/>
                    <Route path={"/firm/score"} component={FirmScore} key={"firmScore"}/>
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

export default withRouter(connect(mapStateToProps,{})(FirmEntry))
