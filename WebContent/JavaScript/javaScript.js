
function switchPage(page) {
    $("body").load(page);
    
}

window.onload = function () {

    $(document).ready(function () {
        $("p").click(function () {
            $(this).hide();
        });
    });

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




