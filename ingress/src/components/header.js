import React, {Component} from 'react';
import '../css/bootstrap.css';
import {withRouter} from 'react-router-dom'
import {connect} from 'react-redux';
import {Link, Route} from 'react-router-dom'
import {Active} from "./common/active";

class Header extends Component {
    render() {
        let s1=this.props.calActive(1),s2=this.props.calActive(2),s3=this.props.calActive(3),s4=this.props.calActive(4)
        return (
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <a className="navbar-brand" href="#">通用分配系统-{this.props.mid}</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className={s1} onClick={()=>this.props.onActive(1)}>
                            <Link to="/cat/rule" className={"nav-link"}>配置</Link>
                        </li>
                        <li  className={s2} onClick={()=>this.props.onActive(2)}>
                            <Link to="/group" className={"nav-link"}>员工</Link>
                        </li>

                        <li className={s3} onClick={()=>this.props.onActive(3)}>
                            <Link to="/monitor" className={"nav-link"}>监控</Link>
                        </li>

                        <li  className={s4} onClick={()=>this.props.onActive(4)}>
                            <Link to="/oplogs" className={"nav-link"}>日志</Link>
                        </li>
                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <li role="presentation">
                            <a className={"nav-link"}>
                                快速切换
                            </a>
                        </li>
                        <li role="presentation">
                            <a className={"nav-link"}>
                                首页
                            </a>
                        </li>

                        <li role="presentation">
                            <a className={"nav-link"}>
                                注销 {this.props.eid}
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }

}

const mapStateToProps=(state,ownProps)=>{
    const eid=state.eid;
    const mid=state.mid;

    return {eid,mid}
}

export default withRouter(connect(mapStateToProps,{})(Active(Header,{active:"nav-item bg-info",unactive:"nav-item"})))
