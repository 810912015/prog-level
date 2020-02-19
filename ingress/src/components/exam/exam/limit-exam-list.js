import React,{Component} from 'react'

import {bound} from "./exam";
import {JPostData, post} from "../../common/util";
import {Link} from "react-router-dom";
import {Scroller} from "../../common/scroller";
import {EItem} from "./examList";


export class LeList extends Component{
    state={
        items:[]
    }
    componentWillMount() {
        this.get()
    }
    get=()=>{
        let bid=bound(this.state.items)
        post(new JPostData("/exam/all",{maxId:bid.maxId,minId:bid.minId,size:10},(d)=>{
            if(d&&d.length){
                let t=[];
                for(let i=0;i<this.state.items.length;i++){
                    t.push(this.state.items[i])
                }
                for(let i=0;i<d.length;i++){
                    t.push(d[i])
                }
                t.sort((a,b)=>a.id>b.id?-1:1)
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
    render(){
        let ia=[]
        for(let i=0;i<this.state.items.length;i++){
            let t=this.state.items[i];
            ia.push(
                <Link to={"/epass/"+t.id+"/"+t.name} className={"btn"} key={i}>
                <EItem  {...t}>
                </EItem>
                </Link>
            )
        }
        return (
            <div className={"container"}>
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
