import React from 'react';
import { Layout} from 'antd';

export function Footer() {
  return (
      <Layout.Footer style={{padding:0}}>
          <div style={{textAlign:"center",backgroundColor:"#444",color:"#eee",padding:"2px"}}>
              <div className={"space"}>分码编程 编程入门和提高社区</div>
              <div className={"space"}>能开发中大型项目 联系请邮件810912015@qq.com</div>
              <div className={"space"}> <a href={"http://www.beian.miit.gov.cn/"} style={{color:"#eee"}}>沪ICP备20013508号-1</a></div>
          </div>
      </Layout.Footer>
  )
}