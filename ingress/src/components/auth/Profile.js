import {Link, withRouter} from "react-router-dom";
import React, {Component} from "react";
import {connect} from "react-redux";
import {CtrlItem,upload,post,JPostData,get} from "../common/util";
import DatePicker from 'react-datepicker';
import Dropzone from 'react-dropzone';

class Profile extends Component {
    state = {
        name: '',
        gender: '',
        birthday: new Date(),
        desc: '',
        imgmsg:'',
        imgurl:'',
    }
    componentWillMount() {
        get(new JPostData("/profile",null,(d)=>{

            if(d.success) {
                let dd={
                    name: d.data.name,
                    gender: d.data.gender,
                    birthday:d.data.birthday==null?new Date():new Date(d.data.birthday),
                    desc: d.data.desc,
                    imgurl: d.data.imgurl==null?"":d.data.imgurl
                }
                this.setState(dd)
            }else{
                this.props.history.push("/login");
            }
        },null))
    }

    change = (e, w) => {
        this.setState({[w]: e.target.value})
    }
    dropImage=(fs)=>{
        upload("/upload",fs[0],(d)=>{
            if(d.success){
                this.setState({
                    imgurl:d.data,
                    imgmsg:''
                })
            }else{
                this.setState({
                    imgmsg:d.msg
                })
            }
        },()=>{
            this.setState({
                imgmsg:'出错了...请重试'
            })
        })
    }
    bdChange=(e)=>{
        this.setState(
            {birthday: e})
    }
    save=()=>{
        let d=Object.assign({},this.state)
        delete d.imgmsg;
        post(new JPostData("/profile",d,(data)=>{
            if(data.success) {
                this.props.history.push("/qlist")
            }else if(data.msg==='请重新登录'){
                this.props.history.push("/login")
            }
        }))
    }
    render() {
        let img=null;
        if(this.state.imgurl&&this.state.imgurl.length>0){
            img=(
                <img src={this.state.imgurl} alt={"拖动或选择文件"}/>
            )
        }else{
            img=(
                <i>拖动或选择文件</i>
            )
        }
        return (
            <div className={"container p-3"}>
                <CtrlItem value={this.state.name} change={(e) => this.change(e, 'name')} label={"昵称"}/>
                <div className={"form-group"}>
                    <label>性别</label>
                    <select className={"form-control"} onChange={(e) => this.change(e, 'gender')}>
                        <option value={'男'}>男</option>
                        <option value={'女'}>女</option>
                    </select>
                </div>
                <div className={"form-group"}>
                    <label>出生日期</label>
                    <DatePicker dropdownMode={"select"} selected={this.state.birthday} className={"m-3"}
                                dateFormat="yyyy-MM-dd"
                                onChange={this.bdChange}/>
                </div>
                <div className={"form-group"}>
                    <label>头像</label>
                    <Dropzone multiple={false} accept={"image/*"} onDrop={this.dropImage}>
                        {({getRootProps, getInputProps}) => (
                            <section>
                                <div {...getRootProps()}>
                                    <input {...getInputProps()} />
                                    {img}
                                    <div>{this.state.imgmsg}</div>
                                </div>
                            </section>
                        )}
                    </Dropzone>


                </div>
                <CtrlItem value={this.state.desc} change={(e) => this.change(e, 'desc')} label={"个人简介"}/>
                <div className={"form-group"}>
                    <button className={"btn btn-outline-primary"} onClick={this.save}>保存</button>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(Profile))
