/**
 * Bolg main JS.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {
	console.log('aaaaaaaaaaa');
	// 提交变更后，清空表单
	$("#submitEdit").click(function() {
		console.log('submit');
		$.ajax({ 
			 url: "/register", 
			 type: 'POST',
			 data:$('#userForm').serialize(),
			 success: function(data){
				/* $('#userForm')[0].reset();  */
				 
				 if (data.success) {
					 toastr.success(data.message);
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
		
});