import React, {useState} from "react";
import {Form,Input,Select,Button,Row,Col} from "antd";
import {post,get} from "../../component/common/network";
import {Link} from "react-router-dom";

const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 8 },
};
const tailLayout = {
    wrapperCol: { offset: 8, span: 8 },
};

export function ResShow(props) {
    if(!props){
        return null;
    }
    let l=null;
    if(props.data) {
        if(typeof props.data==="string"){
            l=(<div>{props.data}</div>)
        }else if (props.data.length) {
            let d = props.data[0];
            if (d.id) {
                l = (
                    <Row>
                        <Col>{d.name}</Col>
                        <Col>
                            <Link to={"/art/" + d.id} title={"编辑"} style={{padding: "0px 10px"}}>{"编辑"}</Link>
                        </Col>
                    </Row>
                )
            }
        }
    }
    return  (
        <div>
            <div>{props.code}</div>
            <div>{props.message}</div>
            {l}
           </div>
    )

}

export function Translating(props) {
    if(!props.sche||!props.sche.data||!props.sche.data.length) return null;
    let r=[]
    props.sche.data.forEach(a=>{
        r.push(
            <div>
                <a href={a}>{a}</a>
                <a style={{marginLeft:"10px"}} onClick={()=>props.translating(1,a)}>{"查询结果"}</a>
                <a style={{marginLeft:"10px"}} onClick={()=>props.translating(2,a)}>{"查询同步锁"}</a>
                <a style={{marginLeft:"10px"}} onClick={()=>props.translating(3,a)}>清除同步锁</a>
            </div>
        )
    })
    return (
        <div style={{marginTop:"10px"}}>
            <div>{props.title}</div>
            {r}
        </div>
    )
}

export function Translator(props) {
    const [res,setRes]=useState(null)
    const [form] = Form.useForm();
    const [sche,setSche]=useState(null)
    const [run,setRun]=useState(null)
    const onFinish=(v)=>{
       let p={...v}
       if(!p.type) p.type="c";
       post("/admin/translate/by-link",p,(d)=>{
           setRes(d)
       })
    }
    const makeCall=(tag)=>{
        let p={
            url:form.getFieldValue("url"),
            type:form.getFieldValue("type")
        }
        post ("/admin/translate/"+tag,p,(d)=>{
            setRes(d)
        })
    }
    const query=()=>{
        makeCall("result")
    }
    const clear=()=>{
        makeCall("clear")
    }
    const queryLock=()=>{
        makeCall("query-lock")
    }
    const queryScheduled=()=>{
        get("/admin/translate/scheduled",(d)=>{
            setSche(d);
        })
    }
    const running=()=>{
        get("/admin/translate/running",(d)=>{
            setRun(d);
        })
    }
    const translating=(op,a)=>{
        let p={url:a}
        let tag=op===1?"result":op===2?"query-lock":op===3?"clear":null;
        if(!tag) return;
        post("/admin/translate/"+tag,p,setRes)
    }
  return (
      <div>
      <Form
          {...layout}
          name="basic" form={form}
          initialValues={{ type: "c" }}
          onFinish={onFinish}
      >
          <Form.Item name="url" label="文章地址" rules={[{ required: true }]}>
              <Input />
          </Form.Item>
          <Form.Item name="type" label="地址类型">
              <Select
                  placeholder="地址类型"
                  allowClear
              >
                  <Select.Option value="c">单个文章</Select.Option>
                  <Select.Option value="s">聚合</Select.Option>

              </Select>
          </Form.Item>

          <Form.Item name="linkerContainerPattern" label="聚合链接模式" rules={[{ required: false }]}>
              <Input />
          </Form.Item>


          <Form.Item {...tailLayout}>
              <Button type="primary" htmlType="submit">
                  提交翻译
              </Button>
              <Button type="default" onClick={query}>
                  查询结果
              </Button>
              <Button type="default" danger={true} onClick={clear}>
                  清除同步锁
              </Button>
              <Button type="default"  onClick={queryLock}>
                  查询同步锁
              </Button>
              <Button type={"dashed"} danger={true} onClick={queryScheduled}>排队中</Button>
              <Button type={"dashed"} danger={true} onClick={running}>翻译中</Button>
          </Form.Item>
      </Form>
        <Row>
            <Col span={8} offset={8}>
                <ResShow {...res}/>
                <Translating sche={run} title={"翻译中"} translating={translating}/>
                <Translating sche={sche} title={"排队中"} translating={translating}/>
            </Col>
        </Row>
      </div>
  )
}
