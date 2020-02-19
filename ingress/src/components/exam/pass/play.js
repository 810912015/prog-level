import React from 'react'
import {MultiLangEditor} from "../../common/CoderEditor";
import {post} from "../../common/util";

const initSource={
    java:'public class Solution {\n' +
        '    public static void main(String[] args){\n' +
        '        System.out.println("杨辉三角--java");\n' +
        '        printYFTriangle();\n' +
        '    }\n' +
        '    public static void printYFTriangle(){\n' +
        '        int lines = 20;\n' +
        '        int[] a = new int[lines + 1];\n' +
        '        int previous = 1;\n' +
        '        for (int i = 1; i <= lines; i ++){\n' +
        '            for (int j = 1; j <= i; j++){\n' +
        '                int current = a[j];\n' +
        '                a[j] = previous + current;\n' +
        '                previous = current;\n' +
        '                System.out.print(a[j] + " ");\n' +
        '            }\n' +
        '            System.out.println();\n' +
        '        }\n' +
        '    }\n' +
        ' \n' +
        '}',
    c:'#include <stdio.h>\n' +
        '#include <stdlib.h>\n' +
        '\n' +
        'const int length = 10;  // 定义杨辉三角的大小\n' +
        '\n' +
        'int main(void)\n' +
        '{\n' +
        '    printf("%s\\n","杨辉三角--c");\n' +
        '    int nums[length][length];\n' +
        '    int i, j;\n' +
        '    /*计算杨辉三角*/ \n' +
        '    for(i=0; i<length; i++)\n' +
        '    {\n' +
        '        nums[i][0] = 1;\n' +
        '        nums[i][i] = 1;\n' +
        '        for(j=1; j<i; j++)\n' +
        '            nums[i][j] = nums[i-1][j-1] + nums[i-1][j];\n' +
        '    }\n' +
        '\n' +
        '    /*打印输出*/ \n' +
        '    for(i=0; i<length; i++)\n' +
        '    {\n' +
        '        for(j=0; j<length-i-1; j++)\n' +
        '            printf("   ");\n' +
        '        for(j=0; j<=i; j++)\n' +
        '            printf("%-5d ", nums[i][j]);\n' +
        '        putchar(\'\\n\');\n' +
        '    }\n' +
        '    \n' +
        '    return EXIT_SUCCESS;\n' +
        '}',
    cpp:'#include<iostream>\n' +
        '\n' +
        '//求阶乘的函数\n' +
        'int factorial(int num)\n' +
        '{\n' +
        '    if (num == 0)\n' +
        '        return 1;\n' +
        '    else\n' +
        '    {\n' +
        '        int result = 1;\n' +
        '        for (int i = 1; i <= num; ++i)\n' +
        '            result *= i;\n' +
        '        return result;\n' +
        '    }\n' +
        '}\n' +
        '\n' +
        '//打印杨辉三角的函数\n' +
        'void function(int rowNum)\n' +
        '{\n' +
        '    int value;\n' +
        '    for (int n = 1; n <= rowNum; ++n) //对于每一行\n' +
        '    {\n' +
        '        for (int i = 0; i <= rowNum - n; ++i)  //打印每行前的空格\n' +
        '            std::cout << " ";\n' +
        '        for (int k = 1; k <= n; ++k)  //打印每行的每个数\n' +
        '        {\n' +
        '            value = factorial(n - 1) / (factorial(k - 1)*factorial(n - k));\n' +
        '            std::cout << value << " ";\n' +
        '        }\n' +
        '        std::cout << std::endl;\n' +
        '    }\n' +
        '}\n' +
        '\n' +
        'int main()\n' +
        '{\n' +
        '    int lineNum=10; \n' +
        '    std::cout<<"杨辉三角--c++"<<std::endl;\n' +
        '    function(lineNum);\n' +
        '    return 0;\n' +
        '}',
    csharp:'using System;\n' +
        'using System.Collections.Generic;\n' +
        'using System.IO;\n' +
        'using System.Linq;\n' +
        '\n' +
        'class Solution {\n' +
        '    static void Main(string[] args) {\n' +
        '        Console.WriteLine("杨辉三角--c#");\n' +
        '            int rows =10;\n' +
        '            //初始化二维数组\n' +
        '            int[][]  nums = new int[rows][];\n' +
        '            for (int i = 0; i < rows; i++)\n' +
        '            { \n' +
        '               nums[i]=new int[i+1];\n' +
        '            }\n' +
        '            //边界赋值\n' +
        '            for (int i = 0; i < rows; i++)\n' +
        '            {\n' +
        '                nums[i][0] = 1;\n' +
        '                nums[i][i] = 1;\n' +
        '            }\n' +
        '            //中心值赋值\n' +
        '            for (int i = 2; i < rows; i++) //控制行数\n' +
        '            {\n' +
        '                for (int j = 1; j < i; j++) //控制列数\n' +
        '                { \n' +
        '                   nums[i][j]=nums[i-1][j]+nums[i-1][j-1]; //每一列的值等于上一行的列+上一行的列-1的值\n' +
        '                }\n' +
        '            }\n' +
        '            //输出\n' +
        '            for (int i = 0; i < rows; i++)\n' +
        '            {\n' +
        '                //打印空格\n' +
        '                for (int k = 0; k < rows-i; k++)\n' +
        '                {\n' +
        '                    Console.Write(" ");\n' +
        '                }\n' +
        '                    for (int j = 0; j <= i; j++)\n' +
        '                    {\n' +
        '                        Console.Write(nums[i][j] + " ");\n' +
        '                    }\n' +
        '                Console.WriteLine();\n' +
        '            }\n' +
        '\n' +
        '    }\n' +
        '}',
    python:'def triangle():\n' +
        '    N = [1]\n' +
        '    while True:\n' +
        '        yield N\n' +
        '        N.append(0)\n' +
        '        N = [N[i]+N[i-1] for i in range(len(N))]\n' +
        ' \n' +
        'def print_triangle(x):\n' +
        '    a = 0\n' +
        '    for t in triangle():\n' +
        '        print(t)\n' +
        '        a += 1\n' +
        '        if a ==x:\n' +
        '             break\n' +
        'print("yanghui triangle -- python")\n' +
        'print_triangle(10)\n',
    go:'package main //command executable（命令行可执行程序）必须使用main作为package名字。\n' +
        '\n' +
        'import "fmt"\n' +
        '\n' +
        'func main() {\n' +
        '    fmt.Printf("杨辉三角--go \\n")\n' +
        '    ShowYangHuiTriangle();\n' +
        '}\n' +
        '\n' +
        '//行数\n' +
        'const LINES int = 8\n' +
        '\n' +
        '//杨辉三角\n' +
        'func ShowYangHuiTriangle() {\n' +
        '\tnums := []int{}\n' +
        '\tfor i := 0; i < LINES; i++ {\n' +
        '\t\t//补空白\n' +
        '\t\tfor j := 0; j < (LINES - i); j++ {\n' +
        '\t\t\tfmt.Print("  ")\n' +
        '\t\t}\n' +
        '\n' +
        '\t\tfor j := 0; j < (i + 1); j++ {\n' +
        '\t\t\t//当前数组长度\n' +
        '\t\t\tvar length = len(nums)\n' +
        '\t\t\t//本位置应该生成的数字\n' +
        '\t\t\tvar value int\n' +
        '\n' +
        '\t\t\t//每行第一个元素和最后一个元素是1，中间的元素等于它上方的两个元素之和\n' +
        '\t\t\tif j == 0 || j == i {\n' +
        '\t\t\t\tvalue = 1\n' +
        '\t\t\t} else {\n' +
        '\t\t\t\tvalue = nums[length-i] + nums[length-i-1]\n' +
        '\t\t\t}\n' +
        '\t\t\tnums = append(nums, value)\n' +
        '\t\t\tfmt.Print(value, " ")\n' +
        '\t\t}\n' +
        '\t\t//换行\n' +
        '\t\tfmt.Println("")\n' +
        '\t}\n' +
        '}',
    javascript:'console.log(\'杨辉三角--javascript\')\n' +
        '\n' +
        'var lineNumber = 10;\n' +
        '//新建数组，放置杨辉三角\n' +
        'var array = new Array(lineNumber);\n' +
        '\n' +
        'for (var k = 0; k < lineNumber; k++) {\n' +
        '    array[k] = new Array();\n' +
        '}\n' +
        '\n' +
        '\n' +
        'for (var i = 0; i < lineNumber; i++) {\n' +
        '    var type = "";\n' +
        '    for (var j = 0; j <= i; j++) {\n' +
        '\n' +
        '        //每一行首尾两个元素赋值为1\n' +
        '        if (0 == j || i == j) {\n' +
        '            array[i][j] = 1;\n' +
        '            type += array[i][j] + " ";\n' +
        '        }\n' +
        '        //其他元素为上一行前一列元素上一行这一列元素\n' +
        '        else {\n' +
        '            array[i][j] = array[i - 1][j - 1] + array[i - 1][j];\n' +
        '\n' +
        '            type += array[i][j] + " ";\n' +
        '        }\n' +
        '\n' +
        '    }\n' +
        '    console.log(type)\n' +
        '}'

}

const PLAY_KEY="54c934d3-9990-4f1e-86da-623e3b7da3eb";

export class Play extends React.Component {
    constructor(props){
        super(props)
        let p=null
        try{
            let s=window.localStorage.getItem(PLAY_KEY);
            p=JSON.parse(s);
        }catch (e) {

        }
        this.state = {
            initSource:p!=null?p.source:initSource,
            source:p!=null?p.source:initSource,
            lang:p!=null?p.lang: 'java',
            cur: p!=null?p.cur:'public class Solution {\n' +
                '    public static void main(String[] args){\n' +
                '        System.out.println("hello world");\n' +
                '    }\n' +
                '}',
            msg: p!=null?p.msg:'',
            running:false,
            saveHandle:null
        }
    }

    componentDidMount() {
        if(this.state.saveHandle==null){
            let sh=setInterval(()=>{
                try {
                    let p = {
                        source: this.state.source,
                        lang: this.state.lang,
                        cur: this.state.cur,
                        msg: this.state.msg
                    }
                    window.localStorage.setItem(PLAY_KEY, JSON.stringify(p))
                }catch (e) {

                }
            },30000);
            this.setState({saveHandler:sh})
        }
    }
    componentWillUnmount() {
        clearInterval(this.state.saveHandle);
    }

    run = () => {
        if(this.state.running) return;
        this.setState({running:true})
        post({
            url: "/play",
            data: {
                lang: this.state.lang,
                source: this.state.cur
            },
            func: (d) => {
                this.setState({msg: d.data,running:false})
            },
            errFunc:(e)=>{
                this.setState({msg:e.toString(),running:false})
            }
        })
    }

    render() {
        return (
            <div className={"container-fluid"}>
                <div className={"row mb-1"}>
                    <div className={"col offset-md-5"}>
                        <span className={"mr-3 font-weight-light"}>输入源码,点击编译运行</span>
                        <button className={"btn btn-outline-primary"} onClick={this.run} disabled={this.state.running}>编译运行</button>
                        <button className={"btn btn-outline-info ml-3"} onClick={()=>{
                            var s={
                                initSource:initSource,
                                source:initSource,
                                lang:'java',
                                cur: initSource.java,
                                msg: ''
                            }
                            this.setState(s)
                            console.log("清空缓存")
                        }}>清空缓存</button>
                    </div>
                </div>
                <div className={"row"}>
                    <div className={"col-sm-6"}>
                        <MultiLangEditor sourceList={this.state.initSource}
                                         curSourceChange={(e, l) => {
                                             this.setState({
                                                 lang: l,
                                                 cur: e
                                             })
                                         }}
                                         sourceChange={(e)=>this.setState({source:e})}
                        />

                    </div>
                    <div className={"col-sm-6"}>
                    <textarea value={this.state.msg} readOnly={true}
                              className={"form-control"}
                              style={{height: '600px',marginTop:'30px'}}
                    />
                    </div>
                </div>
            </div>
        )
    }
}
