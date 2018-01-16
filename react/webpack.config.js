var path = require('path');

module.exports = {
    entry: './app.js',
    devtool: 'sourcemaps',
    // devtool: false,
    cache: true,
    output: {
        path: '../src/main/resources/static',
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js(x)?$/,
                loader: 'babel-loader',
                exclude: function (path) {
                    return (!!path.match(/node_modules/));
                }
            },
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['env', 'react']
                }
            }, {
                test: /\.(css)$/,
                loader: 'style-loader!css-loader'
            }, {
                test: /\.(less)$/,
                loader: 'style!css!less'
            },
            {
                // 图片加载器
                test: /\.(gif|jpg|png|woff|woff2|svg|eot|otf|ttf)\??.*$/,
                loader: 'url-loader?limit=4096&name=/images/[name].[ext]'
            }
        ]
    }
};