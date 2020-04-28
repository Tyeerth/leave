import React, { Component } from 'react'
import { Table, Switch, Radio, Form } from 'antd';
import { DownOutlined, TeamOutlined } from '@ant-design/icons';

const columns = [
    {
        title: '姓名',
        dataIndex: 'name',
    },
    {
        title: '一卡通号',
        dataIndex: 'stuID',
        sorter: (a, b) => a.stuID - b.stuID,
    },
    {
        title: '性别',
        dataIndex: 'gender',
    },
    {
        title: '级别',
        dataIndex: 'grade',
        sorter: (a, b) => a.grade - b.grade,
    },
    {
        title: '班级',
        dataIndex: 'stuClass',
    },
    {
        title: '家庭地址',
        dataIndex: 'address',
        filters: [
            {
                text: 'London',
                value: 'London',
            },
            {
                text: 'New York',
                value: 'New York',
            },
        ],
        onFilter: (value, record) => record.address.indexOf(value) === 0,
    },
    {
        title: 'Action',
        key: 'action',
        sorter: true,
        filters: [],
        onFilter: () => { },
        render: () => (
            <span>
                <a style={{ marginRight: 16 }}>Delete</a>
                <a className="ant-dropdown-link">
                    More actions <DownOutlined />
                </a>
            </span>
        ),
    },
];

const data = [];
for (let i = 1; i <= 10; i++) {
    data.push({
        key: i,
        name: 'John Brown',
        stuID:'123456789',
        gender:'男',
        grade:'17级',
        stuClass:'信息171',
        address: `New York No. ${i} Lake Park`,
    });
}

const showHeader = true;
const pagination = { position: 'bottom' };

export default class UserInfo extends Component {
    state = {
        bordered: true,
        pagination,
        size: 'small',
        showHeader:showHeader,
        rowSelection: {},
        scroll: undefined,
        xScroll:'scroll',
        hasData: true,
        tableLayout: 'unset',
    };

    render() {
        const { xScroll, ...state } = this.state;

        const scroll = {};
        if (xScroll) {
            scroll.x = '100vw';
        }
        const tableColumns = columns.map(item => ({ ...item }));
        return (
            <div>
                <h1><TeamOutlined style={{paddingRight:10}}/>我校学生信息</h1>
                <Form
                    layout="inline"
                    className="components-table-demo-control-bar"
                    style={{ marginBottom: 16 ,paddingTop:10}}>
                </Form>
                <Table
                    {...this.state}
                    columns={tableColumns}
                    dataSource={state.hasData ? data : null}
                    scroll={scroll}/>
            </div>
        )
    }
}
