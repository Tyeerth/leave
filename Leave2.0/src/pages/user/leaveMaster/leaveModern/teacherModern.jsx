import React, { Component } from 'react'
import { List,  Divider, Button, Tabs, Row, Col } from 'antd';
import { CopyOutlined } from '@ant-design/icons';
const { TabPane } = Tabs;

function callback(key) {
    console.log(key);
}

const data = [
    '您好，本人因..........(请假原因)需向您请假......天',
    '请假时间为：xxx年xxx月xxx日 至  xxx年xxx月xxx日',
    '前往地点是：xxxxxxxxxxxxxxx',
    '教职工号：xxxxxxxxx',
    '学院：xxxxxx',
    '联系方式：xxxxxx',
    '特此请假，恳请批准 !',
    '此致 !',
    '敬礼 !',
    '承诺人（请假人）：xxxx',
    '负责人签名：xxxx',
];


export default class TeacherModern extends Component {
    render() {
        return (
            <div>
                <h1><CopyOutlined style={{ paddingRight: 10 }} />假条模板</h1>
                <Tabs defaultActiveKey="1" onChange={callback}>
                    <TabPane tab="短期假条" key="1">
                        <Divider orientation="left"><CopyOutlined style={{paddingRight:10}}/>江西理工大学（教职工）短期请假模板</Divider>
                        <List
                            size="large"
                            header={<div>校领导：</div>}
                            footer={<div>2020年2月20日</div>}
                            bordered
                            dataSource={data}
                            renderItem={item => <List.Item>{item}</List.Item>} />
                   </TabPane>
                   <TabPane tab="长期假条" key="2">
                        <Divider orientation="left"><CopyOutlined style={{ paddingRight: 10 }}/>江西理工大学（教职工）长期请假模板</Divider>
                       <List
                           size="large"
                           header={<div>校领导：</div>}
                           footer={<div>2020年2月20日</div>}
                           bordered
                           dataSource={data}
                           renderItem={item => <List.Item>{item}</List.Item>}/>
                   </TabPane>
                </Tabs>,
                <Row>
                    <Col span={3} offset={22}>
                        <Button type="primary">打印</Button>
                    </Col>
                </Row>
            </div>
        )
    }
}
