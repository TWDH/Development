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

3、typeScript中的函数

    3.1、函数的定义
    3.2、可选参数
    3.3、默认参数
    3.4、剩余参数
    3.5、函数重载
    3.6、箭头函数  es6


*/


// 3.1、函数的定义


//es5定义函数的方法
   /*
    //函数声明法
        function run(){

            return 'run';
        }
    //匿名函数
        var run2=function(){

            return 'run2';
        }
   */



//ts中定义函数的方法


 //函数声明法
        // function run():string{

        //     return 'run';
        // }


        //错误写法
        // function run():string{

        //     return 123;
        // }






//匿名函数


    // var fun2=function():number{

    //     return 123;
    // }


    // alert(fun2()); /*调用方法*/





//ts中定义方法传参



    /*
        function getInfo(name:string,age:number):string{

                return `${name} --- ${age}`;
        }


        alert(getInfo('zhangsan',20));

    */


    // var getInfo=function(name:string,age:number):string{

    //     return `${name} --- ${age}`;
    // }
       
    // alert(getInfo('zhangsan',40));




//没有返回值的方法

        // function run():void{

        //     console.log('run')
        // }
        // run();




// 3.2、方法可选参数 

        // es5里面方法的实参和行参可以不一样，但是ts中必须一样，如果不一样就需要配置可选参数 



       /*
        function getInfo(name:string,age?:number):string{

                if(age){

                    return `${name} --- ${age}`;
                }else{

                    return `${name} ---年龄保密`;
                }


        }

        alert(getInfo('zhangsan'))

        alert(getInfo('zhangsan',123))
       */



    //注意:可选参数必须配置到参数的最后面

    //错误写法
      /*
       function getInfo(name?:string,age:number):string{

                if(age){

                    return `${name} --- ${age}`;
                }else{

                    return `${name} ---年龄保密`;
                }
               

        }

        alert(getInfo('zhangsan'))
      */


// 3.3、默认参数 可选参数


        // es5里面没法设置默认参数，es6和ts中都可以设置默认参数

        /*

                function getInfo(name:string,age:number=20):string{

                            if(age){

                                return `${name} --- ${age}`;
                            }else{

                                return `${name} ---年龄保密`;
                            }
                        

                }

                // alert( getInfo('张三'));
                alert( getInfo('张三',30));
        */




// 3.4、剩余参数



            // function sum(a:number,b:number,c:number,d:number):number{

            //     return a+b+c+d;

            // }


            // alert(sum(1,2,3,4)) ;


    //三点运算符 接受新参传过来的值


        /*
            function sum(...result:number[]):number{

                
                var sum=0;

                for(var i=0;i<result.length;i++){

                    sum+=result[i];  
                }

                return sum;

            }

            alert(sum(1,2,3,4,5,6)) ;
        */




    /*

        function sum(a:number,b:number,...result:number[]):number{

                    
                var sum=a+b;

                for(var i=0;i<result.length;i++){

                    sum+=result[i];  
                }

                return sum;

            }

            alert(sum(1,2,3,4,5,6)) ;
    */
                    


// 3.5、ts函数重载

    // java中方法的重载：重载指的是两个或者两个以上同名函数，但它们的参数不一样，这时会出现函数重载的情况。

    // typescript中的重载：通过为同一个函数提供多个函数类型定义来试下多种功能的目的。

    //ts为了兼容es5 以及 es6 重载的写法和java中有区别。



    //es5中出现同名方法，下面的会替换上面的方法 
        /*
            function css(config){

            }

            function css(config,value){


            }
        */



    //ts中的重载

                // function getInfo(name:string):string;

                // function getInfo(age:number):string;

                // function getInfo(str:any):any{

                //     if(typeof str==='string'){

                //         return '我叫：'+str;
                //     }else{

                //         return '我的年龄是'+str;
                //     }

                // }

                // alert(getInfo('张三'));   //正确


                // alert(getInfo(20));   //正确


                // alert(getInfo(true));    //错误写法

    

                function getInfo(name:string):string;
                function getInfo(name:string,age:number):string;
                function getInfo(name:any,age?:any):any{
                    if(age){

                        return '我叫：'+name+'我的年龄是'+age;
                    }else{

                        return '我叫：'+name;
                    }
                }

                // alert(getInfo('zhangsan'));  /*正确*/

                // alert(getInfo(123));  错误

                // alert(getInfo('zhangsan',20));




// 3.6、箭头函数  es6  


//this指向的问题    箭头函数里面的this指向上下文


            

        // setTimeout(function(){

        //     alert('run')
        // },1000)



        setTimeout(()=>{

            alert('run')
        },1000)
    

