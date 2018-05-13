
$('#comment').keydown(function (e) {
    
    var key = e.which;
    var url = $('#nameFB').attr("action");
    var idFeedback = $('#idfeedback').val();
    var userId = $("#userId").val();
    var comment = $("#comment").val();
    if (key === 13) {
        e.preventDefault();
        $.post(url, {
            feedback_id: idFeedback,
            user_id: userId,
            comment: comment
        }).done(function(data) {
            $("#listcomment").html(data);
        })
    }
});


