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

export default class StuApply extends Component {
    render() {
        return (
            <div>
                <h1 style={{ paddingBottom: 20 }}><EditOutlined style={{ paddingRight: 10 }} />假条申请</h1>
                <Row>
                    <Col span={16} offset={2}>
                        <Form >
                            <Form.Item label="学号">
                                <Input />
                            </Form.Item>
                            <Form.Item label="姓名">
                                <Input />
                            </Form.Item>
                            <Form.Item label="级别">
                                <Select>
                                    <Select.Option value="16">16级</Select.Option>
                                    <Select.Option value="17">17级</Select.Option>
                                    <Select.Option value="18">18级</Select.Option>
                                    <Select.Option value="19">19级</Select.Option>
                                </Select>
                            </Form.Item>
                            <Form.Item label="班级">
                                <Input />
                            </Form.Item>
                            <Form.Item label="手机号">
                                <Input />
                            </Form.Item>
                            <Form.Item label="寝室">
                                <Cascader
                                    options={[
                                        {
                                            value: '16栋',
                                            label: '16栋',
                                            children: [
                                                {
                                                    value: '123',
                                                    label: '123',
                                                },
                                            ],
                                        },
                                        {
                                            value: '12栋',
                                            label: '12栋',
                                            children: [
                                                {
                                                    value: '456',
                                                    label: '456',
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
