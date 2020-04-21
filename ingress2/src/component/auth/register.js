import {Commence} from "../common/commence";
import {post} from "../common/network";
import {Button, Checkbox, Col, Form, Input, Row} from "antd";
import React, {useState} from "react";

export const formItemLayout = {
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
export const tailFormItemLayout = {
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

export const CountDown=(props)=>{
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
export const Register= (props) => {
    const [form] = Form.useForm();

    const [can,setCan]=useState(false)
    const [msgs,setMsgs]=useState({})

    const onFinish = values => {
        var ro={
            name:form.getFieldValue("name"),
            pwd:form.getFieldValue("pwd"),
            email:form.getFieldValue("email"),
            kapcha:"",
            confirm:form.getFieldValue("confirm")
        }
        post("/auth/register",ro,(d)=>{
            if(!d.success){
                setMsgs(d.msgs)
            }else{
                props.history.push("/login")
            }
        })
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
    const getVStatus=(key)=>{
        if(msgs&&(key in msgs)) return "error";
        return "success";
    }
    const getVHelp=(key)=>{
        if(msgs&&(key in msgs)) return msgs[key]
        return null;
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
                validateStatus={getVStatus("name")}
                help={getVHelp("name")}
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
                validateStatus={getVStatus("email")}
                help={getVHelp("email")}
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
                            if (!value || getFieldValue('pwd') === value) {
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
                            validateStatus={getVStatus("confirm")}
                            help={getVHelp("confirm")}
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
                    已阅读并同意 <a >服务协议</a>
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