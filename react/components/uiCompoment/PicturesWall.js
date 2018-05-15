import {Upload, Icon, Modal, message} from 'antd';
import React from 'react';

export default class PicturesWall extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            previewVisible: false,
            previewImage: '',
            file: {},
            fileList: [],
            value:'',
        };
        this.handlePreview = this.handlePreview.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handlePreview(file) {
        this.setState({
            previewImage: file.url || file.thumbUrl,
            previewVisible: true,
        });
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.value){
            this.setState({
                fileList: [
                    {
                        uid: -1,
                        name: nextProps.value,
                        status: 'done',
                        url: nextProps.value,
                    }
                ]
            });
        }
        this.setState({value:nextProps.value})

    }

    handleChange({fileList}) {
        let file = fileList[0];
        if (!file) {
            this.setState({fileList, value: ''})
            return;
        }
        if (file.status === 'done' && file.response.status > 0) {
            message.success("上传成功");
            if (this.props.onChange) {
                this.props.onChange(file.response.info);
            }
        } else if (file.status === 'error') {
            message.error("上传失败");
        } else if (file.response && file.response.status < 0) {
            message.error(file.response.info)
        }
        this.setState({fileList})

    }


    render() {
        const {previewVisible, previewImage, fileList} = this.state;
        const uploadButton = (
            <div>
                <Icon type="plus"/>
                <div className="ant-upload-text">上传</div>
            </div>
        );
        return (
            <div className="clearfix">
                <Upload
                    action="/fileUpload"
                    accept="image/jpg,image/png,image/jpeg"
                    listType="picture-card"
                    fileList={fileList}
                    onPreview={this.handlePreview}
                    onChange={this.handleChange}
                >
                    {fileList.length >= 1 ? null : uploadButton}
                </Upload>
                <Modal visible={previewVisible} footer={null} onCancel={e => this.setState({previewVisible: false})}>
                    <img style={{width: '100%'}} src={previewImage}/>
                </Modal>
            </div>
        );
    }
}