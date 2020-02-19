import React,{Component} from 'react'
import PropTypes from 'prop-types'

//全高度下拉加载:高度动态计算
export class Scroller extends Component {
    static propTypes={
        more:PropTypes.bool.isRequired
    }
    constructor(props) {
        super(props);
        this.state = {
            scrollHeight: this.props.height||0,
            start: false,
            bottom: false
        }
    }

    componentDidMount() {

        if(this.state.scrollHeight===0) {
            this.setState({
                scrollHeight: window.innerHeight - this.header.clientHeight - 130
            })
        }
    }

    render() {
        return (
            <div>
                <div ref={ref => this.header = ref}></div>
                <FixedScroller height={this.state.scrollHeight} onBottom={this.props.onBottom} more={this.props.more}>
                    {this.props.children}
                </FixedScroller>
            </div>
        )
    }
}

//下拉加载组件:加载开始1.5秒提示结束,指定高度
export class FixedScroller extends Component {
    static propTypes={
        more:PropTypes.bool.isRequired
    }
    constructor(props) {
        super(props);
        this.state = {
            start: false
        }
    }

    handleScroll() {
        if(this.state.start) return;
        if (this.scrollDom.scrollTop + this.scrollDom.clientHeight >= this.scrollDom.scrollHeight) {
            this.setState({start:true})
            this.props.onBottom();
            let st=setTimeout(()=>{
                this.setState({start:false})
                clearTimeout(st)
            },1500)
        }
    }
    render() {
        let more=null
        if(this.props.more){
            more=(
                <div className={"border text-center p-1"}>
                    <span onClick={this.props.onBottom} style={{cursor:'pointer'}}>加载更多</span>
                </div>
            )
        }
        let d=this.state.start?"正在加载,请稍后...":""
        return (
            <div>
                <div
                    ref={body => this.scrollDom = body}
                    //style={{overflow: "auto", _webkitOverflowScrolling: 'touch', height: this.props.height}}
                    //onScroll={this.handleScroll.bind(this)}
                >
                    {this.props.children}
                </div>
                {more}
                <div>
                    {d}
                </div>
            </div>
        )
    }
}
