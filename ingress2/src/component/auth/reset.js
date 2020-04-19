import {Button, Form, Input} from "antd";
import React from "react";
import {formItemLayout,tailFormItemLayout} from "./register";

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