$(function () {
    $("#jqGrid").jqGrid({
        url: '../shstudent/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true, frozen: true },
			{ label: '学号', name: 'studentNo', index: 'student_no', width: 180, frozen: true },
			{ label: '姓名', name: 'name', index: 'name', width: 100,frozen: true },
			{ label: '姓名拼音', name: 'spellname', index: 'spellname', width: 100 },
			{ label: '曾用名', name: 'usedname', index: 'usedname', width: 100 },
			{ label: '性别', name: 'sex', index: 'sex', width: 80, hidden: true },
			{ label: '性别', name: 'sex_code', index: 'sex_code', width: 80 },
			{ label: '出生日期', name: 'birthday', index: 'birthday', width: 100 },
			{ label: '证件类型', name: 'certificateType', index: 'certificate_type', width: 120, hidden: true },
			{ label: '证件类型', name: 'certificate_code', index: 'certificate_code', width: 120 },
			{ label: '身份证号码', name: 'idCard', index: 'id_card', width: 180 },
			{ label: '一卡通号', name: 'metroCard', index: 'metro_card', width: 100 },
			{ label: '手机卡号', name: 'mobileno', index: 'mobileNo', width: 100 },
			{ label: '考生号', name: 'candidateNo', index: 'candidate_no', width: 180 },
			{ label: '准考证号', name: 'ticketNo', index: 'ticket_no', width: 100 },
			{ label: '生源地', name: 'localInstitution', index: 'local_institution', width: 150 },
			{ label: '籍贯', name: 'nativePlace', index: 'native_place', width: 150 },
			{ label: '入团时间', name: 'leagueTime', index: 'league_time', width: 100 },
			{ label: '入党时间', name: 'partyTime', index: 'party_time', width: 100 },
			{ label: '婚姻状态', name: 'maritalStatus', index: 'marital_status', width: 100, hidden: true },
			{ label: '婚姻状态', name: 'marital_code', index: 'marital_code', width: 100 },
			{ label: '身高（cm）', name: 'height', index: 'height', width: 80 },
			{ label: '体重（kg）', name: 'weight', index: 'weight', width: 80 }, 			
			{ label: '民族', name: 'nation', index: 'nation', width: 120, hidden: true },
			{ label: '民族', name: 'nation_code', index: 'nation_code', width: 120 },
			{ label: '政治面貌', name: 'politicalStatus', index: 'political_status', width: 150, hidden: true },
			{ label: '政治面貌', name: 'political_code', index: 'political_code', width: 150 },
			{ label: '来源国别', name: 'sourceCountry', index: 'source_country', width: 150, hidden: true },
			{ label: '来源国别', name: 'sourceCountry_code', index: 'sourceCountry_code', width: 150 },
			{ label: '户口所在省', name: 'registeredProvince', index: 'registered_province', width: 120 },
			{ label: '户口所在市', name: 'registeredCity', index: 'registered_city', width: 120 },
			{ label: '户口所在县', name: 'registeredCounty', index: 'registered_county', width: 120 },
			{ label: '户口所在地', name: 'domicilePlace', index: 'domicile_place', width: 120 },
			{ label: '家庭所属街道（镇', name: 'familyStreet', index: 'family_street', width: 120 },
			{ label: '警署', name: 'townPoliceStation', index: 'town_police_station', width: 120 },
			{ label: '本人住址所在省', name: 'addressProvince', index: 'address_province', width: 120 },
			{ label: '本人住址所在市', name: 'addressCity', index: 'address_city', width: 120 },
			{ label: '本人住址所在县', name: 'addressCounty', index: 'address_county', width: 120 },
			{ label: '本人住址', name: 'address', index: 'address', width: 150 },
			{ label: '家庭特殊情况', name: 'specialFamilyCircumstances', index: 'special_family_circumstances', width: 120 },
			{ label: '是否知青子女', name: 'educatedChildren', index: 'educated_children', width: 80, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' :  value == false ?
                        '<span class="label label-danger">否</span>' : '';
            } },
			{ label: '健康状况', name: 'healthCondition', index: 'health_condition', width: 120, hidden: true },
			{ label: '健康状况', name: 'health_code', index: 'health_code', width: 120 },
			{ label: '血型', name: 'bloodType', index: 'blood_type', width: 100, hidden: true },
			{ label: '血型', name: 'blood_code', index: 'blood_code', width: 100 },
			{ label: '银行卡号', name: 'bankCard', index: 'bank_card', width: 100 },
			{ label: '录取通知书邮递编号', name: 'postalCodes', index: 'postal_codes', width: 100 },
			{ label: '港澳台侨', name: 'emigrantcode', index: 'emigrantCode', width: 120, hidden: true },
			{ label: '港澳台侨', name: 'emigrantcode_code', index: 'emigrantcode_code', width: 120 },
			{ label: '户口性质', name: 'householdRegistrationType', index: 'household_registration_type', width: 120, hidden: true },
			{ label: '户口性质', name: 'householdRegistrationType_code', index: 'householdRegistrationType_code', width: 120 },
			{ label: '特长', name: 'speciality', index: 'speciality', width: 150 },
			{ label: '获奖情况', name: 'award', index: 'award', width: 150 },
            { label: '通讯地址', name: 'postaladdress', index: 'postalAddress', width: 150 },
            { label: '电子邮箱', name: 'email', index: 'email', width: 100 },
            { label: '通讯编码', name: 'postalcode', index: 'postalCode', width: 100 },
            { label: '其它联系方式', name: 'otherWay', index: 'other_way', width: 100 },
            { label: 'MSN号', name: 'msn', index: 'msn', width: 100 },
            { label: '个人主页', name: 'homepage', index: 'homePage', width: 150 },
            { label: 'QQ号', name: 'qq', index: 'qq', width: 100 },
            { label: '家庭地址', name: 'homeAddress', index: 'home_address', width: 150 },
            { label: '家庭邮编', name: 'homePostal', index: 'home_postal', width: 100 },
            { label: '家庭电话', name: 'homePhone', index: 'home_phone', width: 100 },
            { label: '家庭Email', name: 'familyEmail', index: 'family_email', width: 100},
            { label: '到达地点', name: 'arrival', index: 'arrival', width: 150 },
            { label: '预计到达时间', name: 'arrivalTime', index: 'arrival_time', width: 150 },
            { label: '到达班次', name: 'arrivalFlight', index: 'arrival_flight', width: 100 },
            { label: '是否自备车', name: 'selfMadeCar', index: 'self_made_car', width: 100, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' :  value == false ?
                        '<span class="label label-danger">否</span>' : '';
            } },
            { label: '随行人数', name: 'entourage', index: 'entourage', width: 80 },
            { label: '是否走读', name: 'nonResident', index: 'non_resident', width: 80, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' :  value == false ? '<span class="label label-danger">否</span>' : '';

            } },
            { label: '是否预定生活用品', name: 'bookLiving', index: 'book_living', width: 150, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' :
                    '<span class="label label-danger">否</span>';
            }},
            { label: '学籍号', name: 'studentStatus', index: 'student_status', width: 150 },
            { label: '学生类别', name: 'studentCategory', index: 'student_category', width: 150, hidden: true },
            { label: '学生类别', name: 'student_type_code', index: 'student_type_code', width: 150 },
            { label: '学生身份', name: 'studentIdentity', index: 'student_identity', width: 150, hidden: true },
            { label: '学生身份', name: 'student_identity_code', index: 'student_identity_code', width: 150 },
            { label: '院系', name: 'department', index: 'department', width: 150, hidden: true },
            { label: '院系', name: 'department_code', index: 'department_code', width: 150 },
            { label: '年级', name: 'grade', index: 'grade', width: 150 },
            { label: '专业', name: 'profession', index: 'profession', width: 150, hidden: true },
            { label: '专业', name: 'profession_code', index: 'profession_code', width: 150 },
            { label: '班级', name: 'stuClass', index: 'stu_class', width: 120, hidden: true },
            { label: '班级', name: 'class_code', index: 'class_code', width: 120 },
            { label: '开户银行1', name: 'bankAccountF', index: 'bank_account_f', width: 150 },
            { label: '开户银行2', name: 'bankAccountS', index: 'bank_account_s', width: 150 },
            { label: '银行账号', name: 'bankAccount', index: 'bank_account', width: 150 },
            { label: '报考省市', name: 'bkss', index: 'bkss', width: 150 },
            { label: '入学前单位', name: 'rxqdw', index: 'rxqdw', width: 150 },
            { label: '入学日期', name: 'rxrq', index: 'rxrq', width: 120 },
            { label: '入学年级', name: 'rxnj', index: 'rxnj', width: 120 },
            { label: '入学院系', name: 'rxyx', index: 'rxyx', width: 150, hidden: true },
            { label: '入学院系', name: 'rxyx_code', index: 'rxyx_code', width: 150 },
            { label: '入学专业', name: 'rxzy', index: 'rxzy', width: 150, hidden: true },
            { label: '入学专业', name: 'rxzy_code', index: 'rxzy_code', width: 150 },
            { label: '入学方式', name: 'rxfs', index: 'rxfs', width: 150, hidden: true },
            { label: '入学方式', name: 'rxfs_code', index: 'rxfs_code', width: 150 },
            { label: '培养方式', name: 'pyfs', index: 'pyfs', width: 150, hidden: true },
            { label: '培养方式', name: 'pyfs_code', index: 'pyfs_code', width: 150 },
            { label: '学生来源', name: 'xsly', index: 'xsly', width: 150 },
            { label: '校区', name: 'xq', index: 'xq', width: 150, hidden: true },
            { label: '校区', name: 'xq_code', index: 'xq_code', width: 150 },
            { label: '就读学位', name: 'jdxw', index: 'jdxw', width: 150, hidden: true},
            { label: '就读学位', name: 'jdxw_code', index: 'jdxw_code', width: 150 },
            { label: '就读学历', name: 'jdxl', index: 'jdxl', width: 150, hidden: true },
            { label: '就读学历', name: 'jdxl_code', index: 'jdxl_code', width: 150 },
            { label: '学制', name: 'xz', index: 'xz', width: 120 },
            { label: '预计毕业年份', name: 'yjbynf', index: 'yjbynf', width: 100 },
            { label: '实际毕业时间', name: 'sjbysj', index: 'sjbysj', width: 100 },
            { label: '手机号2', name: 'mobile', index: 'mobile', width: 120 },
            { label: '注册学年', name: 'zcxn', index: 'zcxn', width: 150, hidden: true },
            { label: '注册学年', name: 'zcxn_code', index: 'zcxn_code', width: 150 },
            { label: '注册学期', name: 'zcxq', index: 'zcxq', width: 120, hidden: true },
            { label: '注册学期', name: 'zcxq_code', index: 'zcxq_code', width: 120 },
            { label: '在校', name: 'zx', index: 'zx', width: 100, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' : value == false ?
                    '<span class="label label-danger">否</span>' : '';
            } },
            { label: '再籍', name: 'zj', index: 'zj', width: 100, formatter: function(value, options, row){
                return value == true ?
                    '<span class="label label-success">是</span>' : value == false ?
                        '<span class="label label-danger">否</span>' : '';
            } },
            { label: '高考成绩', name: 'gkcj', index: 'gkcj', width: 120 },
            { label: '备注', name: 'remark', index: 'remark', width: 150 },
            { label: '军训信息', name: 'jxxx', index: 'jxxx', width: 150 },
            { label: '毕业类型', name: 'bylx', index: 'bylx', width: 150, hidden: true },
            { label: '毕业类型', name: 'bylx_code', index: 'bylx_code', width: 150 },
            { label: '毕业证书编号', name: 'byzsbh', index: 'byzsbh', width: 150 },
            { label: '考区', name: 'kq', index: 'kq', width: 120 }
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
    $("#jqGrid").jqGrid('setFrozenColumns');
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		shStudent: {},
        sex:[],
        certificate_type: [],
        marital_status: [],
        nation: [],
        policital_status: [],
        source_country: [],
        health_status: [],
        blood_type: [],
        gatq: [],
        household: [],
        student_type: [],
        student_identity: [],
        department: [],
        profession: [],
        clazz:[],
        enrolment: [],
        cultivation_mode: [],
        degree: [],
        academic: [],
        registered_school_year: [],
        semester: [],
        graduation_type: [],
        campus: []
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
			vm.shStudent = {};
            $("#nativePlace").attr('type','hidden');
            $("#distpicker4").distpicker('reset', true);
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            $("#nativePlace").attr('type','text');
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.shStudent.id == null ? "../shstudent/save" : "../shstudent/update";
			console.log(url);
            vm.shStudent['arrivalTime'] = $('#arrival_time').val().replace("T", " ");
            console.log($('#nativePlace').val());
            vm.shStudent['nativePlace'] =  $('#nativePlace').val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.shStudent),
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
				    url: "../shstudent/delete",
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
        imp: function (event) {
            window.location.href = '/shstudent/import';

        },
		getInfo: function(id){
			$.get("../shstudent/info/"+id, function(r){
                if(r.code == 0){
                	console.log(r.shStudent.arrivalTime);
                    vm.shStudent = r.shStudent;
                    if (r.shStudent.arrivalTime != null && r.shStudent.arrivalTime.indexOf(" ") != -1) {
                        vm.shStudent['arrivalTime'] = r.shStudent.arrivalTime.replace(" ", "T");
                    }
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
            var arr = ['sex', 'certificate_type', 'marital_status', 'nation', 'policital_status', 'source_country', 'health_status', 'blood_type', 'gatq', 'household',
            'student_type', 'student_identity', 'department', 'profession', 'class', 'enrolment', 'cultivation_mode', 'degree', 'academic', 'registered_school_year',
                'semester', 'graduation_type', 'campus'];
            for (let i of arr) {
                this.$http.get('../app/getCodeByMark/' + i).then(function(res){
                    console.log(res.data);
                    var selectInfo = res.data.codeEntitys;
                    switch (i) {
                        case 'sex':
                            this.sex = selectInfo;
                            break;
                        case 'certificate_type':
                            this.certificate_type = selectInfo;
                            break;
                        case 'marital_status':
                            this.marital_status = selectInfo;
                            break;
                        case 'nation':
                            this.nation = selectInfo;
                            break;
                        case 'policital_status':
                            this.policital_status = selectInfo;
                            break;
                        case 'source_country':
                            this.source_country = selectInfo;
                            break;
                        case 'health_status':
                            this.health_status = selectInfo;
                            break;
                        case 'blood_type':
                            this.blood_type = selectInfo;
                            break;
                        case 'gatq':
                            this.gatq = selectInfo;
                            break;
                        case 'household':
                            this.household = selectInfo;
                            break;
                        case 'student_type':
                            this.student_type = selectInfo;
                            break;
                        case 'student_identity':
                            this.student_identity = selectInfo;
                            break;
                        case 'department':
                            this.department = selectInfo;
                            break;
                        case 'profession':
                            this.profession = selectInfo;
                            break;
                        case 'class':
                            this.clazz = selectInfo;
                            break;
                        case 'enrolment':
                            this.enrolment = selectInfo;
                            break;
                        case 'cultivation_mode':
                            this.cultivation_mode = selectInfo;
                            break;
                        case 'degree':
                            this.degree = selectInfo;
                            break;
                        case 'academic':
                            this.academic = selectInfo;
                            break;
                        case 'registered_school_year':
                            this.registered_school_year = selectInfo;
                            break;
                        case 'semester':
                            this.semester = selectInfo;
                            break;
                        case 'graduation_type':
                            this.graduation_type = selectInfo;
                            break;
                        case 'campus':
                            this.campus = selectInfo;
                            break;
                        default:
                            break;
                    }
                });
            }
        }
	}
});