import React, {useEffect, useState,useRef} from 'react'
import {Form,Input,Button,Checkbox,Row,Col} from "antd";
import {post} from "./common/network";
import { QuestionCircleOutlined } from '@ant-design/icons';
import {Commence} from "./common/commence";

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
const tailLayout={
    wrapperCol:{
        offset:8,
        span:8
    }
}
export function Login(props) {
    const [form]=Form.useForm()
    const finish=values=>{
        console.log(values)
        post("/auth/login",{name:values.name,pwd:values.pwd,kapcha:""},(d)=>{
            console.log(d);
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



const formItemLayout = {
    labelCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 9,
        },
    },
    wrapperCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 6,
        },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 23,
            offset: 1,
        },
        sm: {
            span: 6,
            offset: 9,
        },
    },
};

const CountDown=(props)=>{
    return (
        <Commence tip={"发送验证码"} can={props.can}
            call={(setTip)=>{
                post("/auth/confirm",{email:props.email||""},(d)=>{
                    setTip(d.msg)
                })
            }}
           >
            {(can,tip,start)=>{
                return (<Button disabled={!can} onClick={start}>{tip}</Button>)
            }}
        </Commence>
    )
}
export const Register= () => {
    const [form] = Form.useForm();

    const [can,setCan]=useState(false)


    const onFinish = values => {
        console.log('Received values of form: ', values);
    };
    const valueChange=(changed,all)=>{
        let key="email"
        if(!(key in changed)) return;
        let e=form.getFieldError(key);
        let c=e.length===0
        if(c!==can){
            setCan(c)
        }
    }

    return (

        <Form
            {...formItemLayout}
            form={form}
            name="register"
            onFinish={onFinish}
            scrollToFirstError
            hideRequiredMark
            initialValues={{agreement:true}}
            onValuesChange={valueChange}
        >
            <Form.Item
                name="name"
                label="用户名"
                rules={[
                    {
                        required: true,
                        message: '用户名不能为空',
                    },
                ]}
            >
                <Input />
            </Form.Item>
            <Form.Item
                name="email"
                label="电子邮件"
                rules={[
                    {
                        type: 'email',
                        message: '电子邮件格式错误',
                    },
                    {
                        required: true,
                        message: '电子邮件不能为空',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                name="pwd"
                label="密码"
                rules={[
                    {
                        required: true,
                        message: '密码不能为空',
                    },
                ]}
                hasFeedback
            >
                <Input.Password />
            </Form.Item>

            <Form.Item
                name="pwd2"
                label="确认密码"
                dependencies={['pwd']}
                hasFeedback
                rules={[
                    {
                        required: true,
                        message: '密码不能为空',
                    },
                    ({ getFieldValue }) => ({
                        validator(rule, value) {
                            if (!value || getFieldValue('password') === value) {
                                return Promise.resolve();
                            }

                            return Promise.reject('密码不一致');
                        },
                    }),
                ]}
            >
                <Input.Password />
            </Form.Item>

            <Form.Item label="邮箱验证码" extra="填入邮箱收到的验证码">
                <Row gutter={8}>
                    <Col span={12}>
                        <Form.Item
                            name="confirm"
                            noStyle
                            rules={[
                                {
                                    required: true,
                                    message: '填入邮箱收到的验证码',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <CountDown can={can} email={form.getFieldValue("email")||""}/>
                    </Col>
                </Row>
            </Form.Item>

            <Form.Item
                name="agreement"
                valuePropName="checked"
                rules={[
                    {
                        validator: (_, value) =>
                            value ? Promise.resolve() : Promise.reject('应接受协议'),
                    },
                ]}
                {...tailFormItemLayout}
            >
                <Checkbox>
                    已阅读并同意 <a href="">服务协议</a>
                </Checkbox>
            </Form.Item>
            <Form.Item {...tailFormItemLayout}>
                <Button type="primary" htmlType="submit">
                    注册
                </Button>
                <a href={"#/login"} style={{marginLeft:"100px"}}>登录</a>
            </Form.Item>
        </Form>

    );
};

export const Reset=()=>{
   const [form]=Form.useForm();
   const onFinish=(values)=>{

   }
   const valueChange=()=>{

   }
   return (
       <Form
           {...formItemLayout}
           form={form}
           name="register"
           onFinish={onFinish}
           scrollToFirstError
           hideRequiredMark
           initialValues={{agreement:true}}
           onValuesChange={valueChange}
       >
           <Form.Item
               name="email"
               label="电子邮件"
               rules={[
                   {
                       type: 'email',
                       message: '电子邮件格式错误',
                   },
                   {
                       required: true,
                       message: '电子邮件不能为空',
                   },
               ]}
           >
               <Input />
           </Form.Item>

           <Form.Item
               name="pwd"
               label="新密码"
               rules={[
                   {
                       required: true,
                       message: '密码不能为空',
                   },
               ]}
               hasFeedback
           >
               <Input.Password />
           </Form.Item>

           <Form.Item
               name="pwd2"
               label="确认密码"
               dependencies={['pwd']}
               hasFeedback
               rules={[
                   {
                       required: true,
                       message: '密码不能为空',
                   },
                   ({ getFieldValue }) => ({
                       validator(rule, value) {
                           if (!value || getFieldValue('password') === value) {
                               return Promise.resolve();
                           }

                           return Promise.reject('密码不一致');
                       },
                   }),
               ]}
           >
               <Input.Password />
           </Form.Item>
           <Form.Item {...tailFormItemLayout}>
               <Button type="primary" htmlType="submit">
                   重置密码
               </Button>
               <a href={"#/login"} style={{marginLeft:"100px"}}>登录</a>
           </Form.Item>
       </Form>
   )
}