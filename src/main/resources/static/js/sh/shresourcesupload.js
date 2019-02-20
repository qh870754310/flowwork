$(function () {
    $("#jqGrid").jqGrid({
        url: '../shresourcesupload/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true },
            { label: '文件名', name: 'name', index: 'name', width: 80 },
			{ label: '图文模块', name: 'type', index: 'type', width: 40, formatter: function(value, options, row) {
                if (value == 1) {
                    return '<span class="label label-success">报到须知</span>';
				} else if (value == 2) {
                    return '<span class="label label-success">资助政策</span>';
				} else if (value == 3) {
                    return '<span class="label label-success">缴费说明</span>';
                } else if (value == 4) {
                    return '<span class="label label-success">安全信息</span>';
                } else if (value == 5) {
                    return '<span class="label label-success">学院简介</span>';
                } else if (value == 6) {
                    return '<span class="label label-success">招生指南</span>';
                } else if (value == 7) {
                    return '<span class="label label-success">报道引导</span>';
                }
			}},
            { label: '状态', name: 'status', index: 'remark', width: 30, formatter: function(value, options, row) {
                if (value == 1) {
                    return '<span class="label label-success">正常</span>';
                } else if (value == 0) {
                    return '<span class="label label-danger">禁用</span>';
                }
			}},
            { label: '显示顺序', name: 'order', index: 'order', width: 30 },
            { label: '存储路径', name: 'path', index: 'path', hidden: true, width: 80 },
            { label: '文件说明', name: 'remark', index: 'remark', width: 80 }

        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            type: null
        },
		showList: true,
		title: null,
		shResourcesUpload: {
            path: "",
            name: "",
            type: "",
            remark: "",
            status: 1,
			order:""
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.shResourcesUpload = {};
            vm.shResourcesUpload.status = 1;
            $("#demo2").empty();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.shResourcesUpload.id == null ? "../shresourcesupload/save" : "../shresourcesupload/update";
            vm.shResourcesUpload['path'] = $('#path').val();
            vm.shResourcesUpload['name'] = $('#name').val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.shResourcesUpload),
			    success: function(r){
			    	if(r.code == 0){
                        alert(r, function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../shresourcesupload/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert(r, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../shresourcesupload/info/"+id, function(r){
                if(r.code == 0){
                    vm.shResourcesUpload = r.shResourcesUpload;
                    $("#demo2").empty();
                    $('#demo2').append('<img src="'+ r.imageAdress + r.shResourcesUpload.path +'" alt="'+ r.shResourcesUpload.name +'" class="layui-upload-img">')
                }else{
                    alert(r.msg);
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'type': vm.q.type},
                page:page
            }).trigger("reloadGrid");
		}
	}
});