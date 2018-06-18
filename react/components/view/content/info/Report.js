import React from 'react';
import 'whatwg-fetch';
import ReactEcharts from "echarts-for-react";
import {pc_code} from "../../../config/pc_code";

require('echarts/map/js/china.js');
var provinces = ['shanghai', 'hebei', 'shanxi', 'neimenggu', 'liaoning', 'jilin', 'heilongjiang', 'jiangsu', 'zhejiang', 'anhui', 'fujian', 'jiangxi', 'shandong', 'henan', 'hubei', 'hunan', 'guangdong', 'guangxi', 'hainan', 'sichuan', 'guizhou', 'yunnan', 'xizang', 'shanxi1', 'gansu', 'qinghai', 'ningxia', 'xinjiang', 'beijing', 'tianjin', 'chongqing', 'xianggang', 'aomen'];
var provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];

export default class Report extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            totalLoading: false,
            totalOption: {},
            sexLoading: false,
            sexOption: {},
            HQCountryLoading: false,
            HQCountryOption: {data: [], selectData: {}, legendData: [],},
            LXCountryLoading: false,
            LXCountryOption: {data: [], selectData: {}, legendData: [],},
            QJ_HQCountryLoading: false,
            QJ_HQCountryOption: {data: [], selectData: {}, legendData: [],},
            QJ_LXCountryLoading: false,
            QJ_LXCountryOption: {data: [], selectData: {}, legendData: [],},
            Native_PlaceLoading: false,
            Native_PlaceOption: {lx: [], hq: []},
            Native_CityPlaceOption: {cityName: '', lx: [], hq: []},
        };
        this.colorList = ['#54C7FC', '#FFB54D', '#FF7566', '#44DB5E'];
        this.fetchTotal = this.fetchTotal.bind(this);
        this.getColor = this.getColor.bind(this);
        this.fetchSex = this.fetchSex.bind(this);
        this.getMaxValue = this.getMaxValue.bind(this);
        this.fetchHQCountry = this.fetchHQCountry.bind(this);
        this.fetchLXCountry = this.fetchLXCountry.bind(this);
        this.fetchQJ_HQCountry = this.fetchQJ_HQCountry.bind(this);
        this.fetchQJ_LXCountry = this.fetchQJ_LXCountry.bind(this);
        this.fetchNative_Place = this.fetchNative_Place.bind(this);
        this.generate = this.generate.bind(this);
        this.changeCity = this.changeCity.bind(this);
    }

    componentDidMount() {
        this.fetchTotal();
        this.fetchSex();
        this.fetchHQCountry();
        this.fetchLXCountry();
        this.fetchQJ_HQCountry();
        this.fetchQJ_LXCountry();
        this.fetchNative_Place();
    }

    fetchNative_Place() {
        this.setState({Native_PlaceLoading: true});
        fetch("/NativePlace", {
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

    fetchQJ_HQCountry() {
        this.setState({QJ_HQCountryLoading: true});
        fetch("/QJHQCountry", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                let data = {
                    data: [],
                    selectData: {},
                    legendData: [],
                };
                let i = 0;
                Object.keys(json).sort((a, b) => json[b] - json[a]).forEach(key => {
                    data.data.push({name: key, value: json[key]});
                    data.legendData.push(key);
                    data.selectData[key] = i < 10;
                    i++;
                });
                this.setState({
                    QJ_HQCountryOption: data,
                    QJ_HQCountryLoading: false
                })
            })
    }

    fetchQJ_LXCountry() {
        this.setState({QJ_LXCountryLoading: true});
        fetch("/QJLXCountry", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                let data = {
                    data: [],
                    selectData: {},
                    legendData: [],
                };
                let i = 0;
                Object.keys(json).sort((a, b) => json[b] - json[a]).forEach(key => {
                    data.data.push({name: key, value: json[key]});
                    data.legendData.push(key);
                    data.selectData[key] = i < 10;
                    i++;
                });
                this.setState({
                    QJ_LXCountryOption: data,
                    QJ_LXCountryLoading: false
                })
            })
    }

    fetchSex() {
        this.setState({sexLoading: true});
        fetch("/sex", {
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
        fetch("/total", {
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

    fetchHQCountry() {
        this.setState({HQCountryLoading: true});
        fetch("/HQCountry", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                let data = {
                    data: [],
                    selectData: {},
                    legendData: [],
                };
                let i = 0;
                Object.keys(json).sort((a, b) => json[b] - json[a]).forEach(key => {
                    data.data.push({name: key, value: json[key]});
                    data.legendData.push(key);
                    data.selectData[key] = i < 10;
                    i++;
                });
                this.setState({
                    HQCountryOption: data,
                    HQCountryLoading: false
                })
            })
    }

    fetchLXCountry() {
        this.setState({LXCountryLoading: true});
        fetch("/LXCountry", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                let data = {
                    data: [],
                    selectData: {},
                    legendData: [],
                };
                let i = 0;
                Object.keys(json).sort((a, b) => json[b] - json[a]).forEach(key => {
                    data.data.push({name: key, value: json[key]});
                    data.legendData.push(key);
                    data.selectData[key] = i < 10;
                    i++;
                });
                this.setState({
                    LXCountryOption: data,
                    LXCountryLoading: false
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
            let pinyin = provinces[provincesText.indexOf(e.name)];
            require(`echarts/map/js/province/${pinyin}.js`);
            let lxdata = [];
            let hqdata = [];

            let tmp = this.state.Native_PlaceOption.lx.filter(province => {
                return province.name === e.name
            })[0];
            if (tmp) {
                lxdata = tmp.child?tmp.child:[];
            }
            tmp = this.state.Native_PlaceOption.hq.filter(province => {
                return province.name === e.name
            })[0];
            if (tmp) {
                hqdata = tmp.child?tmp.child:[];
            }

            let province = pc_code.filter(city=>city.label.startsWith(e.name))[0];
            province.children.forEach(city=>{
                if(!hqdata.filter(a=>a.name===city)[0]){
                    hqdata.push({name:city.value})
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

        let HQCountryOption = {
            title: {
                text: '华侨现居住国或地区分布'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 1,
                data: this.state.HQCountryOption.legendData,
                selected: this.state.HQCountryOption.selectData,
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: this.state.HQCountryOption.data,
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

        let LXCountryOption = {
            title: {
                text: '留学生现居住国或地区分布'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 1,
                data: this.state.LXCountryOption.legendData,
                selected: this.state.LXCountryOption.selectData,
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: this.state.LXCountryOption.data,
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

        let QJ_HQCountryOption = {
            title: {
                text: '侨眷海外直系亲属居住国或地区分布'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 1,
                data: this.state.QJ_HQCountryOption.legendData,
                selected: this.state.QJ_HQCountryOption.selectData,
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: this.state.QJ_HQCountryOption.data,
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

        let QJ_LXCountryOption = {
            title: {
                text: '留学生家属海外直系亲属居住国或地区分布'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 1,
                data: this.state.QJ_LXCountryOption.legendData,
                selected: this.state.QJ_LXCountryOption.selectData,
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: this.state.QJ_LXCountryOption.data,
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

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_HQCountry'
                                                      option={HQCountryOption}
                                                      showLoading={this.state.HQCountryLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_LXCountry'
                                                      option={LXCountryOption}
                                                      showLoading={this.state.LXCountryLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_QJ_HQCountry'
                                                      option={QJ_HQCountryOption}
                                                      showLoading={this.state.QJ_HQCountryLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <ReactEcharts ref='echarts_QJ_LXCountry'
                                                      option={QJ_LXCountryOption}
                                                      showLoading={this.state.QJ_LXCountryLoading}
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}