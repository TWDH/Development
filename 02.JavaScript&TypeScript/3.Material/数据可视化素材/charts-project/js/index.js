// 监控区域模块制作
(function() {
  $(".monitor .tabs").on("click", "a", function() {
    $(this)
      .addClass("active")
      .siblings("a")
      .removeClass("active");

    // console.log($(this).index());
    //   选取对应索引号的content
    $(".monitor .content")
      .eq($(this).index())
      .show()
      .siblings(".content")
      .hide();
  });
  // 1. 先克隆marquee里面所有的行（row）
  $(".marquee-view .marquee").each(function() {
    // console.log($(this));
    var rows = $(this)
      .children()
      .clone();
    $(this).append(rows);
  });
})();
// 点位分布统计模块
(function() {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".pie"));
  // 2. 指定配置项和数据
  var option = {
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    // 注意颜色写的位置
    color: [
      "#006cff",
      "#60cda0",
      "#ed8884",
      "#ff9f7f",
      "#0096ff",
      "#9fe6b8",
      "#32c5e9",
      "#1d9dff"
    ],
    series: [
      {
        name: "点位统计",
        type: "pie",
        // 如果radius是百分比则必须加引号
        radius: ["10%", "70%"],
        center: ["50%", "50%"],
        roseType: "radius",
        data: [
          { value: 20, name: "云南" },
          { value: 26, name: "北京" },
          { value: 24, name: "山东" },
          { value: 25, name: "河北" },
          { value: 20, name: "江苏" },
          { value: 25, name: "浙江" },
          { value: 30, name: "四川" },
          { value: 42, name: "湖北" }
        ],
        // 修饰饼形图文字相关的样式 label对象
        label: {
          fontSize: 10
        },
        // 修饰引导线样式
        labelLine: {
          // 连接到图形的线长度
          length: 6,
          // 连接到文字的线长度
          length2: 8
        }
      }
    ]
  };

  // 3. 配置项和数据给我们的实例化对象
  myChart.setOption(option);
  // 4. 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function() {
    // 让我们的图表调用 resize这个方法
    myChart.resize();
  });
})();
// 柱形图模块
(function() {
  var item = {
    name: "",
    value: 1200,
    // 1. 修改当前柱形的样式
    itemStyle: {
      color: "#254065"
    },
    // 2. 鼠标放到柱子上不想高亮显示
    emphasis: {
      itemStyle: {
        color: "#254065"
      }
    },
    // 3. 鼠标经过柱子不显示提示框组件
    tooltip: {
      extraCssText: "opacity: 0"
    }
  };
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".bar"));
  // 2. 指定配置和数据
  var option = {
    color: new echarts.graphic.LinearGradient(
      // (x1,y2) 点到点 (x2,y2) 之间进行渐变
      0,
      0,
      0,
      1,
      [
        { offset: 0, color: "#00fffb" }, // 0 起始颜色
        { offset: 1, color: "#0061ce" } // 1 结束颜色
      ]
    ),
    tooltip: {
      trigger: "item"
    },
    grid: {
      left: "0%",
      right: "3%",
      bottom: "3%",
      top: "3%",
      //  图表位置紧贴画布边缘是否显示刻度以及label文字 防止坐标轴标签溢出跟grid 区域有关系
      containLabel: true,
      // 是否显示直角坐标系网格
      show: true,
      //grid 四条边框的颜色
      borderColor: "rgba(0, 240, 255, 0.3)"
    },
    xAxis: [
      {
        type: "category",
        data: [
          "上海",
          "广州",
          "北京",
          "深圳",
          "合肥",
          "",
          "......",
          "",
          "杭州",
          "厦门",
          "济南",
          "成都",
          "重庆"
        ],
        axisTick: {
          alignWithLabel: false,
          // 把x轴的刻度隐藏起来
          show: false
        },
        axisLabel: {
          color: "#4c9bfd"
        },
        // x轴这条线的颜色样式
        axisLine: {
          lineStyle: {
            color: "rgba(0, 240, 255, 0.3)"
            // width: 3
          }
        }
      }
    ],
    yAxis: [
      {
        type: "value",
        axisTick: {
          alignWithLabel: false,
          // 把y轴的刻度隐藏起来
          show: false
        },
        axisLabel: {
          color: "#4c9bfd"
        },
        // y轴这条线的颜色样式
        axisLine: {
          lineStyle: {
            color: "rgba(0, 240, 255, 0.3)"
            // width: 3
          }
        },
        // y轴分割线的颜色样式
        splitLine: {
          lineStyle: {
            color: "rgba(0, 240, 255, 0.3)"
          }
        }
      }
    ],
    series: [
      {
        name: "直接访问",
        type: "bar",
        barWidth: "60%",
        data: [
          2100,
          1900,
          1700,
          1560,
          1400,
          item,
          item,
          item,
          900,
          750,
          600,
          480,
          240
        ]
      }
    ]
  };
  // 3. 把配置给实例对象
  myChart.setOption(option);
  // 4. 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function() {
    // 让我们的图表调用 resize这个方法
    myChart.resize();
  });
})();
// 销售统计模块
(function() {
  // (1)准备数据
  var data = {
    year: [
      [24, 40, 101, 134, 90, 230, 210, 230, 120, 230, 210, 120],
      [40, 64, 191, 324, 290, 330, 310, 213, 180, 200, 180, 79]
    ],
    quarter: [
      [23, 75, 12, 97, 21, 67, 98, 21, 43, 64, 76, 38],
      [43, 31, 65, 23, 78, 21, 82, 64, 43, 60, 19, 34]
    ],
    month: [
      [34, 87, 32, 76, 98, 12, 32, 87, 39, 36, 29, 36],
      [56, 43, 98, 21, 56, 87, 43, 12, 43, 54, 12, 98]
    ],
    week: [
      [43, 73, 62, 54, 91, 54, 84, 43, 86, 43, 54, 53],
      [32, 54, 34, 87, 32, 45, 62, 68, 93, 54, 54, 24]
    ]
  };
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".line"));
  // 2. 指定配置和数据
  var option = {
    color: ["#00f2f1", "#ed3f35"],
    tooltip: {
      // 通过坐标轴来触发
      trigger: "axis"
    },
    legend: {
      // 距离容器10%
      right: "10%",
      // 修饰图例文字的颜色
      textStyle: {
        color: "#4c9bfd"
      }
      // 如果series 里面设置了name，此时图例组件的data可以省略
      // data: ["邮件营销", "联盟广告"]
    },
    grid: {
      top: "20%",
      left: "3%",
      right: "4%",
      bottom: "3%",
      show: true,
      borderColor: "#012f4a",
      containLabel: true
    },

    xAxis: {
      type: "category",
      boundaryGap: false,
      data: [
        "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月"
      ],
      // 去除刻度
      axisTick: {
        show: false
      },
      // 修饰刻度标签的颜色
      axisLabel: {
        color: "#4c9bfd"
      },
      // 去除x坐标轴的颜色
      axisLine: {
        show: false
      }
    },
    yAxis: {
      type: "value",
      // 去除刻度
      axisTick: {
        show: false
      },
      // 修饰刻度标签的颜色
      axisLabel: {
        color: "#4c9bfd"
      },
      // 修改y轴分割线的颜色
      splitLine: {
        lineStyle: {
          color: "#012f4a"
        }
      }
    },
    series: [
      {
        name: "预期销售额",
        type: "line",
        stack: "总量",
        // 是否让线条圆滑显示
        smooth: true,
        data: data.year[0]
      },
      {
        name: "实际销售额",
        type: "line",
        stack: "总量",
        smooth: true,
        data: data.year[1]
      }
    ]
  };
  // 3. 把配置和数据给实例对象
  myChart.setOption(option);
  // 4. tab切换效果制作
  // (2) 点击切换效果
  $(".sales .caption").on("click", "a", function() {
    // 此时要注意这个索引号的问题
    index = $(this).index() - 1;
    // 点击当前a 高亮显示 调用active
    $(this)
      .addClass("active")
      .siblings("a")
      .removeClass("active");
    // 拿到当前a 的自定义属性值
    // console.log(this.dataset.type);
    // 根据拿到的值 去找数据
    // console.log(data.year);
    // console.log(data["year"]);
    // console.log(data[this.dataset.type]);
    var arr = data[this.dataset.type];
    // console.log(arr);
    // 根据拿到的数据重新渲染 series里面的data值
    option.series[0].data = arr[0];
    option.series[1].data = arr[1];
    // 重新把配置好的新数据给实例对象
    myChart.setOption(option);
  });
  // 5. tab栏自动切换效果
  //  开启定时器每隔3s，自动让a触发点击事件即可
  var as = $(".sales .caption a");
  var index = 0;
  var timer = setInterval(function() {
    index++;
    if (index >= 4) index = 0;
    as.eq(index).click();
  }, 1000);
  // 鼠标经过sales，关闭定时器，离开开启定时器
  $(".sales").hover(
    function() {
      clearInterval(timer);
    },
    function() {
      clearInterval(timer);
      timer = setInterval(function() {
        index++;
        if (index >= 4) index = 0;
        as.eq(index).click();
      }, 1000);
    }
  );
  // 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function() {
    // 让我们的图表调用 resize这个方法
    myChart.resize();
  });
})();
// 销售渠道模块 雷达图
(function() {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".radar"));
  // 2.指定配置

  var option = {
    tooltip: {
      show: true,
      // 控制提示框组件的显示位置
      position: ["60%", "10%"]
    },
    radar: {
      indicator: [
        { name: "机场", max: 100 },
        { name: "商场", max: 100 },
        { name: "火车站", max: 100 },
        { name: "汽车站", max: 100 },
        { name: "地铁", max: 100 }
      ],
      // 修改雷达图的大小
      radius: "65%",
      shape: "circle",
      // 分割的圆圈个数
      splitNumber: 4,
      name: {
        // 修饰雷达图文字的颜色
        textStyle: {
          color: "#4c9bfd"
        }
      },
      // 分割的圆圈线条的样式
      splitLine: {
        lineStyle: {
          color: "rgba(255,255,255, 0.5)"
        }
      },
      splitArea: {
        show: false
      },
      // 坐标轴的线修改为白色半透明
      axisLine: {
        lineStyle: {
          color: "rgba(255, 255, 255, 0.5)"
        }
      }
    },
    series: [
      {
        name: "北京",
        type: "radar",
        // 填充区域的线条颜色
        lineStyle: {
          normal: {
            color: "#fff",
            width: 1,
            opacity: 0.5
          }
        },
        data: [[90, 19, 56, 11, 34]],
        // 设置图形标记 （拐点）
        symbol: "circle",
        // 这个是设置小圆点大小
        symbolSize: 5,
        // 设置小圆点颜色
        itemStyle: {
          color: "#fff"
        },
        // 让小圆点显示数据
        label: {
          show: true,
          fontSize: 10
        },
        // 修饰我们区域填充的背景颜色
        areaStyle: {
          color: "rgba(238, 197, 102, 0.6)"
        }
      }
    ]
  };
  // 3.把配置和数据给对象
  myChart.setOption(option);
  // 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function() {
    // 让我们的图表调用 resize这个方法
    myChart.resize();
  });
})();
// 销售模块 饼形图 半圆形 设置方式
(function() {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".gauge"));
  // 2. 指定数据和配置
  var option = {
    series: [
      {
        name: "销售进度",
        type: "pie",
        radius: ["130%", "150%"],
        // 移动下位置  套住50%文字
        center: ["48%", "80%"],
        //是否启用防止标签重叠策略
        // avoidLabelOverlap: false,
        labelLine: {
          normal: {
            show: false
          }
        },
        // 饼形图的起始角度为 180， 注意不是旋转角度
        startAngle: 180,
        // 鼠标经过不需要放大偏移图形
        hoverOffset: 0,
        data: [
          {
            value: 100,
            itemStyle: {
              // 颜色渐变#00c9e0->#005fc1
              color: new echarts.graphic.LinearGradient(
                // (x1,y2) 点到点 (x2,y2) 之间进行渐变
                0,
                0,
                0,
                1,
                [
                  { offset: 0, color: "#00c9e0" }, // 0 起始颜色
                  { offset: 1, color: "#005fc1" } // 1 结束颜色
                ]
              )
            }
          },
          {
            value: 100,
            itemStyle: {
              color: "#12274d"
            }
          },
          {
            value: 200,
            itemStyle: {
              color: "transparent"
            }
          }
        ]
      }
    ]
  };
  // 3. 把数据和配置给实例对象
  myChart.setOption(option);
  // 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function() {
    // 让我们的图表调用 resize这个方法
    myChart.resize();
  });
})();
// 全国热榜模块
(function() {
  // 1. 准备相关数据
  var hotData = [
    {
      city: "北京", // 城市
      sales: "25, 179", // 销售额
      flag: true, //  上升还是下降
      brands: [
        //  品牌种类数据
        { name: "可爱多", num: "9,086", flag: true },
        { name: "娃哈哈", num: "8,341", flag: true },
        { name: "喜之郎", num: "7,407", flag: false },
        { name: "八喜", num: "6,080", flag: false },
        { name: "小洋人", num: "6,724", flag: false },
        { name: "好多鱼", num: "2,170", flag: true }
      ]
    },
    {
      city: "河北",
      sales: "23,252",
      flag: false,
      brands: [
        { name: "可爱多", num: "3,457", flag: false },
        { name: "娃哈哈", num: "2,124", flag: true },
        { name: "喜之郎", num: "8,907", flag: false },
        { name: "八喜", num: "6,080", flag: true },
        { name: "小洋人", num: "1,724", flag: false },
        { name: "好多鱼", num: "1,170", flag: false }
      ]
    },
    {
      city: "上海",
      sales: "20,760",
      flag: true,
      brands: [
        { name: "可爱多", num: "2,345", flag: true },
        { name: "娃哈哈", num: "7,109", flag: true },
        { name: "喜之郎", num: "3,701", flag: false },
        { name: "八喜", num: "6,080", flag: false },
        { name: "小洋人", num: "2,724", flag: false },
        { name: "好多鱼", num: "2,998", flag: true }
      ]
    },
    {
      city: "江苏",
      sales: "23,252",
      flag: false,
      brands: [
        { name: "可爱多", num: "2,156", flag: false },
        { name: "娃哈哈", num: "2,456", flag: true },
        { name: "喜之郎", num: "9,737", flag: true },
        { name: "八喜", num: "2,080", flag: true },
        { name: "小洋人", num: "8,724", flag: true },
        { name: "好多鱼", num: "1,770", flag: false }
      ]
    },
    {
      city: "山东",
      sales: "20,760",
      flag: true,
      brands: [
        { name: "可爱多", num: "9,567", flag: true },
        { name: "娃哈哈", num: "2,345", flag: false },
        { name: "喜之郎", num: "9,037", flag: false },
        { name: "八喜", num: "1,080", flag: true },
        { name: "小洋人", num: "4,724", flag: false },
        { name: "好多鱼", num: "9,999", flag: true }
      ]
    }
  ];
  //  2. 根据数据渲染各省热销 sup 模块内容
  // (1) 遍历 hotData对象
  var supHTML = "";
  $.each(hotData, function(index, item) {
    // console.log(item);
    supHTML += `<li><span>${item.city}</span><span> ${item.sales} <s class=
    ${item.flag ? "icon-up" : "icon-down"}></s></span></li>`;
  });
  // 把生成的5个小li字符串给 sub dom盒子
  $(".sup").html(supHTML);
  // 3. 当鼠标进入 tab 的时候
  // 鼠标经过当前的小li要高亮显示
  $(".province .sup").on("mouseenter", "li", function() {
    index = $(this).index();
    render($(this));
  });

  // 声明一个函数 里面设置sup当前小li高亮还有 对应的品牌对象渲染
  // 这个函数需要传递当前元素进去
  function render(currentEle) {
    currentEle
      .addClass("active")
      .siblings()
      .removeClass();
    // 拿到当前城市的品牌对象
    // console.log($(this).index());
    // 可以通过hotData[$(this).index()] 得到当前的城市
    // console.log(hotData[$(this).index()]);
    // 我们可以通过hotData[$(this).index()].brands 拿到的是城市对象的品牌种类
    // console.log(hotData[$(this).index()].brands);
    // 开始遍历品牌数组
    var subHTML = "";
    $.each(hotData[currentEle.index()].brands, function(index, item) {
      // 是对应城市的每一个品牌对象
      // console.log(item);
      subHTML += `<li><span>${item.name}</span><span> ${item.num}<s class=
    ${item.flag ? "icon-up" : "icon-down"}
    ></s></span></li>`;
    });
    // 把生成的6个小li字符串给 sub dom盒子
    $(".sub").html(subHTML);
  }
  // 4. 默认把第一个小li处于鼠标经过状态
  var lis = $(".province .sup li");
  lis.eq(0).mouseenter();
  // 5 开启定时器
  var index = 0;
  var timer = setInterval(function() {
    index++;
    if (index >= 5) index = 0;
    // lis.eq(index).mouseenter();
    render(lis.eq(index));
  }, 2000);

  $(".province .sup").hover(
    // 鼠标经过事件
    function() {
      clearInterval(timer);
    },
    // 鼠标离开事件
    function() {
      clearInterval(timer);
      timer = setInterval(function() {
        index++;
        if (index >= 5) index = 0;
        // lis.eq(index).mouseenter();
        render(lis.eq(index));
      }, 2000);
    }
  );
})();
