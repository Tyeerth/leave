import React, { Component } from 'react'
import { Table, Button } from 'antd';
import { DeleteOutlined } from '@ant-design/icons';
const columns = [
    {
        title: '学号',
        dataIndex: 'stuID',
    },
    {
        title: '姓名',
        dataIndex: 'name',
    },
    {
        title: '班级',
        dataIndex: 'stuClass',
    },
    {
        title: '联系方式',
        dataIndex: 'phone',
    },
    {
        title: '请假开始时间',
        dataIndex: 'startTime',
    },
    {
        title: '请假结束时间',
        dataIndex: 'endTime',
    },
    {
        title: '前往地点',
        dataIndex: 'address',
    },
    {
        title: '状态',
        dataIndex: 'status',
    },
];
const data = [];
for (let i = 0; i < 46; i++) {
    data.push({
        key: i,
        stuID:1520182018,
        name: `Edward King ${i}`,
        stuClass:'英语181班',
        phone:'123456789',
        startTime:'2020-01-01',
        endTime:'2020-02-02',
        status:'未销假',
        address: `London, Park Lane no. ${i}`,
    });
}

export default class LeaveDel extends Component {
    state = {
        selectedRowKeys: [], // Check here to configure the default column
        loading: false,
    };
    start = () => {
        this.setState({ loading: true });
        // ajax request after empty completing
        setTimeout(() => {
            this.setState({
                selectedRowKeys: [],
                loading: false,
            });
        }, 1000);
    };

    onSelectChange = selectedRowKeys => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    };
    
    render() {
        const { loading, selectedRowKeys } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
        };
        const hasSelected = selectedRowKeys.length > 0;
        return (
            <div>
                <h1><DeleteOutlined style={{paddingRight:10}}/>假条注销</h1>
                <div style={{ marginBottom: 16 }}>
                    <Button type="primary" onClick={this.start} disabled={!hasSelected} loading={loading}>
                        Reload
                    </Button>
                    <span style={{ marginLeft: 8 }}>
                        {hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}
                    </span>
                </div>
                <Table rowSelection={rowSelection} columns={columns} dataSource={data}  />
            </div>
        );
    }
}
