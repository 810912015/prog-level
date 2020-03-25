import React, {Component} from 'react';
import '../../../css/bootstrap.css';
import {JPostData,post,Ta2} from "../../common/util";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";

import ReactHtmlParser from 'react-html-parser'
import {MultiLangEditor,getLang} from "../../common/CoderEditor";
import {getSourceObjFromArray} from "../question/make";


class Result extends Component{
    render(){
        let bg=this.props.result.success?'badge badge-success':'badge badge-danger'
        let txt=this.props.result.success?'√':'X'
        return (
            <div className={'mb-1'}>
                <span>
                   <span className={bg}>
                       {txt}
                   </span>
                </span>
                <span className={"ml-3"}>{this.props.result.msg}</span>
            </div>
        )
    }
}

class Pass extends Component{
    constructor(props){
        super(props)
        this.state={
            id:this.props.match.params.id||this.props.id||0,
            name:'',
            title:'',
            skeleton:'',
            run:[],
            compile:null,
            show:false,
            gid:'',
            sourceObj:{},
            lang:'java',
        }
        if(this.state.id>0){
            post(new JPostData("/question/get/"+this.state.id,{},(d)=>{
                let so=getSourceObjFromArray(d.s);
                this.setState({
                    title:d.q.title,
                    name:d.q.name,
                    skeleton:d.q.source,
                    run:[],
                    compile:null,
                    show:false,
                    sourceObj:so
                })
            }))
        }

        this.run=this.run.bind(this)
        this.change=this.change.bind(this)
        this.save=this.save.bind(this)

    }

    componentWillReceiveProps(nextProps, nextContext) {

        if(nextProps.id>0&&this.state.id!==nextProps.id){
            post(new JPostData("/question/get/"+nextProps.id,{},(d)=>{
                let so=getSourceObjFromArray(d.s);
                this.setState({
                    id:nextProps.id,
                    title:d.q.title,
                    name:d.q.name,
                    skeleton:d.q.source,
                    run:[],
                    compile:null,
                    show:false,
                    sourceObj:so
                })
            }))
        }
    }


    save(){
        let hasNext=typeof this.props.done ==='function';
        post(new JPostData("/exam/done/"+this.state.gid,{},(d)=>{
            if(d.success&&!hasNext){
                this.props.history.push("/qlist2")
            }
        }))
        if(hasNext){
            this.props.done();
        }
    }
    change(e,l){
        this.setState({
            skeleton:e,
            lang:l
        })
    }
    run(){
        let nd={
            id:this.state.id,
            source:this.state.skeleton,
            action:"compile",
            eid:1,
            qid:1,
            lang:this.state.lang,
            uid:this.props.ld.uid,
            eiid:this.props.eiid||''
        }

        post({
            url:"/exam/pass",
            method:'POST',
            data:nd,
            func:(d)=>{
                this.setState({
                    compile:d.compile,
                    run:d.test,
                    show:true,
                    gid:d.gid
                })
            }
        })
    }

    render(){
        let ra=null
        if(this.state.show){

            ra=[]

            if(this.state.compile) {
                ra.push(<Result result={this.state.compile} key={0}/>)
            }
            if(this.state.run) {
                for(let i=0;i<this.state.run.length;i++) {
                    ra.push(<Result result={this.state.run[i]} key={i+1}/>)
                }
            }
        }

        let enableSave=!(this.state.gid&&this.state.gid.length>0);
        let bg=this.state.show?'bg-light':'bg-transparent';
        return (
            <div className={"container-fluid"}>
                <div className={"row"}>
                    <div className={"col-sm-5"}>
                        <div className={"h5 text-center"}>
                            {this.state.name}
                        </div>
                        <div className={"border-right"}>{ReactHtmlParser(this.state.title)}</div>

                    </div>
                    <div className={"col-sm-7"}>
                        <div className={"p-3"}>
                            <MultiLangEditor sourceList={this.state.sourceObj}
                                             qid={this.state.id}
                                             langInSource={true}
                                             curSourceChange={(e,l)=>{this.change(e,l)}}
                            />
                        </div>
                        <div  >

                            <div className={"btn-group"}>
                                <button className={'btn btn-outline-primary'} onClick={this.run}>编译执行</button>
                                <button className={'btn btn-outline-info'} onClick={this.save} disabled={enableSave}>上传保存</button>
                            </div>

                            <div className={bg}  >
                                {ra}
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}


const mapStateToProps=(state,ownProps)=>{
    const ld=state.login;

    return {ld}
}

export default withRouter(connect(mapStateToProps,{})(Pass))


