const path = require("path");
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');

module.exports = merge(baseConfig, {
  output: {
    path: path.resolve(__dirname, "../../ReactJs.CMSWeb/wwwroot/scripts/local/dam/"),
    publicPath: "/scripts/local/dam/",
    filename: "dam-app.js",
    chunkFilename: "chunk-[name].[contenthash].dam-app.js",
  },
  devtool: 'inline-source-map'
});