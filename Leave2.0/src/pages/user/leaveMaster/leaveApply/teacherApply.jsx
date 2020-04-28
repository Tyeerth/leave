import React, { Component } from 'react'
import {
    Form,
    Input,
    Button,
    Select,
    Cascader,
    DatePicker,
    TreeSelect,
    Col,
    Row
} from 'antd';
import { EditOutlined } from '@ant-design/icons';

export default class TeacherApply extends Component {
    render() {
        return (
            <div>
                <h1 style={{paddingBottom:20}}><EditOutlined style={{paddingRight:10}}/>假条申请</h1>
                <Row>
                    <Col span={16} offset={2}>
                        <Form >
                            <Form.Item label="教职工号">
                                <Input />
                            </Form.Item>
                            <Form.Item label="姓名">
                                <Input />
                            </Form.Item>
                            <Form.Item label="学院">
                                <Select>
                                    <Select.Option value="16">信息工程学院</Select.Option>
                                    <Select.Option value="17">信息工程学院</Select.Option>
                                    <Select.Option value="18">信息工程学院</Select.Option>
                                    <Select.Option value="19">信息工程学院</Select.Option>
                                </Select>
                            </Form.Item>
                            
                            <Form.Item label="手机号">
                                <Input />
                            </Form.Item>
                            <Form.Item label="家庭地址">
                                <Cascader
                                    options={[
                                        {
                                            value: '赣州市',
                                            label: '赣州市',
                                            children: [
                                                {
                                                    value: '章贡区',
                                                    label: '章贡区',
                                                },
                                            ],
                                        },
                                        {
                                            value: '赣州市',
                                            label: '赣州市',
                                            children: [
                                                {
                                                    value: '滨溪花园',
                                                    label: '滨溪花园',
                                                },
                                            ],
                                        },
                                    ]}
                                />
                            </Form.Item>
                            <Form.Item label="请假去往地点">
                                <Cascader
                                    options={[
                                        {
                                            value: '江西',
                                            label: '江西',
                                            children: [
                                                {
                                                    value: '赣州',
                                                    label: '赣州',
                                                },
                                            ],
                                        },
                                        {
                                            value: '广东',
                                            label: '广东',
                                            children: [
                                                {
                                                    value: '广州',
                                                    label: '广州',
                                                },
                                            ],
                                        },
                                    ]}
                                />
                            </Form.Item>
                            <Form.Item label="请假开始时间">
                                <DatePicker />
                            </Form.Item>
                            <Form.Item label="请假结束时间">
                                <DatePicker />
                            </Form.Item>
                            <Form.Item label="TreeSelect">
                                <TreeSelect
                                    treeData={[
                                        { title: 'Light', value: 'light', children: [{ title: 'Bamboo', value: 'bamboo' }] },
                                    ]}
                                />
                            </Form.Item>
                            <Form.Item>
                                <Button type="primary">提交</Button>
                            </Form.Item>
                        </Form>
                    </Col>
                </Row>
            
            </div>
        )
    }
}
