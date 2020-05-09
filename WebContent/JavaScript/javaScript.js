
function switchPage(page) {
    $("body").load(page);
    
}

$("#createUser").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "../api/userAdmin/create",
        data: JSON.stringify($("#createUser").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            alert("Bruger oprettet succesfuldt!");
            switchPage("brugerAdmin.html");
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
});

$("#editUser").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "../api/userAdmin/update",
        data: JSON.stringify($("#editUser").serializeJSON()),
        contentType: "application/JSON",
        method: "POST",
        success: function (data) {
            alert("Bruger opdateret succesfuldt!");
            switchPage("brugerAdmin.html");
        },
        error: function(XHR) {
            console.log(XHR);
            alert("Fejl:" + XHR.responseText);
        },
    });
});

$("#deleteUser").submit(function(event) {
    event.preventDefault();
    if (confirm('Are you sure you want to delete user?')) {
        $.ajax({
            url: "../api/userAdmin/delete",
            data: JSON.stringify($("#deleteUser").serializeJSON()),
            contentType: "application/JSON",
            method: "POST",
            success: function (data) {
                alert("Bruger slettet succesfuldt!");
                switchPage("brugerAdmin.html");
            },
            error: function (XHR) {
                console.log(XHR);
                alert("Fejl:" + XHR.responseText);
            },
        });
    }
});


window.onload = function () {

   /* $(document).ready(function () {
        $("p").click(function () {
            $(this).hide();
        });
    });*/

    $(document).ready(function(){
        $("input").focus(function(){
            $(this).css("background-color", "grey");
        });
        $("input").blur(function(){
            $(this).css("background-color", "white");
        });
    });


    $(document).ready(function(){
        $("#hide").click(function(){
            $("#tabel1").hide();
        });
        $("#show").click(function(){
            $("#tabel1").show();
        });
    });





}




