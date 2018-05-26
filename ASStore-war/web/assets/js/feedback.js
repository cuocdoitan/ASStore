
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
            comment: comment.trim()
        }).done(function(data) {
            $("textarea[name=comment]").val("");
            $("#listcomment").html(data);
        })
    }
});

function deleteFeedbackComment(id, feedbackId, url) {
    $.get(url, {
        idfeedbackdelete: feedbackId,
        idcmt: id
    }).done(function(data) {
        $("#listcomment").html(data);
    });
}

//category

function previewPhoto(event){
            var output = document.getElementById('preview_inputPhoto');
            output.src = URL.createObjectURL(event.target.files[0]);
            output.style.maxWidth = "100px";
            output.style.maxHeight = "100px";
        };

function previewImage(event){
            var outputImage = document.getElementById('preview_inputImage');
            outputImage.src = URL.createObjectURL(event.target.files[0]);
            outputImage.style.maxWidth = "250px";
            outputImage.style.maxHeight = "250px";
        };

