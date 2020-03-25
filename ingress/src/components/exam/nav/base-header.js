import {Link, withRouter} from "react-router-dom";
import React, {Component} from "react";
import '../../../css/bootstrap.css';
import {connect} from "react-redux";
import {logout} from "../../../action/cat";
import {Active} from "../../common/active";
import NavDropdown from "react-bootstrap/es/NavDropdown";
import {Logo} from "../../common/icon";


class UserLink extends Component {
    render() {
        let un = window.userName;
        let c = null;
        if (!!un) {
            c = (
                <NavDropdown id={"user"} title={un}>
                    <NavDropdown.Item href={"/login/#/profile"}>个人中心</NavDropdown.Item>
                    <NavDropdown.Item onClick={this.props.logout}>退出{this.props.who}</NavDropdown.Item>
                </NavDropdown>
            )
        } else {
            c = (
                <NavDropdown id={"user"} title={''}>
                    <NavDropdown.Item href={"/login.html"}>登录</NavDropdown.Item>
                    <NavDropdown.Item href={"/login.html/#/register"}>注册</NavDropdown.Item>
                </NavDropdown>
            )
        }
        return c;
    }
}

class BaseHeader extends Component {
    state = {
        show: true
    }
    toggle = () => {

        this.setState({
            show: !this.state.show
        })
    }
    logout = () => {
        this.props.logout({
            url: "/logout",
            data: this.props.ld
        })
    }

    render() {


        let cn = this.state.show ? "collapse navbar-collapse" : "show"
        let ls = this.state.show ? {} : {marginRight: '10px'};
        let nl = [];
        for (let i = 0; i < this.props.navs.length; i++) {
            let t = this.props.navs[i]
            nl.push(
                <li className={this.props.calActive(i)}
                    onClick={() => this.props.onActive(i)}
                    style={ls} key={i}>
                    <Link to={t.to} className={"nav-link"} key={t.to}>{t.name}</Link>
                </li>
            )
        }
        if (this.props.links && this.props.links.length) {
            for (let i = 0; i < this.props.links.length; i++) {
                let t = this.props.links[i];
                nl.push(
                    <li className="nav-item" style={ls} key={i + 1000}>
                        <a href={t.href} className={"nav-link"}>{t.tip}</a>
                    </li>
                )
            }
        }

        let nc = "navbar navbar-expand-sm navbar-dark " + this.props.bg

        return (
            <div>
                <div className={"container"}>
                    <div className={"d-inline-block"}>
                        <Logo height={"25px"} width={"30px"}/>
                        <div style={{fontSize: '18px', display: 'inline'}}>分码网</div>
                        <div style={{fontSize: '9px', display: 'inline', paddingLeft: '15px'}}>在线编程,练习和考试</div>
                        <div style={{fontSize: '9px', display: 'inline', paddingLeft: '15px'}}>联系请邮件810912015@qq.com
                        </div>
                    </div>
                    <div className={"d-inline-block float-right"}>
                        <UserLink who={''} logout={this.logout}/>
                    </div>
                    <div className={"clearfix"}></div>
                </div>
                <nav className={nc}>
                    <div className="container">
                        <a className="navbar-brand" href="http://www.proglevel.com">
                            <span>
                                <Logo height={"33px"} width={"39px"}/>
                                {this.props.title}
                            </span>
                        </a>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" onClick={this.toggle}
                                data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className={cn}>
                            <ul className="navbar-nav" style={{flexDirection: "row"}}>
                                {nl}
                            </ul>
                        </div>

                    </div>
                </nav>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {logout})
(Active(BaseHeader, {
    active: 'nav-item bg-dark',
    unactive: 'nav-item'
})))
