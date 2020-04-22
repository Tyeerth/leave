$(function(){
    $("#selectAll").click(function(){
        $(".checkboxInput").prop("checked",$(this).prop("checked"));
    });

    $(".checkbox").click(function(){
        if(".checkboxInput:checked".length==$("#checkboxInput").length){
            $("#selectAll").prop("checked",true)
        }
        else{
            $("#selectAll").prop("checked",false);
        }
    });


    $("table").on("click",".delete-a",function(){
        $(this).parent().parent().remove();
    })

    
})