<link href="${ctxStatic}/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/global/common/common.min.css" rel="stylesheet" id="style_components" type="text/css" />

<script src="${ctxStatic}/global/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery/jquery-mymigrate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery/jquery.form-3.51.0.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/common/common.js" type="text/javascript"></script>

<!--[if lte IE 6]>
<link href="${ctxStatic}/global/plugins/bootstra-compatible/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/global/plugins/bootstra-compatible/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script>
<![endif]-->

<style>
	#seachForm .form-group{margin-bottom: 10px;}
	.form-control[readonly]{background-color: #fff;}
</style>

<script>
//跳转到前台页面上
// $(function(){
 //   备份jquery的ajax方法  
//     var _ajax=$.ajax;
//    重写ajax方法，先判断登录在执行success函数 
//     $.ajax=function(opt){
//     	var _success = opt && opt.success || function(a, b){};
//         var _opt = $.extend(opt, {
//         	success:function(data, textStatus,request){
//         		 if(request.getResponseHeader('sessionstatus')=='timeOut'){     
//         				 window.top.location = request.getResponseHeader('loginPath');
//         		 }else{
        			//_success(data, textStatus);    
//         		 }
        	
//             }  
//         });
//         _ajax(_opt);
//     };
// });
</script>