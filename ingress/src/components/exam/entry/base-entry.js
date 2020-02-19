
import {withRouter,Route} from "react-router-dom";
import {connect} from "react-redux";
import React, {Component} from "react";
import {Logo} from "../../common/icon";

export class Footer extends Component{
    render() {
        return (
            <div className={"container-fluid bg-secondary"}>
                <div className={"text-center p-3"} style={{color:'white'}}>
                    <Logo height={"25px"} width={"30px"}/>
                    <div style={{fontSize: '18px', display: 'inline'}}>分码网</div>
                    <div style={{fontSize: '16px', display: 'inline', paddingLeft: '15px'}}>在线编程,练习和考试</div>
                    <div style={{fontSize: '16px', display: 'inline', paddingLeft: '15px'}}>联系请邮件810912015@qq.com</div>
                    <div style={{fontSize: '18px', display: 'inline'}}>请使用谷歌浏览器获取最佳使用体验</div>
                </div>
            </div>
        )
    }
}

export const MainPage={
    teacher:"qlist",
    firm:"invite",
    admin:"user/all",
    login:"login"
}
MainPage.get=()=>{
    let p=window.location.pathname.substr(1);
    let url=null;
    for(let k in MainPage){
        if(p.startsWith(k)){
            url=MainPage[k];
            break;
        }
    }
    if(!url) url="qlist2";
    return url;
}

class BaseEntry extends Component{
    render(){

        return (
            <div  className={"mt-3"} style={{minHeight:'800px'}}>

                {this.props.children}

            </div>
        )
    }
}


const mapStateToProps=(state,ownProps)=>{
    const ld=state.login;

    return {ld}
}

export default withRouter(connect(mapStateToProps,{})(BaseEntry))
