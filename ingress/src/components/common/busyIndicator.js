import React, {Component} from 'react'
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {busy} from "./util";

class BusyIndicator extends Component{
    constructor(props){
        super(props)
        busy.register(this.change)
        this.state={
            show:false
        }
    }
    change=(e)=>{
        this.setState({show:e})
    }
    render() {
        let t=this.state.show?"alert alert-info alert-dismissible fade show"
            :"alert alert-info alert-dismissible fade hide";
        let s=this.state.show?{display:"block"}:{display:"none"};
        return (
            <div style={{position:'fixed',top:'85px',right:'10px',width:'400px'}}>
            <div className={t} style={s}>
                <strong>努力执行中...</strong>
            </div>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(BusyIndicator))
