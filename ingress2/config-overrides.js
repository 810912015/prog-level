const { override, fixBabelImports,addLessLoader } = require('customize-cra');

const ae=require('react-app-rewire-multiple-entry')([{
    entry:"src/admin/index.js",
    template:"public/admin.html",
    outPath:"admin.html"
},{
    entry:"src/detail/index.js",
    template:"public/detail.html",
    outPath:"detail.html"
}])

module.exports=override(
    ae.addMultiEntry,
    fixBabelImports('import',{
         libraryName:'antd',
         libraryDirectory:'es',
         style:true
    }),
    addLessLoader({
        javascriptEnabled:true,
        modifyVars: {
            '@primary-color': '#f50',
            '@link-color': '#39a', // 链接色
            '@success-color': '#52c41a', // 成功色
            '@warning-color': '#faad14', // 警告色
            '@error-color': '#f5222d', // 错误色
            '@font-size-base': '14px', // 主字号
            '@heading-color': 'rgba(0, 0, 0, 0.85)', // 标题色
            '@text-color': 'rgba(0, 0, 0, 0.65)', // 主文本色
            '@text-color-secondary': 'rgba(0, 0, 0, 0.45)', // 次文本色
            '@disabled-color': 'rgba(0, 0, 0, 0.25)', // 失效色
            '@border-radius-base': '0px', // 组件/浮层圆角
            '@border-color-base': '#d9d9d9', // 边框色
            '@box-shadow-base': '0 2px 8px rgba(0, 0, 0, 0.15)', // 浮层阴影
        }
    })
)