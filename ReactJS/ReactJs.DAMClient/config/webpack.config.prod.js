const path = require("path");
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');
const TerserPlugin = require("terser-webpack-plugin");

module.exports = merge(baseConfig, {
  mode: "production",
  output: {
    path: path.resolve(__dirname, "../../ReactJs.CMSWeb/wwwroot/scripts/dist/dam/"),
    publicPath: "/scripts/dist/dam/",
    filename: "dam-app.min.js",
    chunkFilename: "chunk-[name].[contenthash].dam-app.min.js",
  },
  externals: {
    "react": "React",
    "react-dom": "ReactDOM",
    "react-bootstrap": "ReactBootstrap",
    "axios": "axios"
  },
  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()],
  }
});