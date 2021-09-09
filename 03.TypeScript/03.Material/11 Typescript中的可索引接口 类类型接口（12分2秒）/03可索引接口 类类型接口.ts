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
4、typeScript中的类

    4.1 类的定义
    4.2 继承
    4.3 类里面的修饰符
    4.4 静态属性 静态方法
    4.5 抽象类 多态
5、typeScript中的接口

    5.1 属性类接口
    5.2 函数类型接口
    5.3 可索引接口
    5.4 类类型接口
    5.5 接口扩展

   
*/


/*
接口的作用：在面向对象的编程中，接口是一种规范的定义，它定义了行为和动作的规范，在程序设计里面，接口起到一种限制和规范的作用。接口定义了某一批类所需要遵守的规范，接口不关心这些类的内部状态数据，也不关心这些类里方法的实现细节，它只规定这批类里必须提供某些方法，提供这些方法的类就可以满足实际需要。 typescrip中的接口类似于java，同时还增加了更灵活的接口类型，包括属性、函数、可索引和类等。

定义标准。

*/


// 可索引接口：数组、对象的约束  （不常用）



    //ts定义数组的方式
        /*
            var arr:number[]=[2342,235325]


            var arr1:Array<string>=['111','222']
        */



        //可索引接口 对数组的约束
                // interface UserArr{
                //     [index:number]:string
                // }


                // // var arr:UserArr=['aaa','bbb'];

                // // console.log(arr[0]);



                // var arr:UserArr=[123,'bbb'];  /*错误*/

                // console.log(arr[0]);


        //可索引接口 对对象的约束




                // interface UserObj{

                //     [index:string]:string
                // }


                // var arr:UserObj={name:'张三'};






//类类型接口:对类的约束  和   抽象类抽象有点相似    


    interface Animal{
        name:string;
        eat(str:string):void;
    }

    class Dog implements Animal{

        name:string;
        constructor(name:string){

            this.name=name;

        }
        eat(){

            console.log(this.name+'吃粮食')
        }
    }


    var d=new Dog('小黑');
    d.eat();


    class Cat implements Animal{
        name:string;
        constructor(name:string){

            this.name=name;

        }
        eat(food:string){

            console.log(this.name+'吃'+food);
        }
    }

    var c=new Cat('小花');
    c.eat('老鼠');