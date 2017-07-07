$(document).ready( function () {
	 var table = $('#listItemsTable').DataTable({
			"sAjaxSource": "/api/listItem/all",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "value"},
			    { "mData": "listItemType"},
			    { "mData": "id"}
			]
	 })
	 
	 $("#listItem-form").submit(function (event) {

	        //stop submit the form, we will post it manually.
	        event.preventDefault();

	        lisItems_create_ajax_submit();

	    });
});

function lisItems_create_ajax_submit() {

    var listItem = {}
    listItem["value"] = $("#value").val();
    listItem["type"] = $("#type").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/listItem/register",
        data: JSON.stringify(listItem),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	
            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);
            $("#listItemsTable").DataTable().ajax.reload();
        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}