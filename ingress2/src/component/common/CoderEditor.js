import React from 'react'
import PropTypes from 'prop-types'
//import MonacoEditor from './me'
const MonacoEditor = React.lazy(() => import('./me'))

export function getLang(v) {
    if(v==="c++"){
        return "cpp"
    }else if(v==="c#"){
        return "csharp"
    }
    return v;
}
const DefaultLangs=["c","c++","c#","java","javascript","go","python"];
export class LangChooser extends React.Component {
    static propTypes={
        langs:PropTypes.array,
        change:PropTypes.func,
        selected:PropTypes.string
    }
    static defaultProps={
        langs:DefaultLangs,

        selected:'java'
    }

    render() {
        let a = this.props.langs
        let ol = [];
        for (let i = 0; i < a.length; i++) {
            let t=a[i];
            let v=getLang(a[i]);

            ol.push(
                <option value={v} key={t}>{t}</option>
            )
        }
        return (
            <select style={{marginLeft: '20px', width: '150px'}}
                    onChange={(e)=>this.props.change(e.target.value)}
                    defaultValue={this.props.selected}
            >
                {ol}
            </select>
        )
    }
}

export class JavaCodeEditor extends React.Component {

    render() {
        const options = {
            selectOnLineNumbers: true,
            roundedSelection: false,
            readOnly: false,
            cursorStyle: 'line',
            automaticLayout: true,
            fontSize: 12,
            scrollBeyondLastLine: false,
            contextmenu: true,
            scrollbar: {
                vertical: 'hidden',
                horizontal: 'auto'
            }
        };


        return (
            <React.Suspense fallback={<div style={{marginLeft:"20px"}}>加载中...</div>}>
                <MonacoEditor
                    height={this.props.height || '600'}
                    value={this.props.value}
                    language={this.props.lang || "java"}
                    options={options}
                    onChange={this.props.change}
                />
            </React.Suspense>
        )
    }
}

export function isFunc(f) {
    return typeof f==='function';
}
export function noop() {

}
export class MultiLangEditor extends React.Component{
    static propTypes={
        sourceList:PropTypes.object.isRequired,
        sourceChange:PropTypes.func,
        curSourceChange:PropTypes.func,
        langInSource:PropTypes.bool,
        qid:PropTypes.oneOfType([PropTypes.number,PropTypes.string]).isRequired
    }
    static defaultProps={
        sourceList:{},
        langInSource:false,
        qid:0
    }

    constructor(props){
        super(props)
        let sl=this.getFirstLang(this.props.sourceList);
        this. state={
            lang:sl[0]||"",
            source:sl[1]||"",
            list:this.props.sourceList||{},
            set:false,
            qid:this.props.qid
        }

    }

    componentWillReceiveProps(nextProps, nextContext) {
        if(!this.state.set||this.state.qid!==nextProps.qid)
        {
            let js=JSON.stringify(nextProps.sourceList);
            if(js==="{}") return;
            let sl=this.getFirstLang(nextProps.sourceList)
            this.setState({
                set:true,
                qid:nextProps.qid,
                list: nextProps.sourceList,
                lang:sl[0]||"",
                source: sl[1]||""
            })
        }
    }
    getFirstLang=(list)=>{
        let sl;
        for(let t in list){
            sl=t;
            break;
        }
        return [sl,list[sl]];
    }
    langChange=(e)=>{
        let sc=this.state.list[e]||'';

        this.setState({
            lang:e,
            source:sc
        })

    }
    textChange=(e)=>{
        let l=Object.assign({},this.state.list)
        l[this.state.lang]=e;
        this.setState({
            source:e,
            list:l
        })
        if(isFunc(this.props.curSourceChange)){
            this.props.curSourceChange(e,this.state.lang);
        }
        if(isFunc(this.props.sourceChange)) {
            this.props.sourceChange(this.state.list)
        }
    }
    render() {

        let langs=[];
        if(this.props.langInSource){
            for(let k in this.props.sourceList){
                if(!!this.props.sourceList[k]) {
                    langs.push(k)
                }
            }
        }else{
            langs=DefaultLangs;
        }
        return (
            <div>
                <LangChooser langs={langs} change={this.langChange}/>
                <JavaCodeEditor value={this.state.source} lang={this.state.lang}
                                change={this.textChange}/>
            </div>
        )
    }
}
