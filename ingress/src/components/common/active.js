import React from "react";
/*
高阶组件--活动按钮指示器
功能:指示多个按钮中当前活动的那个
参数:cn{active:活动样式(css类名,字符串),unactive(不活动样式)}
使用:
     1.render时调用calActive(index)计算样式,参数是按钮序号
     2.click时调用onActive(index)更新状态
 */
export function Active(C,cn) {
    return class extends React.Component {
        constructor(props){
            super(props)
            this.state={
                active:0
            }
            this.calActive = this.calActive.bind(this)
            this.onActive = this.onActive.bind(this)
        }
        calActive(index) {
            if (index === this.state.active) {
                return cn.active
            }
            return cn.unactive
        }

        onActive(index) {
            this.setState({
                active: index
            })
        }
        render(){
            return (
                <C calActive={this.calActive} onActive={this.onActive} {...this.props}/>
            )
        }
    }

}
