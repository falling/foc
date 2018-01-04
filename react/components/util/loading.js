import React from 'react';
import ReactLoading from 'react-loading';

export default class loading {
    render() {
        return (
            <div>
                <ReactLoading type='balls' color='#f00'/>
            </div>
        )
    }
}