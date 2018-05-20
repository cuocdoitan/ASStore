/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(".pcheck").change(function(e) {
    if(this.checked) {
        $.post($(this).attr("url"), {
            userid: $(this).attr("uid"),
            roleid: $(this).attr("pid")
        });
    }
    //console.log($(e))
});
