import React, {useState} from "react";
import {Form,Input,Select,Button,Row,Col} from "antd";
import {post} from "../../component/common/network";
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
        if (props.data.length) {
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
        }else {
           l=(<div>{props.data}</div>)
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

export function Translator(props) {
    const [res,setRes]=useState(null)
    const [form] = Form.useForm();
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
          </Form.Item>
      </Form>
        <Row>
            <Col span={8} offset={8}>
                <ResShow {...res}/>
            </Col>
        </Row>
      </div>
  )
}