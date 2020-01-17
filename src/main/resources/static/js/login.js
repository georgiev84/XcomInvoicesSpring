jQuery(document).ready(
    function($) {

        $("#btn").click(function(event) {

            var data = {};
            data["username"] = $("#username").val();
            data["password"] = $("#password").val();


            $("#btn").prop("disabled", true);

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/invoices",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000,
                success: function (data) {
                    $("#btn-update").prop("disabled", false);
                    //...
                },
                error: function (e) {
                    $("#btn-save").prop("disabled", false);
                    //...
                }
            });


        });

    });