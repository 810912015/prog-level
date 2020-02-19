import React from 'react'
import '../css/bootstrap.css';

export class Col248Pannel extends React.Component{
    render (){
        return (
            <div className={"container-fluid"}>
            <div className="row" >
                <div className="col-4"  style={{margin:0,padding:2}}>
                    <div>
                        {this.props.head}
                    </div>
                    <div style={{height:'780px',overflow:'auto'}}>
                        {this.props.left}
                    </div>
                </div>
                <div className="col-8"  style={{margin:0,padding:2}}>
                    <div style={{height:'840px',overflow:'auto'}}>
                        <div style={{marginTop:'30px'}}>
                    {this.props.right}
                        </div>
                    </div>
                </div>
            </div>
            </div>
        )
    }
}



export class Col2111Pannel extends React.Component{
    render(){
        return (
            <div className={"container-fluid"}>
            <div className="row">
                <div className="col-1" style={{margin:0,padding:0}}>
                   {this.props.left}
                </div>
                <div className="col-11"  style={{margin:0,padding:2}}>
                    {this.props.right}
                </div>
            </div>
            </div>
        )
    }

}