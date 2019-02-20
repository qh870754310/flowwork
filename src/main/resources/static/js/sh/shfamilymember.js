$(function () {
    $("#jqGrid").jqGrid({
        url: '../shfamilymember/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 120, key: true, hidden: true, frozen: true },
			{ label: '学生基本信息id', name: 'studentId', index: 'student_id', width: 120, hidden: true, frozen: true },
			{ label: '亲属关系Id', name: 'domesticRelation', index: 'domestic_relation', width: 120, hidden: true, frozen: true },
			{ label: '亲属关系', name: 'relation_code', index: 'relation_code', width: 120, frozen: true },
			{ label: '亲属姓名', name: 'familyName', index: 'family_name', width: 100, frozen: true },
			{ label: '政治面貌', name: 'politicalStatus', index: 'political_status', width: 150, hidden: true },
			{ label: '政治面貌', name: 'political_code', index: 'political_code', width: 150 },
			{ label: '出生日期', name: 'birthday', index: 'birthday', width: 120 },
			{ label: '证件类型', name: 'certificateType', index: 'certificate_type', width: 150, hidden: true },
			{ label: '证件类型', name: 'certificate_code', index: 'certificate_code', width: 150 },
			{ label: '证件号', name: 'idCard', index: 'id_card', width: 150 },
			{ label: '家庭成员', name: 'member', index: 'member', width: 120 },
			{ label: '平均月收入', name: 'averageMonthlyEarnings', index: 'average_monthly_earnings', width: 120 },
			{ label: '健康状况', name: 'healthCondition', index: 'health_condition', width: 150, hidden: true },
			{ label: '健康状况', name: 'health_code', index: 'health_code', width: 150 },
			{ label: '是否监护人', name: 'guardian', index: 'guardian', width: 100, formatter: function(value, options, row){
				return value == true ?
					'<span class="label label-success">是</span>' : value == false ?
                        '<span class="label label-danger">否</span>' : '';
			}},
			{ label: '文化程度', name: 'educationalLevel', index: 'educational_level', width: 150, formatter: function(value, options, row){
                var str = '';
				if (value == 0) {
					str = '小学';
				} else if (value == 1) {
					str = '初中';
				} else if (value == 2) {
                    str = '高中';
                } else if (value == 3) {
                    str = '专科';
                } else if (value == '4') {
                    str = '本科';
                } else if (value == 5) {
                    str = '硕士研究生';
                } else if (value == 6) {
                    str = '博士研究生';
                } else if (value == 7) {
                    str = '博士后';
                }
				return str;
            }},
			{ label: '工作单位', name: 'company', index: 'company', width: 150 },
			{ label: '职业', name: 'profession', index: 'profession', width: 150 },
			{ label: '职务', name: 'post', index: 'post', width: 150 },
			{ label: '联系地址', name: 'contactAddress', index: 'contact_address', width: 200 },
			{ label: '邮政编码', name: 'postalCodes', index: 'postal_codes', width: 120 },
			{ label: '手机', name: 'mobile', index: 'mobile', width: 120 },
			{ label: '联系电话', name: 'phone', index: 'phone', width: 120 },
			{ label: '婚姻状况', name: 'maritalStatus', index: 'marital_status', width: 120, hidden: true },
			{ label: '婚姻状况', name: 'marital_code', index: 'marital_code', width: 120 },
			{ label: '备注', name: 'remark', index: 'remark', width: 200 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        shrinkToFit:false,
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		shFamilyMember: {},
        relationship: [],
        policital_status: [],
        certificate_type: [],
        health_status: [],
        marital_status: [],
		studentList: []
	},
    created: function() {
        console.log("页面初始化完成111");
    },
    //vue实例挂载完毕之后马上从后台获取数据,vue的生命周期中一个实例的mounted只会运行一次,mounted在vue的渲染模板挂载到$el元素上才会调用
    mounted: function() {
        console.log("页面初始化完成222");
        //这个是钩子函数
        this.$nextTick(function () {
            this.getSelectInfo();
        })

    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.shFamilyMember = {};
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
			var url = vm.shFamilyMember.id == null ? "../shfamilymember/save" : "../shfamilymember/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.shFamilyMember),
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
				    url: "../shfamilymember/delete",
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
			$.get("../shfamilymember/info/"+id, function(r){
                if(r.code == 0){
                    vm.shFamilyMember = r.shFamilyMember;
                }else{
                    alert(r.msg);
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        getSelectInfo: function (event) {
            var arr = ['relationship', 'policital_status', 'certificate_type', 'health_status', 'marital_status'];
            for (let i of arr) {
                this.$http.get('../app/getCodeByMark/' + i).then(function(res){
                    console.log(res.data);
                    if (res.data.code == 0) {
                        var selectInfo = res.data.codeEntitys;
                        switch (i) {
                            case 'relationship':
                                this.relationship = selectInfo;
                                break;
                            case 'policital_status':
                                this.policital_status = selectInfo;
                                break;
                            case 'certificate_type':
                                this.certificate_type = selectInfo;
                                break;
                            case 'health_status':
                                this.health_status = selectInfo;
                                break;
                            case 'marital_status':
                                this.marital_status = selectInfo;
                                break;
                            default:
                                break;
                        }
					}
                });
            }

            this.$http.get('../shfamilymember/getStudentList/').then(function(res){
                if (res.data.code == 0) {
					this.studentList = res.data.list;
				}
			});
        }
	}
});