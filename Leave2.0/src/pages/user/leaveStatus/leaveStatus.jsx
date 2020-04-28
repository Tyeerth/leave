import React, { Component } from 'react'

import { Table } from 'antd';
import { DownOutlined, BarsOutlined } from '@ant-design/icons';
import {connect} from 'react-redux'

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
        title: '请假开始时间',
        dataIndex: 'start_time',
    },
    {
        title: '请假结束时间',
        dataIndex: 'end_time',
    },
    {
        title: '审批状态',
        dataIndex: 'status',
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
        stuID: `${i}2`,
        gender:'男',
        start_time:'2020-09-09',
        end_time:'2020-90-89',
        status:'待审核'
    });
}

export default class LeaveStatus extends Component {
    state = {
        bordered: true,
        pagination:{
            position: 'bottom' 
        },
        showHeader:true,
        rowSelection: {},
        hasData: true,
    };

    render() {
        const tableColumns = columns.map(item => ({ ...item, }));
        return (
            <div>
                <h1><BarsOutlined style={{paddingRight:10}}/>请假审批状态</h1>
                <Table
                    style={{paddingTop:20}}
                    {...this.state}
                    columns={tableColumns}
                    dataSource={data}/>
            </div>
        )
    }
}
