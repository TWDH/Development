
$(function() {
    var $table = $('#table');
    $.extend({
        _option: {},
        //分页列表
        pageTable: function(options) {
            $._option = options;
            $table.bootstrapTable({
                url: options.url,  // 请求后台URL(*)
                method: 'post',       // 请求方式（*）
                contentType: "application/x-www-form-urlencoded",   // 编码类型（*）
                cache: false,         // 是否使用缓存
                pagination: true,   // 是否显示分页（*）
                sidePagination: 'server', // 分页方式：client客户端分页，server服务端分页（*）
                pageNumber: options.pageNumber,   // 当前页码，初始化加载第1页，默认第1页
                pageSize: options.pageSize,       // 每页的记录行数（*）
                buttonsAlign: 'right',     // 按钮对齐方式
                showRefresh: true,    // 是否显示刷新按钮
                iconSize: 'sm',      // 工具图标大小：undefined默认的按钮尺寸,xs超小按钮,sm小按钮,lg大按钮
                queryParams: $.queryParams,     // 条件查询参数
                columns: options.columns,
                onLoadSuccess: function (res) {// 成功加载远程数据时触发
                    console.log('res', res);
                    // 1. 加载数据到列表中 rows接收渲染数据 res.data.records , total接收总记录数来自动计算页码
                    $table.bootstrapTable("load", {rows: res.data.records, total: res.data.total});

                    // 2. `操作`权限判断, 增删改只要有一个就显示, 自已在html上定义此方法实现
                    showOperation();

                    // 3. 渲染为树状列表
                    $table.treegrid({
                        initialState: 'collapsed',  // collapsed折叠, expanded展开(默认)
                        treeColumn: 1,
                        onChange: function() {
                            $table.bootstrapTable('resetWidth');
                        }
                    })
                }
            })
        },
        // 封装分页查询参数, params是bootstrap-table自动传入的
        queryParams: function(params) {
            return {
                size: params.limit, // 每页显示条数
                current: params.offset / params.limit + 1 //当前页码; params.offset 每次查询数据初始的数字
            }
        },

        // 树列表
        treeTable: function(options) {
            $table.bootstrapTable({
                url: options.url,  // 请求后台URL(*)
                method: 'post',           // 请求方式（*）
                contentType: "application/x-www-form-urlencoded",   // 编码类型（*）
                cache: false,             // 是否使用缓存
                idField: options.idField,       // 指定主键列
                parentIdField: options.parentIdField, // 父菜单字段名
                treeShowField: options.treeShowField, // 在哪一列展开树形
                buttonsAlign: 'right', // 按钮对齐方式
                showRefresh: true,    // 是否显示刷新按钮
                iconSize: 'sm',      // 工具图标大小(刷新图标)：undefined默认的按钮尺寸,xs超小按钮,sm小按钮,lg大按钮
                columns: options.columns,
                onLoadSuccess: function (res) { // 成功加载远程数据时触发
                    console.log('res', res);
                    // 1. 加载 res.data 中的数据到列表中
                    $table.bootstrapTable("load", res.data);

                    // 2. `操作`权限判断, 增删改只要有一个就显示, 自已在html上定义此方法实现
                    showOperation();

                    // 3. 渲染为树状列表
                    $table.treegrid({
                        initialState: 'collapsed',  // collapsed折叠, expanded展开(默认)
                        treeColumn: 1,
                        onChange: function() {
                            //重新计算宽度
                            $table.bootstrapTable('resetWidth');
                        }
                    })
                }
            });
        },

        // 列表中的 `操作` 列
        operationFormatter: function(value, row, index) {
            // 拼接操作项
            var operationHtml = [];
            operationHtml.push(
                '<div class="btn-group">' +
                '   <button type="button" class="btn btn-primary dropdown-toggle dropdown-hover dropdown-icon btn-xs" data-toggle="dropdown" >' +
                '    <i class="fa fa-cog"></i>' +
                '   </button>' +
                '<ul class="dropdown-menu dropdown-menu-right" role="menu">');

            getOperationHtml(operationHtml, row);

            operationHtml.push( '</ul></div>');
            // 数组元素以空字符拼接成字符串返回
            return operationHtml.join('');
        },

        delete: function (url) {
            layer.confirm('确定删除该条信息吗？', {
                icon: 3,
                title: '系统提示',
                btn: ['确定', '取消'],
                btnclass: ['btn btn-primary', 'btn btn-danger'],
            }, function (index) {
                // 点击确定
                layer.close(index);

                //发送请求
                $.submit(url, 'post', {'_method': 'delete'}, 'json');

            })
        },

        submit: function (url, type, data, dataType) {
            //发送请求
            $.ajax({
                url: url,
                type: type,//此处仍然使用post
                data: data,
                dataType: dataType,
                success: function (result) {
                    console.log(result);
                    if(result.code == 200) {
                        layer.msg('操作成功', {icon: 1, time: 1000, shift: 5});
                        $table.bootstrapTable('refresh')
                    }else {
                        layer.msg('操作失败');
                    }
                },
                error: function () {
                    layer.msg('请求异常');
                }
            })
        }
    });
});





