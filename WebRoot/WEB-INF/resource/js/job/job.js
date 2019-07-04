var job = {
	//绑定事件
	bindEvent : function() {
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		this.basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
	},

	// 处理提示信息
	dealStatusDesc : function(e) {
		$("#status").html(e.status);
		$("#statusDesc").html(e.statusDesc);
		if (e.status)
			$("#statusDesc").css("color", "green");

		setTimeout(function() {
			$("#statusDesc").html("");
		}, 3000);
	},

	// 暂停job
	pauseJob : function(id) {
		var _this = this;
		$("#status_opera_" + id).addClass("disabled");
		$.post(this.basePath + "/Servlet/JobContorl/pauseJob", {
			jobId : id
		}, function(e) {
			$("#jod_status_" + id).attr("src", _this.basePath + "/resource/css/images/sungeon_pause.png");
			$("#status_opera_" + id).removeClass("pause");
			$("#status_opera_" + id).addClass("start");
			$("#status_opera_" + id).html("恢复");
			$("#status_opera_" + id).attr("onclick", "job.resumeJob(" + id + ");");
			_this.dealStatusDesc(e);
			$("#status_opera_" + id).removeClass("disabled");
		});
	},

	// 恢复job
	resumeJob : function(id) {
		var _this = this;
		$("#status_opera_" + id).addClass("disabled");
		$.post(this.basePath + "/Servlet/JobContorl/resumeJob", {
			jobId : id
		}, function(e) {
			$("#jod_status_" + id).attr("src", _this.basePath + "/resource/css/images/sungeon_ok.png");
			$("#status_opera_" + id).removeClass("start");
			$("#status_opera_" + id).addClass("pause");
			$("#status_opera_" + id).html("暂停");
			$("#status_opera_" + id).attr("onclick", "job.pauseJob(" + id + ");");
			_this.dealStatusDesc(e);
			$("#status_opera_" + id).removeClass("disabled");
		});
	},

	// 立即执行job
	triggerJob : function(id) {
		var _this = this;
		$("#job_trigger_" + id).addClass("disabled");
		$.post(this.basePath + "/Servlet/JobContorl/triggerJob", {
			jobId : id
		}, function(e) {
			_this.dealStatusDesc(e);
			$("#job_trigger_" + id).removeClass("disabled");
		});
	},

	// 修改job
	saveJob : function(id) {
		var _this = this;
		$("#job_edit_" + id).addClass("disabled");
		var desc = $("#description_" + id).val();
		var cron = $("#cronExpression_" + id).val();

		$.post(this.basePath + "/Servlet/JobContorl/updateJob", {
			jobId : id,
			description : desc,
			cronExpression : cron
		}, function(e) {
			_this.dealStatusDesc(e);
			$("#job_edit_" + id).removeClass("disabled");
		});
	},

	//初始化绑定事件
	init : function() {
		job.bindEvent();
	}
};

//初始化事件
$(function() {
	job.init();
});