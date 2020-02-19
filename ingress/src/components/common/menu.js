import React from 'react'
import PropTypes from 'prop-types'
import {Link} from "react-router-dom";


export class MenuItem extends React.Component {
    static propTypes = {
        to: PropTypes.string,
        tip: PropTypes.string,
        link: PropTypes.bool,
        sub:PropTypes.object
    }
    render() {
        let s=null;
        let s1=null;
        let c = null;
        if(!!this.props.sub) {
            s='list-group-item m-0 p-0'
            s1='transparent'
            c=(
                <MenuItems {...this.props.sub}/>
            )
        }else{
            s='list-group-item'
            s1='#cec'
            if (this.props.link) {
                c = (
                    <Link to={this.props.to} style={{fontSize:16,fontWeight:'500'}}>{this.props.tip}</Link>
                )
            } else {
                c = (
                    <a href={this.props.to} style={{fontSize:16,fontWeight:'500'}}>{this.props.tip}</a>
                )
            }
        }
        return (
            <li className={s} style={{border: '0',backgroundColor:s1}}>
                {c}
            </li>
        )
    }
}

export class MenuItems extends React.Component {
    static propTypes = {
        header: PropTypes.string,
        items: PropTypes.array
    }
    state = {
        show: !this.props.header
    }

    render() {

        let st={
            display:'inline-block',
            color:'#fd7e14',
            transform: this.state.show?'rotate(-90deg)':'rotate(90deg)'
            ,fontSize:18,
            fontWeight:'500'
        }

        let h = null
        if (!!this.props.header) {
            h = (
                <div className={'card-header'} style={{border: 0,cursor:'point'
                    ,backgroundColor:'#aba'}}
                     onClick={() =>
                     {
                         this.setState({show: !this.state.show})}
                     }
                >
                    <span style={st}>&gt;</span>
                    <span className={'btn btn-link'} style={{fontSize:16,fontWeight:'500'}}>
                        {this.props.header}
                    </span>
                </div>
            )
        }
        let l = [];
        this.props.items.forEach((a,i) => {
            l.push(<MenuItem key={i} {...a}/>)
        })
        let s = 'card-text ' + (this.state.show ? 'd-block' : 'd-none');
        return (
            <div className={'card mt-0 pt-0'} style={{border: 0,backgroundColor:'transparent'}}>
                {h}
                <div className={s} style={{border: 0, marginLeft: '20px', marginTop: '-10px'}}>
                    <ul className={'list-group'}>
                        {l}
                    </ul>
                </div>
            </div>
        )
    }
}

export class Menu extends React.Component {
    static propTypes = {
        items: PropTypes.array.isRequired
    }

    render() {
        let l = []
        this.props.items.forEach((a, i) => {
            l.push(
                <MenuItems key={i} items={a.items} header={a.header}/>
            )
        })
        return (
            <div className={'p-1 mt-3'} >
                {l}
            </div>
        );
    }
}

export class SidebarContainer extends React.Component {
    static propTypes = {
        left: PropTypes.any.isRequired,
        right: PropTypes.any.isRequired
    }

    render() {
        return (
            <div className={"row"}>
                <div className={"col-xl-2 col-md-3 col-12 d-flex flex-column"}
                     style={{
                         position: 'sticky',
                         height: 'calc(100vh)',
                         marginTop: '-16px',
                         backgroundColor: '#dfd',

                         boxShadow: '0 1px 2px rgba(0,0,0,0.3)'
                     }}
                >
                    {this.props.left}
                </div>
                <div className={"col-xl-8 col-md-9 col-12"}>
                    {this.props.right}
                </div>
            </div>
        )
    }
}
