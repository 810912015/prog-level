import React from 'react'
import {BsInlineForm, Ctrl, BsList, TableHeader} from "../../common/bsInlineForm";
import {Mgr} from "../exam/invite";
import {post,get} from "../../common/util";
import {Link} from "react-router-dom";
import {SimpleModal} from "../../common/Model";

export class FirmMgr extends React.Component{
    items=[
        {
            name:'新建企业',
            url:'/firm/create/0',
            url2:'/firm/create/:id',
            component:CreatFirm
        },
        {
            name:'企业列表',
            url:'/firm/list',
            url2:'/firm/list',
            component:ListFirm
        }
    ]
    render() {
        return (
            <Mgr items={this.items}/>
        )
    }
}

export class ListFirm extends React.Component{
    state={
        items:[]
    }
    componentWillMount() {
        this.getData({name:'',title:''},(d)=>this.setState({items:d.data}))
    }

    getData=(e,f)=>{
        post({
            url:"/firm/list",
            data:e,
            func:(data)=>{

                f({data:data})
            }
        })
    }
    del=(id)=>{
        post({
            url:"/firm/del/"+id,
            data:{},
            func:(data)=>{
                this.getData({},(d)=>this.setState({items:d.data}))
            }
        })
    }
    makeCmd=(t)=>{
        return (
            <div>
                <Link to={"/firm/create/"+t.id}>修改</Link>
                <SimpleModal title={"确认删除"} cmdClass={"btn btn-link"} action={()=>this.del(t.id)}>
                    确认删除企业{t.name}吗?
                </SimpleModal>
            </div>
        )
    }
    render(){
        return (
            <BsList ctrl={[
                new Ctrl("name","简称"),
                new Ctrl("title","全称")
            ]} pager={false}
                    header={[
                new TableHeader("id","编号"),
                new TableHeader("name","简称"),
                new TableHeader("title","全称"),
                new TableHeader("remarks","备注"),
                new TableHeader("dclt","输入时间",(e)=>new Date(e).toLocaleString())
            ]} getData={this.getData}
                    size={10}
                    makeCmd={this.makeCmd}
                    initItems={this.state.items}
            />
        )
    }
}


export class CreatFirm extends React.Component{
    state={
        id:this.props.match.params.id||0,
        name:'',
        title:'',
        remarks:''
    }
    componentWillMount() {
        if(this.state.id>0) {
            get({
                url: "/firm/get/" + this.state.id,
                func: (d) => {
                    this.setState({
                        id: d.id,
                        name: d.name,
                        title: d.title,
                        remarks: d.remarks
                    })
                }
            })
        }
    }

    submit=(e)=>{

        if(this.state.id>0){
            e.id=this.state.id
        }

        post({
            url:"/firm/create",
            data:e,
            func:(se)=>{

                if(se.success){
                    this.props.history.push("/firm/list");
                }
            }
        })
    }
    render() {
        let ctrls=[];
        ctrls.push(new Ctrl("name","简称",(e)=>{
            if(!e){
                return "不能为空"
            }
            if(e.length>15){
                return "不能超过15个字"
            }
        },"text",this.state.name));
        ctrls.push(new Ctrl("title","全称",(e)=>{
            if(!e){
                return "不能为空"
            }
            if(e.length>50){
                return "不能超过50个字"
            }
        },"textarea",this.state.title))
        ctrls.push(new Ctrl("remarks","简介",(e)=>{
            if(!!e&&e.length>200){
                return "不能超过200字"
            }
        },"textarea",this.state.remarks))
        return (
            <BsInlineForm ctrls={ctrls} submit={this.submit}/>
        )
    }
}
