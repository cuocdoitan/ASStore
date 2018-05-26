$("#buttonPrintReport").click(function (){
    $.ajax({
            url: $("#urlProject").val() + '/statisticals/printReport',
            type: 'post',
            data: {
                yearFrom: $("select[name=yearFrom]").val(),
                monthFrom: $("select[name=monthFrom]").val(),
                dayFrom: $("select[name=dayFrom]").val(),
                dayTo : $("select[name=dayTo]").val(),
                monthTo : $("select[name=monthTo]").val()
            }
        });
});



