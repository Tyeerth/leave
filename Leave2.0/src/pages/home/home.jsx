import React, { Component } from 'react'
import { Row, Col, Card, Alert} from 'antd';
import ReactEcharts from 'echarts-for-react';

export default class Home extends Component {
    state = {
        sales_zhe: [5, 20, 36, 10, 10, 20],
        stores_zhe: [15, 120, 6, 40, 110, 20],
        sales_bing: [5, 20, 36, 10, 10, 20],
        stores_bing: [15, 120, 6, 40, 110, 20]
    }
    getOption_zhe = (sales, stores) => {
        return {
            tooltip: {},
            legend: {
                data: ['学生', '老师']
            },
            xAxis: {
                data: ["7月", "8月","9月", "10月", "11月", "12月"]
            },
            yAxis: {},
            series: [{
                name: '学生',
                type: 'line',
                data: sales
            },
            {
                name: '老师',
                type: 'line',
                data: stores
            }]
        };
    }
    getOption_bing = (sales, stores) => {
        return {
            title: {
                text: '请假人数统计',
                subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['7月', '8月', '9月', '10月', '11月']
            },
            series: [
                {
                    name: '请假人数',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [
                        { value: 335, name: '7月' },
                        { value: 310, name: '8月' },
                        { value: 234, name: '9月' },
                        { value: 135, name: '10月' },
                        { value: 1548, name:'11月' }
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        }
    }
    render() {
        const {sales_zhe,stores_zhe,sales_bing,stores_bing}=this.state;
        return (
          <div >
              <Row gutter={16}>
                    <Col span={6}>
                        <Card title="今日请假人数" bordered={false} style={{ background:  '#FFC1C1' }}>
                            10人
                       </Card>
                    </Col>
                    <Col span={6}>
                        <Card title="昨日请假人数" bordered={false} style={{ background: '#E6E6FA'  }}>
                            11人
                        </Card>
                    </Col>
                    <Col span={6}>
                        <Card title="本周请假人数" bordered={false} style={{ background: '#CAE1FF' }}>
                            12人
                        </Card>
                    </Col>
                    <Col span={6}>
                        <Card title="本月请假人数" bordered={false} style={{ background: '#F0FFF0' }}>
                            30人
                        </Card>
                    </Col>
                </Row>
              <Row style={{padding:50}}>
                   <Col span={12}>
                        <ReactEcharts option={this.getOption_zhe(sales_zhe, stores_zhe)} />
                   </Col>
                   <Col span={12}>
                        <ReactEcharts option={this.getOption_bing(sales_bing, stores_bing)} />
                   </Col>
               </Row>
                <Row>
                  <Col span={12} offset={1}>
                      <Alert
                          message="通知"
                          description="请提前返校人员尽快办理销假 !!!"
                          type="info"
                          showIcon
                      />
                  </Col>
                </Row>
              
          </div>
        )
    }
}
