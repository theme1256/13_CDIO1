
function switchPage(page) {
    $("body").load(page);
    
}

$("#form1").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "../api/userAdmin/create",
        data: JSON.stringify($("#form1").serializeJSON()),
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

$("#form2").submit(function(event) {
    event.preventDefault();
    $.ajax({
        url: "../api/userAdmin/update",
        data: JSON.stringify($("#form2").serializeJSON()),
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




