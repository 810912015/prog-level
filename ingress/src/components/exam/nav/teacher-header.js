import React, {Component} from "react";
import BaseHeader from "./base-header";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";


class TeacherHeader extends Component {
    state = {
        navs:[
            {to:"/qlist",name:"试题"},
            {to:"/exams",name:"试卷"},
            {to:"/play",name:"练习"}
        ]
    }
    componentWillMount() {
        if(window.location.pathname.startsWith("/teacher")){
            this.props.history.push("/qlist");
        }
    }

    render() {
        return (
            <BaseHeader title={"出题"} navs={this.state.navs} bg={"bg-info"}/>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(TeacherHeader))
