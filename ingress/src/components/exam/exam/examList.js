import React,{Component} from "react";
import {post,JPostData} from "../../common/util";
import {Link} from "react-router-dom";
import {bound} from "./exam";
import {Scroller} from "../../common/scroller";
import {mergeArray} from "../../../reducer/question";
import {SimpleModal} from "../../common/Model";
import PropTypes from 'prop-types'

export class EItem extends Component{
    render (){
        let cl=null
        if(this.props.children){
            cl=(
                <div className={"card-footer"}>
                    {this.props.children}
                </div>
            )
        }
        return (
            <div className={"card mt-3"}>
                <div className={"card-header"}>
                    {this.props.id} {this.props.name}
                </div>
                <div className={"card-body"}>
                    <p className={"card-text"}>
                        {this.props.title}
                    </p>
                    <p className={"card-text"}>
                        {this.props.duration}分钟  {this.props.category}
                        {this.props.qCount}题  {this.props.weight}分
                        开始时间:{new Date(this.props.start).toLocaleString()}
                    </p>
                </div>
                {cl}
            </div>
        )
    }
}

export class ExamCollection extends Component{
    static propTypes={
        getCmd:PropTypes.func.isRequired,
        containerClass:PropTypes.string,
        examUrl:PropTypes.string.isRequired
    }


    state={
        items:[]
    }
    componentWillMount() {
        this.get()
    }
    get=()=>{
        let bid=bound(this.state.items)
        post(new JPostData(this.props.examUrl,{maxId:bid.maxId,minId:bid.minId,size:10},(d)=>{
            if(d&&d.length){
                let t=mergeArray(d,this.state.items,a=>a.id).sort((a,b)=>a.id>b.id?-1:1);
                this.setState({items:t})
            }
        }))
    }
    del=(id)=>{
        post(new JPostData("/exam/delete/"+id,{},(d)=>{
            let t=[];
            for(let i=0;i<this.state.items.length;i++){
                let c=this.state.items[i]
                if(c.id==id){
                    continue;
                }
                t.push(c);
            }
            this.setState({items:t})
        }))
    }
    render() {
        let ia=[]
        for(let i=0;i<this.state.items.length;i++){
            let t=this.state.items[i];
            ia.push(
                <EItem key={i} {...t}>
                    {this.props.getCmd(t,this.del)}
                </EItem>
            )
        }
        let cn=this.props.containerClass||"container"
        let nl=null;
        if(window.location.pathname.indexOf('teacher')>-1){
            nl=(
                <Link to="/exam/0" className={"btn btn-link"}>我要组试卷</Link>
            )
        }
        return (

            <div className={cn}>
                {nl}
                <div>
                    <Scroller onBottom={this.get} more={ia.length>9}>
                        <div className={"card-columns"}>
                            {ia}
                        </div>
                    </Scroller>
                </div>
            </div>
        )
    }
}

export class EList extends Component{
    getCmd=(t,del)=>{
        return (
            <div className={"btn-group"}>
                <div className={"d-inline-block"} style={{marginTop:'-4px'}}>
                <Link to={"/exam/"+t.id} className={"nav-link"} key={t.id}>修改</Link>
                </div>
                <div className={"d-inline-block"}  style={{marginTop:'-4px'}}>
                    <Link to={"/judge?eid="+t.id} className={"nav-link"} key={t.id}>阅卷</Link>
                </div>
                <SimpleModal title={"确认删除"} cmdClass={"btn btn-link"} action={()=>del(t.id)}>
                    确认删除试卷{t.name}吗?
                </SimpleModal>
            </div>
        )
    }
    render() {
        return (
            <ExamCollection getCmd={this.getCmd} examUrl={"/exam/mine"}/>
        )
    }
}
