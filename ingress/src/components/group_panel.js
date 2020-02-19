import React from 'react'
import '../css/bootstrap.css';
import {Link, Route} from 'react-router-dom'
import {GroupList} from "./group/group-list"

import {Relation} from "./group/relation"
import {Upload} from "./group/upload"
import { Col2111Pannel } from "./containers"

 class Group extends React.Component {
    constructor(props){
        super(props)
        this.state={
            active:1
        }
        this.calActive=this.calActive.bind(this)
        this.onActive=this.onActive.bind(this)
    }
    calActive(index){
        if(index===this.state.active){
            return {backgroundColor:"#ace",width:'100%'}
        }
        return {width:'100%'}
    }
    onActive(index){
        this.setState({
            active:index
        })
    }
    render() {
        let s1=this.calActive(1);
        let s2=this.calActive(2);
        let s3=this.calActive(3);
        let left=(
            <div className="navbar-collapse"  style={{padding:'0px',margin:'0px'}}>
                <ul className="nav navbar-nav" >
                    <li style={s1} onClick={()=>this.onActive(1)}>
                        <Link to="/group/list">员工组</Link>
                    </li>
                    <li  style={s2} onClick={()=>this.onActive(2)}>
                        <Link to="/group/relation">从属关系</Link>
                    </li>
                    <li  style={s3} onClick={()=>this.onActive(3)}>
                        <Link to="/group/upload">数据导入</Link>
                    </li>
                </ul>
            </div>
        )
        let right=(
            <div>
                <Route path="/group/list" component={GroupList}/>
                <Route path="/group/relation" component={Relation}/>
                <Route path="/group/upload" component={Upload}/>
            </div>
        )
        return (
            <Col2111Pannel left={left} right={right}/>
        )
    }
}

export default Group;