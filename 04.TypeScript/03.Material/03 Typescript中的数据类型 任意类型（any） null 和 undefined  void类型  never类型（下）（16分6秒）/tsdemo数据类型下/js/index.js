"use strict";
/*
1、vscode配置自动编译

    1.第一步   tsc --inti 生成tsconfig.json   改 "outDir": "./js",


    2、第二步 任务 - 运行任务  监视tsconfig.json


2、typeScript中的数据类型

    typescript中为了使编写的代码更规范，更有利于维护，增加了类型校验，在typescript中主要给我们提供了以下数据类型


        布尔类型（boolean）
        数字类型（number）
        字符串类型(string)
        数组类型（array）
        元组类型（tuple）
        枚举类型（enum）
        
        任意类型（any）
        null 和 undefined
        void类型
        never类型

*/
//布尔类型（boolean）
/*
es5的写法 （正确写法）  ts中（错误写法）
    var flag=true;
    
    flag=456;
*/
/*
 typescript中为了使编写的代码更规范，更有利于维护，增加了类型校验

 写ts代码必须指定类型



var flag:boolean=true;

// flag=123;  //错误

flag=false;  //正确

console.log(flag);


*/
// 数字类型（number）
/*
    var num:number=123;

    num=456;

    console.log(num);  /正确/


    num='str';    //错误
    
    */
// 字符串类型(string)
/*
     var str:string='this is ts';

     str='haha';  //正确


     str=true;  //错误

*/
// 数组类型（array）  ts中定义数组有两种方式
// var arr=['1','2'];  //es5定义数组
// 1.第一种定义数组的方式
var arr = [11, 22, 33];
console.log(arr);
//2.第二种定义数组的方式
var arr2 = [11, 22, 33];
console.log(arr2);
//3、第三种
var arr3 = ['131214', 22, true];
console.log(arr3);
// 元组类型（tuple）  属于数组的一种
// var arr:Array<number>=[11,22,33];
// console.log(arr)
//元祖类型
// let arr:[number,string]=[123,'this is ts'];
// console.log(arr);
/*



枚举类型（enum）
    随着计算机的不断普及，程序不仅只用于数值计算，还更广泛地用于处理非数值的数据。
    例如：性别、月份、星期几、颜色、单位名、学历、职业等，都不是数值数据。
    在其它程序设计语言中，一般用一个数值来代表某一状态，这种处理方法不直观，易读性差。
    如果能在程序中用自然语言中有相应含义的单词来代表某一状态，则程序就很容易阅读和理解。
    也就是说，事先考虑到某一变量可能取的值，尽量用自然语言中含义清楚的单词来表示它的每一个值，
    这种方法称为枚举方法，用这种方法定义的类型称枚举类型。
        
            enum 枚举名{
                标识符[=整型常数],
                标识符[=整型常数],
                ...
                标识符[=整型常数],
            } ;

 */
/*

    enum Flag {success=1,error=2};


    let s:Flag=Flag.success;

    console.log(s);



    enum Flag {success=1,error=2};


        let f:Flag=Flag.error;

        console.log(f);
*/
/*
000010

-1

-2


*/
//    enum Color {blue,red,'orange'};
//    var c:Color=Color.red;
//    console.log(c);   //1  如果标识符没有赋值 它的值就是下标
// enum Color {blue,red=3,'orange'};
// // var c:Color=Color.red;
// // console.log(c);   //3
// var c:Color=Color.orange;
// console.log(c);   //4
var Err;
(function (Err) {
    Err[Err["undefined"] = -1] = "undefined";
    Err[Err["null"] = -2] = "null";
    Err[Err["success"] = 1] = "success";
})(Err || (Err = {}));
;
var e = Err.success;
console.log(e);
// 任意类型（any）
// var num:any=123;
// num='str';
// num=true;
// console.log(num)
//任意类型的用处
var oBox = document.getElementById('box');
oBox.style.color = 'red';
// null 和 undefined  其他（never类型）数据类型的子类型
// var num:number;
// console.log(num)  //输出：undefined   报错
// var num:undefined;
// console.log(num)  //输出：undefined  //正确
// var num:number | undefined;
// num=123;
// console.log(num);
//定义没有赋值就是undefined
// var num:number | undefined;
// console.log(num);
// var num:null;
// num=null;
//一个元素可能是 number类型 可能是null 可能是undefined
var num;
num = 1234;
console.log(num);
// void类型 :typescript中的void表示没有任何类型，一般用于定义方法的时候方法没有返回值。
//es5的定义方法
// function run(){
//     console.log('run')
// }
// run();
//表示方法没有返回任何类型
/*
//正确写法
function run():void{

        console.log('run')
    }

    run();
    
    */
//错误写法
/*
    function run():undefined{

        console.log('run')
    }

    run();

*/
//正确写法
/*
function run():number{

    return 123;
 }

 run();
*/
// never类型:是其他类型 （包括 null 和 undefined）的子类型，代表从不会出现的值。
//这意味着声明never的变量只能被never类型所赋值。
/*

 var a:undefined;

 a=undefined;



 var b:null;

 b=null;


*/
var a;
//    a=123; //错误的写法
a = (function () {
    throw new Error('错误');
})();
