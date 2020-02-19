import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import BaseHeader from './base-header'

export class InviteHeader extends Component {
    render() {
        return (
            <div>
                <div className={"container"}>
                    <div className={"d-inline-block"}>
                        <div style={{fontSize: '18px', display: 'inline'}}>分码网</div>
                        <div style={{fontSize: '9px', display: 'inline', paddingLeft: '15px'}}>在线编程,练习和考试</div>
                    </div>

                    <div className={"clearfix"}></div>
                </div>
                <nav className="navbar navbar-expand-sm navbar-dark bg-info">
                    <div className={"container"}>
                    <a className="navbar-brand" href="#">参加考试</a>
                    </div>
                </nav>
            </div>
        )
    }
}
export class LoginHeader extends Component {
    state = {
        links: [
            {href: "/", tip: "主页"},
            {href: "/teacher.html", tip: "出题"}
            ]
    }

    render() {
        return (
            <BaseHeader title={""} navs={[]} bg={"bg-primary"}
                        links={this.state.links}/>
        )
    }
}

class Header extends Component {
    state = {
        navs: [
            {to: "/qlist2", name: "试题"},
            {to: "/play", name: "练习"},
            {to: "/invite", name: "邀请"},
            {to: "/score", name: "成绩"},
        ]
    }

    render() {
        return (
            <BaseHeader title={""} navs={this.state.navs} bg={"bg-primary"}
                        links={[{href: "/teacher.html", tip: "出题"}]}/>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(Header))
