var path = require('path');
const webpack = require('webpack');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    entry: './app.js',
    // devtool: 'sourcemaps',
    devtool: false,
    cache: true,
    output: {
        path: path.resolve(__dirname, "../src/main/resources/static"),
        filename: 'bundle.js'
    },
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        progress: true,
        port: 9090,
        proxy: {
            '*': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                secure: false
            }
        }
    },
    plugins: [
        new UglifyJSPlugin(),

        new webpack.optimize.AggressiveMergingPlugin(),//合并块
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: JSON.stringify(process.env.PRODUCTION),
            }
        }),
        new webpack.HotModuleReplacementPlugin()
    ],
    externals: {
        'echarts': 'window.echarts',
        'react': 'React',
        'react-dom': 'ReactDOM',
        'react-router-dom': 'ReactRouterDOM',
        'antd': 'antd',
    },
    resolve: {
        extensions: ['.js', '.jsx'],
        alias: {
            components: path.resolve(__dirname, './components')
        }
        // modules: [
        //     path.resolve('./components'),
        //     path.resolve('./node_modules')
        // ]
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
                loader: 'style-loader!css-loader!less-loader'
            },
            {
                // 图片加载器
                test: /\.(gif|jpg|png|woff|woff2|svg|eot|otf|ttf)\??.*$/,
                loader: 'url-loader?limit=512&name=/images/[name].[ext]'
            }
        ]
    }
}