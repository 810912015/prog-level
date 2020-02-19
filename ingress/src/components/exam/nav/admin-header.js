import React, {Component} from "react";
import BaseHeader from "./base-header";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";


class AdminHeader extends Component {
    state = {
        navs:[
            {to:"/user/all",name:"用户"},
            {to:"/firm/list",name:'企业'}
        ]
    }
    componentWillMount() {
        if(window.location.pathname.startsWith("/admin")){
            this.props.history.push("/user/all");
        }
    }
    render() {
        return (
            <BaseHeader title={"管理"} navs={this.state.navs} bg={"bg-secondary"}/>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(AdminHeader))
