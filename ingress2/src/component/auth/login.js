import React, {useEffect, useState,useRef} from 'react'
import {Form,Input,Button,Checkbox,Row,Col} from "antd";
import {post} from "../common/network";
import { QuestionCircleOutlined } from '@ant-design/icons';
import {Commence} from "../common/commence";
import {QuestionContext} from "../context";

export function CenterBox(props) {
    let totalMinHeight=props.totalMinHeight||"600px";
    let style=Object.assign({width:"50%",height:"300px",backgroundColor:"#eee"},props.style)
    return(
        <div style={{display:"flex",alignItems:"center",justifyContent:"center",minHeight:totalMinHeight}}>
            <div style={style}>
                {props.children}
            </div>
        </div>
    )
}
export function Login(props) {
  return(
      <QuestionContext.Consumer>
          {
              (c)=><Login2 {...c} {...props}/>
          }
      </QuestionContext.Consumer>
  )
}

function Login2(props) {
    const [form]=Form.useForm()
    const [msg,setMsg]=useState("")
    const finish=values=>{
        post("/auth/login",{name:values.name,pwd:values.pwd,kapcha:""},(d)=>{
            if(d.success){
                console.log(d)
                props.setLoginName(d.data.uname);
                props.history.push("/")
            }else{
                setMsg(d.msg)
            }
        })
    }
  return(
      <Form form={form}   wrapperCol={
          {sm:{offset:9,span:6},xs:{span:16}}
      } onFinish={finish}
          initialValues={{remember:true}}
      >
          <Form.Item name={"name"}
                     rules={[
                         {
                             required:true,
                             message:"用户名不能为空"
                         }
                     ]}
          >
              <Input placeholder={"请输入用户名"}/>
          </Form.Item>
          <Form.Item name={"pwd"} rules={[
              {
                  required:true,
                  message:"密码不能为空"
              }
          ]}>
              <Input.Password/>
          </Form.Item>
          <Form.Item name="remember" valuePropName="checked">
              <Checkbox>记住我</Checkbox>
              <a href={"#/reset"} style={{float:"right"}}>忘记密码？</a>
          </Form.Item>
          <Form.Item >
              <div><span style={{marginLeft:"20px"}}>{msg}</span></div>
              <Button htmlType="submit" type="primary"
                      style={{width:"100%"}}
              >登录</Button>

          </Form.Item>
          <Form.Item>
              <a href={"#/register"} type={"link"}
              >注册</a>
          </Form.Item>
      </Form>
  )
}




