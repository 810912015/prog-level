import React, {Component} from "react";
import {post, JPostData,upload,get} from "../../common/util";
import {EItem, ExamCollection} from "./examList";
import {Link, withRouter,Route} from "react-router-dom";
import {connect} from "react-redux";
import {mailReg} from "../../auth/Regsiter";
import {mergeArray} from "../../../reducer/question";
import PropTypes from 'prop-types'
import {Ctrl, BsList, BsInlineForm, TableHeader} from "../../common/bsInlineForm";
import {SimpleModal} from "../../common/Model";
import Dropzone from 'react-dropzone';


export class Invitee2 extends Component{
    state={
        id:this.props.match.params.id||0,
        msg:'',
        name:this.props.name||'',
        email:this.props.email||'',
        cat1:this.props.cat1||'',
        cat2:this.props.cat2||'',
        cat3:this.props.cat3||'',
        cat4:this.props.cat4||'',
        cat5:this.props.cat5||'',
        remarks:this.props.remarks||''
    }

    componentWillMount() {
        if(this.state.id>0) {
            get({
                url: "/invitee/get/"+this.state.id,
                data:{},
                func:(dd)=>{
                    if(!!dd) {
                        this.setState({
                            id: dd.id,
                            name: dd.name,
                            email: dd.email,
                            cat1: dd.cat1,
                            cat2: dd.cat2,
                            cat3: dd.cat3,
                            cat4: dd.cat4,
                            cat5: dd.cat5,
                            remarks: dd.remarks
                        })
                    }
                }
            })
        }
    }

    submit=(e)=>{
        let t=Object.assign({},e,{msg:''});
        this.setState(t,()=>{
            post({
                url:"/invitee/create",
                data:this.state,
                func:(dd)=>{

                    if(dd.success){
                        //this.setState({msg:'保存成功'})
                        this.props.history.push("/invitee/list")
                    }else{
                        this.setState({msg:dd.msg})
                    }
                }
            })
        })
    }
    render() {
        let ctrls=[
            new Ctrl("name","姓名",null,'text',this.state.name),
            new Ctrl("email","邮箱",(e)=>{
                if(!e||!mailReg.test(e)){
                    return "格式错误"
                }
            },'text',this.state.email),
            new Ctrl("cat1",'分类1',null,'text',this.state.cat1),
            new Ctrl("cat2",'分类2',null,'text',this.state.cat2),
            new Ctrl("cat3",'分类3',null,'text',this.state.cat3),
            new Ctrl('cat4','分类4',null,'text',this.state.cat4),
            new Ctrl('cat5','分类5',null,'text',this.state.cat5),
            new Ctrl('remarks','备注',null,'text',this.state.remarks)
        ]
        return (
            <div>
                <BsInlineForm ctrls={ctrls} submit={this.submit}>
                    <span>{this.state.msg}</span>
                </BsInlineForm>

            </div>
        )
    }
}

export class InviteeList extends React.Component{
    state={
        items:[],
        total:1,
        cur:1
    }
    componentWillMount() {
       this.init()
    }

    init=()=>{
        this.getData({query:{},pager:{cur:1,size:10}},d=>{
            this.setState({
                items:d.data,
                total:d.total,
                cur:d.cur
            })
        })
    }

    ctrls=[
        new Ctrl("name","姓名"),
        new Ctrl("email","邮箱"),
        new Ctrl("cat1",'分类1'),
        new Ctrl("cat2",'分类2'),
        new Ctrl("cat3",'分类3'),
        new Ctrl('cat4','分类4'),
        new Ctrl('cat5','分类5'),
        new Ctrl('remarks','备注')
    ]
    tableHeader=[
        new TableHeader("id","编号"),
        new TableHeader("name","姓名"),
        new TableHeader("email","邮箱"),
        new TableHeader("cat1",'分类1'),
        new TableHeader("cat2",'分类2'),
        new TableHeader("cat3",'分类3'),
        new TableHeader('cat4','分类4'),
        new TableHeader('cat5','分类5'),
        new TableHeader('remarks','备注')
    ]
    getData=(e,f)=>{
        post({
            url:"/invitee/list",
            data:e,
            func:(d)=>{
                f({
                    data:d.data,
                    cur:d.pager.cur,
                    total:d.pager.total,
                    size:d.pager.size
                })
            }
        })
    }
    del=(id)=>{
        post({
            url:"/invitee/del/"+id,
            data:{},
            func:(d)=>{
                console.log(d);
                this.init()
            }
        })
    }
    makeCmd=(t)=>{
        return (
            <div>
                <Link to={"/invitee/create/"+t.id}>修改</Link>
                <SimpleModal title={"确认删除"} cmdClass={"btn btn-link"} action={()=>this.del(t.id)}>
                    确认删除员工{t.name}吗?
                </SimpleModal>
            </div>
        )
    }
    render(){

        return (
            <BsList ctrl={this.ctrls} pager={true} header={this.tableHeader} getData={this.getData} size={10}
                    makeCmd={this.props.makeCmd||this.makeCmd} initItems={this.state.items} initTotal={this.state.total}
                    initCur={this.state.cur}
            />
        )
    }
}

export class Mgr extends React.Component{
    static propTypes={
        items:PropTypes.array.isRequired
    }
    render() {
        let l=this.props.items;
        let left=[],right=[];
        l.forEach((a,i)=>{
            left.push(
                <li className={"list-group-item"}  key={a.name}>
                    <Link to={a.url}>{a.name}</Link>
                </li>
            )
            right.push(
                <Route path={a.url2} key={a.name} component={a.component}/>
            )
        })
        return (
            <div className={"container-fluid"}>
                <div className={"row"}>
                    <div className={"col-sm-1"}>
                        <ul className={"list-group"}>
                            {left}
                        </ul>
                    </div>
                    <div className={"col-sm-11"}>
                        {right}
                    </div>
                </div>
            </div>
        )
    }
}

export class InviteeMgr extends React.Component{
    items=[
        {
            name:'人员列表',
            url:'/invitee/list',
            url2:'/invitee/list',
            component:InviteeList
        },
        {
            name:'新建人员',
            url:"/invitee/create/0",
            url2:"/invitee/create/:id",
            component:Invitee2
        },
        {
            name:'批量上传',
            url:'/invitee/upload',
            url2:'/invitee/upload',
            component:InviteeUpload
        }
    ]
    render() {
        return (
            <Mgr items={this.items}/>
        )
    }
}
export class InviteeUpload extends React.Component{
    state={
        msg:''
    }
    dropFile=(fs)=>{
         upload(
             "/invitee/upload",
             fs[0],
             d=>this.setState({msg:JSON.stringify(d)}),
             e=>this.setState({msg:e.toString()})

         )
    }
    render() {
        return (
            <div>
                <div className={"m-3"}>
                    <a className={"btn btn-link"} href={"/invitee/tpl"}>下载人员模板</a>
                </div>
                <div  style={{maxWidth:'600px'}} className={"border m-3"}>
                <Dropzone multiple={false} accept={".csv"} onDrop={this.dropFile}>
                    {({getRootProps, getInputProps}) => (
                        <section>
                            <div {...getRootProps()}>
                                <input {...getInputProps()} />
                                <div className={"p-3"}>
                                <div className={"p-1"}>选择或拖动文件到这里</div>
                                <div className={"p-1"}>{this.state.msg}</div>
                                </div>
                            </div>
                        </section>
                    )}
                </Dropzone>
                </div>

                {/*<div className="input-group mb-3" style={{maxWidth:'600px'}}>*/}
                    {/*<div className="custom-file">*/}
                        {/*<input type="file" className="custom-file-input" accept={".csv"}/>*/}
                            {/*<label className="custom-file-label" htmlFor="inputGroupFile02"*/}
                                   {/*aria-describedby="inputGroupFileAddon02">选择文件</label>*/}
                    {/*</div>*/}
                    {/*<div className="input-group-append">*/}
                        {/*<span className="input-group-text" id="inputGroupFileAddon02">上传</span>*/}
                    {/*</div>*/}
                {/*</div>*/}
            </div>
        )
    }
}




export class FirmInvite extends Component {
    state = {
        msg: '',
        cur: '',
        show: false,
        eid: 0,
        selected:{}
    }
    getCmd = (t) => {
        let cn = "btn btn-outline-primary"
        if (t.id === this.state.eid) {
            cn = "btn btn-primary"
        }
        return (<div>
            <button className={cn} onClick={() => this.openInvite(t)}>邀请</button>
        </div>)
    }
    openInvite = (t) => {
        this.setState({show: true, eid: t.id})
    }
    closeInvite = (t) => {
        this.setState({show: false, eid: 0})
    }
    change=(e,ex)=>{
        this.setState({msg:''})
        let c=ex.target.checked;
        if(c&&!(e.id in this.state.selected)){
            let t=Object.assign({},this.state.selected,{[e.id]:e})
            this.setState({selected:t})
        }
        if(!c && e.id in this.state.selected){
            let t=this.state.selected;
            delete(t[e.id]);
            this.setState({selected:t});
        }
    }
    del=(id)=>{
        if(id in this.state.selected){
            let t=this.state.selected;
            delete(t[id]);
            this.setState({selected:t});
        }
    }
    select=(e)=>{
        return (
            <div>
                <input type={"checkbox"} onChange={(ex)=>this.change(e,ex)}/>
            </div>
        )
    }
    invite=()=>{
        let il=[]
        for(let k in this.state.selected){
            il.push(this.state.selected[k])
        }
        if(this.state.eid===0||il.length===0){
            this.setState({msg:"请选择考试和考生"})
            return
        }

        post({
            url:'/firm/invite',
            data:{
                eid:this.state.eid,
                iidl:il
            },
            func:(d)=>{
                this.setState({msg:d.msg+(d.data||'')})
            }
        })
    }
    render() {
        let lcn = this.state.show ? "col-sm-4" : "";
        let ccn = this.state.show ? "col-sm-8 border-left" : "d-none";
        let std={}
        for(let k in this.state.selected){
            let v=this.state.selected[k]
            std[k]=(v.name||'')+v.email
        }
        return (
            <div className={"container-fluid"}>
                <div className={"row"}>
                    <div className={lcn}>
                        <ExamCollection getCmd={this.getCmd} containerClass={""} examUrl={"/exam/all"}/>
                    </div>
                    <div className={ccn}>
                        <button className={"btn btn-link float-right"} onClick={this.closeInvite}>X</button>
                        <div className={"border p-2"}>
                            <div>选择的人</div>
                            <Deletable list={std} del={this.del}/>
                            <div>
                                <button className={"btn btn-outline-success"} onClick={this.invite}>发送邀请</button>
                                {this.state.msg}
                            </div>
                        </div>
                        <InviteeList makeCmd={this.select}/>
                    </div>
                </div>
            </div>
        )
    }
}

export class Deletable extends React.Component{
    static propTypes={
        list:PropTypes.object.isRequired,
        del:PropTypes.func.isRequired
    }
    render() {
        let cl=[]
        for(let k in this.props.list){
            cl.push(
                <span key={k} className={"m-1 p-2 border d-inline-block"}>
                    {this.props.list[k]}
                    <span className={"ml-2"} style={{cursor: 'pointer'}} onClick={() => this.props.del(k)}>X</span>
                </span>
            )
        }
        return (
            <span className={"p-3"}>
                {cl}
            </span>
        )
    }
}

class Invite extends Component {
    state = {
        msg: {},
        cur: ''
    }
    getCmd = (t) => {
        let msg = this.state.msg[t.id]
        return (
            <div>
                <div className={"input-group mb-3"}>
                    <input type={"email"} className={"form-control"} placeholder={"输入邮箱"} onChange={this.change}/>
                    <div className="input-group-append">
                        <button className={"btn btn-outline-primary"} onClick={() => this.invite(t.id)}>邀请</button>
                    </div>
                </div>
                <span>{msg}</span>
            </div>
        )
    }

    change = (e) => {
        this.setState({cur: e.target.value})
    }
    invite = (id) => {
        let ue = this.state.cur;
        let vl = mailReg.test(ue);
        if (!ue || !vl) {
            this.setState({msg: {[id]: "必须是有效的邮箱"}})
            return;
        }
        let data = {
            id: 0,
            userId: this.props.ld.uid,
            vUserId: 0,
            uemail: ue,
            iid: '',
            done: 'F',
            eid: id,
            remarks: '',
            dclt: new Date()
        }
        post(new JPostData("/exam/invite", data, (d) => {
            this.setState({
                msg: {[id]: d.msg}
            })
        }))
    }

    render() {
        return (
            <ExamCollection getCmd={this.getCmd} examUrl={"/exam/all"}/>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;

    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(Invite))
