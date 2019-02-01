import ReactEcharts from "echarts-for-react";
import {Table,Empty,Icon} from 'antd';
import React from 'react';

const {Column} = Table;

export default class PieModule extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};
        this.ref = Math.floor(Math.random() * 10000000);
    }

    render() {
        const {data, title, loading,keyName,valueName,maxPIE} = this.props;
        let PIEData;
        if (maxPIE){
            PIEData = data.slice(0,maxPIE);
        } else {
            PIEData = data;
        }
        let option = {
            title: {
                text: title
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: PIEData,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        return (
            data.length > 0 ?
                <div className="row">
                    <div className="col-md-8">
                        <ReactEcharts ref={this.ref}
                                      option={option}
                                      showLoading={loading}
                        />
                    </div>
                    <div className="col-md-4">
                        <Table
                            loading={loading}
                            scroll={{y: 300}}
                            pagination={false}
                            dataSource={data}
                        >
                            <Column
                                title={keyName}
                                dataIndex="name"
                                key="name"
                            />
                            <Column
                                title={valueName}
                                dataIndex="value"
                                key="value"
                                width="80px"
                                sorter={(a, b) => a.value - b.value}
                            />
                        </Table>
                    </div>
                </div>
                :
                <Empty/>
        )
    }
}