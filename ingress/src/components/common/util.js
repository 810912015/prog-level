import React, {Component} from "react";
import PropTypes from 'prop-types';

export class Select extends Component{
    static propTypes={
        title:PropTypes.string.isRequired,
        id: PropTypes.string.isRequired,
        options:PropTypes.object.isRequired,
        change:PropTypes.func.isRequired,
        sel:PropTypes.oneOfType([PropTypes.number,PropTypes.string]).isRequired
    }
    state={
        value:this.props.sel||''
    }
    render() {
        let p=[];
        for(var k in this.props.options){
                p.push(
                    <option value={k} key={k}>{this.props.options[k]}</option>
                )
        }
        return (
            <div>
                <label>{this.props.title}</label>
                <select className={"custom-select"} value={this.state.value}
                        onChange={(e) => {
                            let v=e.target.value
                           this.setState({value:v},()=>{
                               this.props.change(v, this.props.id)
                           })
                        }}>
                    {p}
                </select>
            </div>
        )
    }
}
export function JPostData(url, data, func, errFunc) {
    this.url = url;
    this.data = data;
    this.func = func;
    this.errFunc = errFunc;
}

export const busy = {
    cur: false,
    cba: []
}
busy.set = function (b) {
    busy.cur = b;
    for (let i = 0; i < busy.cba.length; i++) {
        busy.cba[i](busy.cur)
    }
}
busy.register = (f) => {
    busy.cba.push(f);
}

export const upload = function (url, file, success, fail) {
    let filedata = new FormData();
    filedata.append('file', file, 'file');
    filedata.append('path', file.path);

    fetch(url, {
        method: 'POST',
        body: filedata
      }).then(res =>{
        return redirect(res,"upload")
    })
        .then(result => {
            success(result);
        }).catch((e)=>{
            fail(e);
    })
}

const redirect=(res,m)=>{

    if(res.redirected){
        console.log(m+"_redirect",res);
        let u=res.url.replace(".jsp",".html");
        window.location=u;
        return {}
    }
    return res.json()
}

export const get=(d)=>{
    busy.set(true)
    fetch(d.url,{
        method:'GET',
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    }).then(res=>{
       return redirect(res,"get")
    }).then(d1=>{
        d.func(d1);
        busy.set(false)
    })
        .catch(ex=>{
            if(typeof d.errFunc==='function') d.errFunc(ex);
            else console.log(ex);
            busy.set(false)
        })
}

export const post = (d) => {
    busy.set(true)
    fetch(d.url, {
        method: "POST",
        body: JSON.stringify(d.data),
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    }).then(res => {
       return redirect(res,'post')
    }).then(p => {
        d.func(p)
        busy.set(false)
    })
        .catch(ex => {
            if (d.errFunc) d.errFunc(ex);
            else console.log(ex);
            busy.set(false)
        })

}

export class CtrlItem extends Component {
    static propTypes = {
        value:PropTypes.oneOfType([PropTypes.string , PropTypes.number]),
        change: PropTypes.func.isRequired,
        label: PropTypes.string.isRequired
    }

    render() {
        let t = this.props.it;
        if (t == null) {
            t = "text";
        }
        return (
            <div className={"form-group"}>
                <label>{this.props.label}</label>
                <input className={"form-control"} type={t}
                       value={this.props.value}
                       onChange={this.props.change}
                />
            </div>
        )
    }
}

export class CtrlItem2 extends Component{
    static propTypes={
        value:PropTypes.oneOfType([PropTypes.string , PropTypes.number]),
        change: PropTypes.func.isRequired,
        label: PropTypes.string.isRequired,
        id:PropTypes.string.isRequired
    }
    render() {
        return (
            <CtrlItem label={this.props.label} value={this.props.value}
                      change={(e) => this.props.change(e, this.props.id)}
            />
        )
    }
}

export class Ta extends Component {
    state = {
        rows: this.props.rows
    }

    render() {
        return (
            <div className={"form-group"}>
                <label>{this.props.label}</label>
                <Ta2 {...this.props}
                >
                </Ta2>
            </div>
        )
    }

}

export class Ta2 extends Component {
    state = {
        rows: this.props.rows
    }
    onScroll = (e) => {
        this.setState({rows: this.state.rows + 1})
    }

    render() {
        return (
            <textarea rows={this.state.rows} className={"form-control"}
                      value={this.props.value}
                      onChange={this.props.change}
                      onScroll={this.onScroll} spellCheck={false}
            >
                </textarea>
        )
    }

}
