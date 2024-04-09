function becomeMember() {
    // Hide the "Become a Member" button
    document.getElementById('becomeMemberBtn').style.display = 'none';
    // Show the membership modal
    var modal = document.getElementById('membershipModal');
    modal.style.display = 'block';

    $.get("/user/become",{
    },
    //     function (result){
    //     if(result === "更改成功"){
    //         window.location.href="/index/toLogin";
    //     }
    // }
    );
}

// Function to close the membership popup
function closeMembershipPopup() {
    // Close the membership modal
    var modal = document.getElementById('membershipModal');
    modal.style.display = 'none';
    window.location.href = "/user/information";
}
