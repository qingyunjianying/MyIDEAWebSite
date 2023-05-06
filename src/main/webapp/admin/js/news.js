$(function() {
    
	$('#new-msg').validator(
			{
				onValid : function(validity) {
					$(validity.field).closest('.am-form-group').find(
							'.am-alert').hide();
				},

				onInValid : function(validity) {
					var $field = $(validity.field);
					var $group = $field.closest('.am-form-group');
					var $alert = $group.find('.am-alert');
					
				var msg = $field.data('validationMessage')
						|| this.getValidationMessage(validity);

				if (!$alert.length) {
					$alert = $('<div class="am-alert am-alert-danger"></div>')
							.hide().appendTo($group);
				}

				$alert.html(msg).show();
			}
			});


	$('#edit-msg').validator(
			{
				onValid : function(validity) {
					$(validity.field).closest('.am-form-group').find(
							'.am-alert').hide();
				},

				onInValid : function(validity) {
					var $field = $(validity.field);
					var $group = $field.closest('.am-form-group');
					var $alert = $group.find('.am-alert');
					
				var msg = $field.data('validationMessage')
						|| this.getValidationMessage(validity);

				if (!$alert.length) {
					$alert = $('<div class="am-alert am-alert-danger"></div>')
							.hide().appendTo($group);
				}

				$alert.html(msg).show();
			}
			});

});

function del(obj) {
	$('#my-confirm').modal( {
		relatedTarget : this,
		onConfirm : function(options) {
			var delId = $(obj).attr("rel");
			window.location.href = "newsDel.jsp?NewsID=" + delId;
		},
		onCancel : function() {
		}
	});
}

function edit(obj) {
	var _obj=$(obj);
	var newsTitle = _obj.prev().val();
	var NewsContent=_obj.prev().prev().val();
	var NewsId=_obj.prev().prev().prev().val();
	$('#upd_NewsTitle').val(newsTitle);
	$('#upd_NewsContent').val(NewsContent);
	$('#newsId').val(NewsId);
	$('#edit-popup').modal();
}

