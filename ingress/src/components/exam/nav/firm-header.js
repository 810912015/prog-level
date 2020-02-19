import React, {Component} from "react";
import BaseHeader from "./base-header";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";


class FirmHeader extends Component {
    state = {
        navs:[
            {to:"/invite",name:"考试"},
            {to:"/exams",name:"试卷"},
            {to:"/invitee/list",name:'人员'},
            {to:"/firm/score",name:'成绩'}
        ]
    }
    componentWillMount() {
        if(window.location.pathname.startsWith("/firm")){
            this.props.history.push("/invite");
        }
    }
    render() {
        return (
            <BaseHeader title={"企业"} navs={this.state.navs} bg={"bg-success"}/>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(FirmHeader))
