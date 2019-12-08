

$(document).ready( function () {
    $("#courseEditBtn").on("click",courseHideAndShow);
    $("#courseBackBtn").on("click",courseHideAndShow);
    $("#labEditBtn").on("click",labHideAndShow);
    $("#labBackBtn").on("click",labHideAndShow);
    $("#editInfoBtn").on("click",editInfo);
    $("#courseSearchBtn").on("click",searchCourse);
    // $("#searchStudentLabBtn").on("click",searchLab)



})

// function searchLab() {
//     let labToSearch = {
//         regex: $("#searchStudentLab").val()
//     }
//     let toSearch_json = JSON.stringify(labToSearch);
//     $.ajax({
//         url: "/lab/rest/searchLab",
//         type: 'POST',
//         dataTye: 'json',
//         contentType: 'application/json; charset=utf-8',
//         data: toSearch_json,
//         success: function (result) {
//             let searchLabTable = '';
//             for (let f=0;f<result.data.length;f++){
//                 searchLabTable += '<tr><td>' + result.data[f].name + '</td>' +
//                     '<td><input id="invatecode" type="text" class="validate"><a href="#" class="right modal-close addlab">add</a></td>' +
//                     '</tr>'
//             }
//             $('#student_search_lab tbody').html(searchLabTable);
//         }
//     })
//
// }

function searchCourse() {
        let toSearch = {
            regex: $("#searchCourse").val()
        };
        let toSearch_json = JSON.stringify(toSearch);
        $.ajax({
            url: "/course/rest/searchCourse",
            type: 'POST',
            dataTye: 'json',
            contentType: 'application/json; charset=utf-8',
            data: toSearch_json,
            success: function (result) {
                let searchCourseTable = '';
                for (let f=0;f<result.data.length;f++){
                    searchCourseTable += '<tr><td>' + result.data[f].name + '</td>' +
                        '<td class="valign-wrapper"><input id="invatecode" type="text" placeholder="enter invate code here" class="col s4 offset-s7">' +
                        '<a href="#" class="right modal-close addcourse">add</a></td>' +
                        '</tr>'
                }
                $('#student_search_course tbody').html(searchCourseTable);
            }
        })
}
function labHideAndShow(){
    // $(".table_check").css("display","none");
    $(".labcheckcol").toggle();
}
function courseHideAndShow(){
    // $(".table_check").css("display","none");
    $(".coursecheckcol").toggle();
}

function editInfo(){
    console.log("editInfoBtn is pressed");
    $("#first_name").prop("readonly", false);
    $("#last_name").prop("readonly", false);
    $("#email").prop("readonly", false);
    $("#institution").prop("readonly", false);
    $("#password").prop("readonly", false);
    $("#editInfoBtn").toggle();
    $("#saveInfoBtn").toggle();
    $("#cancelBtn").toggle();
}
