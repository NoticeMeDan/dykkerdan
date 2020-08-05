module.exports = {
    html: {
        theme: '#6F00FF',
        title: 'Dykkerdan'
    },
    build: {
        publicPath: '/',
        supportedBrowsers: ['last 1 Chrome versions'], // https://github.com/browserslist/browserslist
        mainBundleName: 'dykkerdan',
        vendorLibs: 'react|react-dom|@material-ui/core' // separated by pipes
    },
    proxy: {
        paths: ['/api', '/google'],
        port: 8080
    }
}
