const HtmlWebPackPlugin = require("html-webpack-plugin");

module.exports = {
	module: {
		rules: [
			{
				test: /\.(js|jsx)$/,
				exclude: /node_modules/,
				use: {
					loader: "babel-loader"
				}
			},
			{
				test: /\.html$/,
				use: [
					{
						loader: "html-loader"
					}
				]
			},
			{
				test: /\.css$/,
				use: [
				  {
					loader: 'style-loader'
				  },
				  {
					loader: 'css-loader'
				  },
				  {
					loader: 'sass-loader'
				  }
				]
			},
			{
				test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
				// Limiting the size of the woff fonts breaks font-awesome ONLY for the extract text plugin
				// loader: "url?limit=10000"
				loader: "url-loader"
			},
			{
				test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/,
				loader: 'file-loader'
			}
		]
	},
	devtool: 'source-map',
	plugins: [
		new HtmlWebPackPlugin({
			template: "./src/index.html",
			filename: "./index.html"
		})
	]
};
