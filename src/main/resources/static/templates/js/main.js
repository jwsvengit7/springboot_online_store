$(function(){
    $("#login_user").on("click",()=>{
        $("#signup").hide(100)
        $("#login").show(100)
    })
    $("#signin_user").on("click",()=>{
        $("#login").hide(100)
        $("#signup").show(100)
    })

    // $("#signup").on("submit",function (e) {
    //     const username = $("#username").val();
    //     const email = $("#email").val();
    //     const password = $("#password").val();
    //     const confirm_password = $("#confirm_password").val();
    //  e.preventDefault();
    //  if(username.length<4){
    //   swal("ALERT!",username.length+" username must be greater than 5","error")
    //  }
    //  else if(email===""){
    //      swal("ALERT!","email cannot be empty","error")
    //  }
    //  else if(password!==confirm_password && password!==""){
    //   alert("password does not match")
    //  }else {
    //   $.ajax({
    //    url: "http://localhost:8080/RegisterController",
    //    type: "POST",
    //    data: new FormData(this),
    //    cache: false,
    //    contentType: false,
    //    processData: false,
    //    beforeSend: () => {
    //        $(".rgb").css({"display":"flex"})
    //
    //        },
    //       success: function(response) {
    //           $(".rgb").css({"display":"none"})
    //           if (response.status === 'success') {
    //              console.log(response)
    //           } else {
    //               console.log(response+" error occur")
    //           }
    //       },
    //    error:(err)=>{
    //     alert(err)
    //    }
    //
    //   })
    //  }
    //
    // })

});