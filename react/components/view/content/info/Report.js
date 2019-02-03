import React from 'react';
import 'whatwg-fetch';
import ReactEcharts from "echarts-for-react";
import PieModule from "components/uiCompoment/PieModule"
import {pc_code} from "../../../config/pc_code";
import {Select, message} from 'antd';

const Option = Select.Option;

require('echarts/map/js/china.js');
require('echarts/map/js/province/shanghai.js')
require('echarts/map/js/province/hebei.js')
require('echarts/map/js/province/shanxi.js')
require('echarts/map/js/province/neimenggu.js')
require('echarts/map/js/province/liaoning.js')
require('echarts/map/js/province/jilin.js')
require('echarts/map/js/province/heilongjiang.js')
require('echarts/map/js/province/jiangsu.js')
require('echarts/map/js/province/zhejiang.js')
require('echarts/map/js/province/anhui.js')
require('echarts/map/js/province/fujian.js')
require('echarts/map/js/province/jiangxi.js')
require('echarts/map/js/province/shandong.js')
require('echarts/map/js/province/henan.js')
require('echarts/map/js/province/hubei.js')
require('echarts/map/js/province/hunan.js')
require('echarts/map/js/province/guangdong.js')
require('echarts/map/js/province/guangxi.js')
require('echarts/map/js/province/hainan.js')
require('echarts/map/js/province/sichuan.js')
require('echarts/map/js/province/guizhou.js')
require('echarts/map/js/province/yunnan.js')
require('echarts/map/js/province/xizang.js')
require('echarts/map/js/province/shanxi1.js')
require('echarts/map/js/province/gansu.js')
require('echarts/map/js/province/qinghai.js')
require('echarts/map/js/province/ningxia.js')
require('echarts/map/js/province/xinjiang.js')
require('echarts/map/js/province/beijing.js')
require('echarts/map/js/province/tianjin.js')
require('echarts/map/js/province/chongqing.js')
require('echarts/map/js/province/xianggang.js')
require('echarts/map/js/province/aomen.js')
var provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];

export default class Report extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            totalLoading: false,
            totalOption: {},
            sexLoading: false,
            sexOption: {},
            Native_PlaceLoading: false,
            Native_PlaceOption: {lx: [], hq: []},
            Native_CityPlaceOption: {cityName: '', lx: [], hq: []},

            areaLoading: false,
            areaType: 'all',
            manager_area: '浙江省',
            areaData: [],

            liveLoading: false,
            liveData: [],
            liveType: 'all',
        };
        this.colorList = ['#54C7FC', '#FFB54D', '#FF7566', '#44DB5E'];
        this.fetchTotal = this.fetchTotal.bind(this);
        this.getColor = this.getColor.bind(this);
        this.fetchSex = this.fetchSex.bind(this);
        this.getMaxValue = this.getMaxValue.bind(this);
        this.fetchNative_Place = this.fetchNative_Place.bind(this);
        this.generate = this.generate.bind(this);
        this.changeCity = this.changeCity.bind(this);
        this.loadAreaData = this.loadAreaData.bind(this);
        this.loadLiveData = this.loadLiveData.bind(this);
    }

    componentDidMount() {
        this.fetchTotal();
        this.fetchSex();
        this.fetchNative_Place();
        this.loadAreaData(this.state.manager_area, this.state.areaType);
        this.loadLiveData(this.state.liveType)
    }

    fetchNative_Place() {
        this.setState({Native_PlaceLoading: true});
        fetch("/api/NativePlace", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                let data = {};
                data.hq = this.generate(json.hq);
                data.lx = this.generate(json.lx);
                this.setState({
                    Native_PlaceOption: data,
                    Native_PlaceLoading: false
                })
            })
    }

    generate(table) {
        let result = [];
        provincesText.forEach(e => {
            result.push({name: e});
        });
        Object.keys(table).forEach(key => {
            let nativePlace = key.split("/");
            if (nativePlace.length >= 2) {
                let provinceName = nativePlace[0].substring(0, 2);
                provinceName = provincesText.filter(e => e.startsWith(provinceName))[0];
                if (!provinceName) {
                    return
                }
                let cityName = nativePlace[1];

                let province = result.filter(e => {
                    return provinceName === e.name;
                })[0];

                if (province.value) {
                    province.value += table[key];
                    province.child.push({name: cityName, value: table[key]});
                } else {
                    province.value = table[key];
                    province.child = [{name: cityName, value: table[key]}];

                }
            } else {
                let provinceName = key.substring(0, 2);
                provinceName = provincesText.filter(e => e.startsWith(provinceName))[0];
                if (!provinceName) {
                    return
                }

                let province = result.filter(e => {
                    return provinceName === e.name;
                })[0];
                if (province.value) {
                    province.value += table[key];
                } else {
                    province.value = table[key];
                    province.child = [];
                }
            }
        });
        return result;
    }

    fetchSex() {
        this.setState({sexLoading: true});
        fetch("/api/sex", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                this.setState({
                    sexOption: json,
                    sexLoading: false
                })
            })
    }

    fetchTotal() {
        this.setState({totalLoading: true});
        fetch("/api/total", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                this.setState({
                    totalOption: json,
                    totalLoading: false
                })
            })
    }

    getColor(params) {
        return this.colorList[params.dataIndex]
    }

    componentWillReceiveProps(nextProps) {
    }

    getMaxValue(a, b) {
        let maxA = 250, maxB = 250;
        a.forEach(e => {
            if (e.value > maxA) {
                maxA = e.value;
            }
        });
        b.forEach(e => {
            if (e.value > maxB) {
                maxB = e.value;
            }
        });
        return maxA + maxB;
    }

    changeCity(e) {
        if (e.name) {
            // let pinyin = provinces[provincesText.indexOf(e.name)];
            // require(`echarts/map/js/province/${pinyin}.js`);
            let lxdata = [];
            let hqdata = [];

            let tmp = this.state.Native_PlaceOption.lx.filter(province => {
                return province.name === e.name
            })[0];
            if (tmp) {
                lxdata = tmp.child ? tmp.child : [];
            }
            tmp = this.state.Native_PlaceOption.hq.filter(province => {
                return province.name === e.name
            })[0];
            if (tmp) {
                hqdata = tmp.child ? tmp.child : [];
            }

            let province = pc_code.filter(city => city.label.startsWith(e.name))[0];
            province.children.forEach(city => {
                if (!hqdata.filter(a => a.name === city)[0]) {
                    hqdata.push({name: city.value})
                }
            });
            this.setState({
                Native_CityPlaceOption: {
                    cityName: e.name,
                    lx: lxdata,
                    hq: hqdata,
                }

            });
        }
    }

    loadAreaData(area, type) {
        this.setState({areaLoading: true});
        let formData = new FormData();
        formData.append("area", area);
        fetch(`/api/${type}AreaData`, {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json())
            .then(json => {
                this.setState({areaLoading: false})
                if (json.status < 0) {
                    message.error(json.info, 5)
                    return;
                }
                let tmpData = [];
                let data = [];
                Object.keys(json)
                    .forEach(key => {
                        let label;
                        let arr = key.split("/");
                        let index = arr.indexOf(this.state.manager_area) + 1;
                        if (arr[index]) {
                            label = arr[index];
                        } else {
                            label = this.state.manager_area;
                        }
                        if (tmpData[label]) {
                            tmpData[label] = tmpData[label] + json[key];
                        } else {
                            tmpData[label] = json[key];
                        }
                    });
                Object.keys(tmpData)
                    .sort((a, b) => tmpData[b] - tmpData[a])
                    .forEach(key=>{
                        data.push({name:key,value:tmpData[key]})
                    });
                this.setState({
                    areaData: data,
                })
            })
    }

    loadLiveData(type) {
        this.setState({liveLoading: true});
        fetch(`/api/${type}Country`, {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                this.setState({liveLoading: false});
                if (json.status < 0) {
                    message.error(json.info, 5)
                    return;
                }
                let data = [];
                Object.keys(json)
                    .sort((a, b) => json[b] - json[a])
                    .forEach(key => {
                        data.push({name: key, value: json[key]});
                    });
                this.setState({
                    liveData: data,
                })
            })
    }

    render() {
        let totalOption = {
            title: {
                text: '表单记录数量'
            },
            yAxis: [
                {
                    type: 'category',
                    data: Object.keys(this.state.totalOption)
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        show: true,
                        interval: 'auto',
                    },
                    show: true
                }
            ],
            series: [
                {
                    "name": "人数",
                    "type": "bar",
                    "data": Object.values(this.state.totalOption),
                    label: {
                        show: true,
                        position: 'right',
                    },
                    itemStyle: {
                        normal: {
                            color: this.getColor
                        }
                    }
                }
            ]
        };
        let sexOption = {
            title: {
                text: '性别'
            },
            yAxis: [
                {
                    type: 'category',
                    data: Object.keys(this.state.sexOption)
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        show: true,
                        interval: 'auto',
                    },
                    show: true
                }
            ],
            series: [
                {
                    "type": "bar",
                    "data": Object.values(this.state.sexOption),
                    label: {
                        show: true,
                        position: 'right',
                    },
                    itemStyle: {
                        normal: {
                            color: this.getColor
                        }
                    }
                }
            ]
        };

        let max = this.getMaxValue(this.state.Native_PlaceOption.hq, this.state.Native_PlaceOption.lx);
        let ChinaOption = {
            title: {
                text: '籍贯分布',
                left: 'center',
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['华侨', '留学生']
            },
            visualMap: {
                min: 0,
                max: max,
                left: 'left',
                top: 'bottom',
                calculable: true
            },
            series: [{
                name: '华侨',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }, data: this.state.Native_PlaceOption.hq
            },
                {
                    name: '留学生',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    }, data: this.state.Native_PlaceOption.lx
                }
            ]
        };
        let ProvinceOption = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['华侨', '留学生']
            },
            visualMap: {
                min: 0,
                max: max,
                left: 'right',
                top: 'bottom',
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    restore: {},
                }
            },
            series: [{
                name: '华侨',
                type: 'map',
                mapType: this.state.Native_CityPlaceOption.cityName,
                roam: true,
                label: {
                    // normal: {
                    //     show: true
                    // },
                    emphasis: {
                        show: true
                    }
                },
                data: this.state.Native_CityPlaceOption.hq
            }, {
                name: '留学生',
                type: 'map',
                roam: true,
                mapType: this.state.Native_CityPlaceOption.cityName,
                label: {
                    // normal: {
                    //     show: true
                    // },
                    emphasis: {
                        show: true
                    }
                },
                data: this.state.Native_CityPlaceOption.lx
            }
            ]
        };
        const {manager_area, areaType, liveType} = this.state;
        return (
            <div className="container-fluid">
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">报表</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_total'
                                                      option={totalOption}
                                                      showLoading={this.state.totalLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_sex'
                                                      option={sexOption}
                                                      showLoading={this.state.sexLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="row">
                                <div className="col-md-8">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_native_place'
                                                      option={ChinaOption}
                                                      style={{height: '550px'}}
                                                      onEvents={{"click": e => this.changeCity(e)}}
                                                      showLoading={this.state.Native_PlaceLoading}
                                        />
                                    </div>
                                </div>
                                <div className="col-md-4">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_native_place_province'
                                                      option={ProvinceOption}
                                                      style={{height: '550px'}}
                                                      showLoading={this.state.Native_PlaceLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <h3>所属侨联</h3>
                            <div className="row" style={{"margin-bottom": "20px"}}>
                                <div className="col-md-12">
                                    区域：
                                    <Select
                                        onChange={value => {
                                            this.setState({manager_area: value});
                                            this.loadAreaData(value, areaType)
                                        }}
                                        value={manager_area}
                                        dropdownMatchSelectWidth={false}
                                    >
                                        <Option value="浙江省">浙江省</Option>
                                        <Option value="杭州市">杭州市</Option>
                                        <Option value="宁波市">宁波市</Option>
                                        <Option value="温州市">温州市</Option>
                                        <Option value="绍兴市">绍兴市</Option>
                                        <Option value="湖州市">湖州市</Option>
                                        <Option value="嘉兴市">嘉兴市</Option>
                                        <Option value="金华市">金华市</Option>
                                        <Option value="衢州市">衢州市</Option>
                                        <Option value="台州市">台州市</Option>
                                        <Option value="丽水市">丽水市</Option>
                                        <Option value="舟山市">舟山市</Option>
                                    </Select>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    类型：
                                    <Select
                                        onChange={value => {
                                            this.setState({areaType: value});
                                            this.loadAreaData(manager_area, value);
                                        }}
                                        value={areaType}
                                        dropdownMatchSelectWidth={false}
                                    >
                                        <Option value="all">全部</Option>
                                        <Option value="HQ">华侨人员</Option>
                                        <Option value="LX">留学人员</Option>
                                        <Option value="QJLX">归侨侨眷</Option>
                                        <Option value="QJHQ">留学人员家属</Option>
                                    </Select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <PieModule
                                            data={this.state.areaData}
                                            // title={"所属侨联"}
                                            keyName={"区域"}
                                            valueName={"人数"}
                                            loading={this.state.areaLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>

                            <h3>现居住国或地区</h3>
                            <div className="row" style={{"margin-bottom": "20px"}}>
                                <div className="col-md-12">
                                    类型：
                                    <Select
                                        onChange={value => {
                                            this.setState({liveType: value});
                                            this.loadLiveData(value);
                                        }}
                                        value={liveType}
                                        dropdownMatchSelectWidth={false}
                                    >
                                        <Option value="all">全部</Option>
                                        <Option value="HQ">华侨人员</Option>
                                        <Option value="LX">留学人员</Option>
                                        <Option value="QJLX">归侨侨眷</Option>
                                        <Option value="QJHQ">留学人员家属</Option>
                                    </Select>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <PieModule
                                            data={this.state.liveData}
                                            // title={"现居住国或地区"}
                                            keyName={"区域"}
                                            maxPIE={10}
                                            valueName={"人数"}
                                            loading={this.state.liveLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}